package com.cerrealic.naturalcobwebs;

import com.cerrealic.cerspilib.config.CerspiPluginConfig;
import com.cerrealic.cerspilib.config.ConfigNode;
import com.google.common.collect.Sets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class NaturalCobwebsConfig extends CerspiPluginConfig {
	private ConfigNode<Double> cobwebSpawnChance;
	private ConfigNode<Integer> cobwebSpawnPeriod;
	private ConfigNode<Integer> maxCobwebSpawnsPerChunkSession;

	public NaturalCobwebsConfig(JavaPlugin plugin, FileConfiguration fileConfiguration) {
		super(plugin, fileConfiguration);
	}

	@Override
	protected HashSet<ConfigNode> getDefinedNodes() {
		cobwebSpawnChance = new ConfigNode<>("cobweb-spawn-chance", 0.0003d);
		cobwebSpawnPeriod = new ConfigNode<>("cobweb-spawn-period", 400);
		maxCobwebSpawnsPerChunkSession = new ConfigNode<>("max-cobweb-spawns-per-chunk-session", 30);
		return Sets.newHashSet(cobwebSpawnChance, cobwebSpawnPeriod, maxCobwebSpawnsPerChunkSession);
	}

	public double getCobwebSpawnChance() {
		return cobwebSpawnChance.getValue();
	}

	public void setCobwebSpawnChance(double cobwebSpawnChance) {
		setNodeValue(this.cobwebSpawnChance, cobwebSpawnChance);
	}

	public int getCobwebSpawnPeriod() {
		return cobwebSpawnPeriod.getValue();
	}

	public void setCobwebSpawnPeriod(int cobwebSpawnPeriod) {
		setNodeValue(this.cobwebSpawnPeriod, cobwebSpawnPeriod);
	}

	public int getMaxCobwebSpawnsPerChunkSession() {
		return maxCobwebSpawnsPerChunkSession.getValue();
	}

	public void setMaxCobwebSpawnsPerChunkSession(int maxCobwebSpawnsPerChunkSession) {
		setNodeValue(this.maxCobwebSpawnsPerChunkSession, maxCobwebSpawnsPerChunkSession);
	}
}
