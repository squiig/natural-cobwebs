package com.cerrealic.naturalcobwebs.commands;

import com.cerrealic.cerspilib.CerspiCommand;
import com.cerrealic.cerspilib.logging.Formatter;
import com.cerrealic.cerspilib.logging.Logger;
import com.cerrealic.naturalcobwebs.NaturalCobwebsConfig;
import com.cerrealic.naturalcobwebs.NaturalCobwebsPlugin;
import com.cerrealic.naturalcobwebs.events.DebugToggleEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandNaturalCobwebs extends CerspiCommand {
	private static final String LABEL = "naturalcobwebs";
	private final NaturalCobwebsPlugin plugin;
	private final Logger logger;
	private static final String OPT_DEBUG = "debug";

	public CommandNaturalCobwebs(NaturalCobwebsPlugin naturalCobwebsPluginInstance) {
		this.plugin = naturalCobwebsPluginInstance;
		this.logger = naturalCobwebsPluginInstance.getCerspiLogger();
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (args.length <= 0) {
			return false;
		}

		NaturalCobwebsConfig config = plugin.getNaturalCobwebsConfig();

		switch (args[0].toLowerCase()) {
			case OPT_DEBUG:
				plugin.setDebugMode(!plugin.getDebugger().isEnabled());
				DebugToggleEvent debugToggleEvent = new DebugToggleEvent(plugin.getDebugger().isEnabled());
				Bukkit.getPluginManager().callEvent(debugToggleEvent);
				return true;
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		List<String> argNames = Collections.singletonList(OPT_DEBUG);

		if (args.length > 1) {
			return null;
		}

		// return unfiltered
		if (args[0].isEmpty()) {
			return argNames;
		}

		// filter based on current input
		String[] result = argNames.stream()
				.filter((name) -> name.startsWith(args[0]))
				.toArray(String[]::new);

		return Arrays.asList(result);
	}

	private void alertCurrentValue(String key, Object value) {
		logger.log(
				new Formatter("Current value of %s: " + value)
						.format(key)
						.stylizeInfo().toString(),
				false);
	}
}
