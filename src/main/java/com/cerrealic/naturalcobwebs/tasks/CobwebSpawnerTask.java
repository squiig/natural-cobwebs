package com.cerrealic.naturalcobwebs.tasks;

import com.cerrealic.naturalcobwebs.NaturalCobwebsPlugin;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class CobwebSpawnerTask {
	private BukkitTask runningTask;
	private final int delay;
	private final int period;
	private final Chunk chunk;
	private final Random random;
	private final float spawnChance;

	public CobwebSpawnerTask(Chunk chunk, int delay, int period, float spawnChance) {
		this.chunk = chunk;
		this.delay = delay;
		this.period = period;
		this.random = new Random();
		this.spawnChance = spawnChance;
	}

	public CobwebSpawnerTask start() {
		runningTask = new BukkitRunnable() {
			@Override
			public void run() {
				attemptSpawn();
			}
		}.runTaskTimer(NaturalCobwebsPlugin.getInstance(), delay, period);
		return this;
	}

	public void stop() {
		if (isRunning()) {
			runningTask.cancel();
		}
	}

	public BukkitTask getRunningTask() {
		return runningTask;
	}

	public boolean isRunning() {
		return runningTask != null && !runningTask.isCancelled();
	}

	private void attemptSpawn() {
		ChunkSnapshot cs = chunk.getChunkSnapshot();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int columnHeight = cs.getHighestBlockYAt(x, z);
				for (int y = 1; y < columnHeight; y++) {
					Material mat = cs.getBlockType(x, y, z);
					if (isValidSpawnCoord(cs, x, y, z) && random.nextFloat() < spawnChance) {
						chunk.getBlock(x, y, z).setType(Material.COBWEB);
					}
				}
			}
		}
	}

	private boolean isValidSpawnCoord(ChunkSnapshot chunkSnapshot, int x, int y, int z) {
		Material mat = chunkSnapshot.getBlockType(x, y, z);
		Material matBelow = chunkSnapshot.getBlockType(x, y - 1, z);
		Material matAbove = chunkSnapshot.getBlockType(x, y + 1, z);
		Material matNorth = chunkSnapshot.getBlockType(x, y, z + 1);
		Material matSouth = chunkSnapshot.getBlockType(x, y, z - 1);
		Material matEast = chunkSnapshot.getBlockType(x + 1, y, z);
		Material matWest = chunkSnapshot.getBlockType(x - 1, y, z);

		return mat.isAir() && matAbove.isSolid() &&
				(matNorth.isSolid() || matSouth.isSolid() || matEast.isSolid() || matWest.isSolid());
	}
}
