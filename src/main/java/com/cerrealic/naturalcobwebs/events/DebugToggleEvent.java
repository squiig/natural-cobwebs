package com.cerrealic.naturalcobwebs.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DebugToggleEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();
	private final boolean isDebugEnabled;

	public DebugToggleEvent(boolean isDebugEnabled) {
		this.isDebugEnabled = isDebugEnabled;
	}

	public boolean isDebugEnabled() {
		return isDebugEnabled;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
