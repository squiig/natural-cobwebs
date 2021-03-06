package com.cerrealic.naturalcobwebs;

import com.cerrealic.naturalcobwebs.events.DebugToggleEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GeneralEventListener implements Listener {
	private final NaturalCobwebsConfig config;

	public GeneralEventListener(NaturalCobwebsPlugin plugin) {
		this.config = plugin.getNaturalCobwebsConfig();
	}

	@EventHandler
	public void onDebugToggle(DebugToggleEvent event) {
	}
}
