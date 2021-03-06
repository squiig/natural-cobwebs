package com.cerrealic.naturalcobwebs;

import com.cerrealic.naturalcobwebs.events.DebugToggleEvent;
import com.cerrealic.naturalcobwebs.tasks.CobwebSpawnerTask;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.HashMap;
import java.util.Map;

public class GeneralEventListener implements Listener {
	private final NaturalCobwebsConfig config;
	private final Map<Chunk, CobwebSpawnerTask> spawnerTasks;

	public GeneralEventListener(NaturalCobwebsPlugin plugin) {
		this.config = plugin.getNaturalCobwebsConfig();
		this.spawnerTasks = new HashMap<>();
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		Chunk chunk = event.getChunk();
		tryRemoveSpawner(chunk);

		CobwebSpawnerTask spawnerTask = new CobwebSpawnerTask(chunk, 0, config.getCobwebSpawnPeriod(), (float)config.getCobwebSpawnChance(), config.getMaxCobwebSpawnsPerChunkSession());
		spawnerTasks.put(chunk, spawnerTask);
		spawnerTask.start();
	}

	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event) {
		tryRemoveSpawner(event.getChunk());
	}

	@EventHandler
	public void onDebugToggle(DebugToggleEvent event) {
	}

	private void tryRemoveSpawner(Chunk chunk) {
		if (spawnerTasks.containsKey(chunk)) {
			spawnerTasks.get(chunk).stop();
			spawnerTasks.remove(chunk);
		}
	}
}
