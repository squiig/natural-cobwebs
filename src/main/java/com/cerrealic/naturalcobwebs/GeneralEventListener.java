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
	private Map<Chunk, CobwebSpawnerTask> spawnerTasks;

	public GeneralEventListener(NaturalCobwebsPlugin plugin) {
		this.config = plugin.getNaturalCobwebsConfig();
		this.spawnerTasks = new HashMap<>();
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		Chunk chunk = event.getChunk();
		CobwebSpawnerTask spawnerTask = new CobwebSpawnerTask(chunk, 0, config.getCobwebSpawnPeriod(), config.getCobwebSpawnChance());
		spawnerTasks.put(chunk, spawnerTask);
		spawnerTask.start();
	}

	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event) {
		if (spawnerTasks.containsKey(event.getChunk())) {
			spawnerTasks.get(event.getChunk()).stop();
			spawnerTasks.remove(event.getChunk());
		}
	}

	@EventHandler
	public void onDebugToggle(DebugToggleEvent event) {
	}
}
