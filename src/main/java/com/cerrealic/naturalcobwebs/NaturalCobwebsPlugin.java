package com.cerrealic.naturalcobwebs;

import com.cerrealic.cerspilib.Cerspi;
import com.cerrealic.cerspilib.CerspiPlugin;
import com.cerrealic.cerspilib.config.CerspiPluginConfig;

public class NaturalCobwebsPlugin extends CerspiPlugin {
	private NaturalCobwebsConfig config;

	public static NaturalCobwebsPlugin getInstance() {
		return NaturalCobwebsPlugin.getPlugin(NaturalCobwebsPlugin.class);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		Cerspi.registerListeners(this, new GeneralEventListener(this));
	}

	@Override
	protected CerspiPluginConfig initConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		config = new NaturalCobwebsConfig(this, getConfig());
		return config;
	}

	public NaturalCobwebsConfig getNaturalCobwebsConfig() {
		return config;
	}
}
