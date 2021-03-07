package com.cerrealic.naturalcobwebs;

import com.cerrealic.cerspilib.config.CerspiPluginConfig;
import com.cerrealic.cerspilib.config.ConfigNode;
import com.google.common.collect.Sets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class NaturalCobwebsConfig extends CerspiPluginConfig {
	private ConfigNode<Float> cobwebSpawnChance;

	public NaturalCobwebsConfig(JavaPlugin plugin, FileConfiguration fileConfiguration) {
		super(plugin, fileConfiguration);
	}

	@Override
	protected HashSet<ConfigNode> getDefinedNodes() {
		cobwebSpawnChance = new ConfigNode<>("spawn-chance", 0.5f);
		return Sets.newHashSet(cobwebSpawnChance);
	}

	public float getCobwebSpawnChance() {
		return cobwebSpawnChance.getValue();
	}

	public void setCobwebSpawnChance(float cobwebSpawnChance) {
		setNodeValue(this.cobwebSpawnChance, cobwebSpawnChance);
	}
}
