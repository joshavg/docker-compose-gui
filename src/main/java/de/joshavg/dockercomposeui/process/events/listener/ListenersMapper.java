package de.joshavg.dockercomposeui.process.events.listener;

import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.Events;

public class ListenersMapper {

    public static void registerListeners() {
        EventHub.listenTo(Events.UP_ATTACHED_CLICKED, new UpAttached());
        EventHub.listenTo(Events.UP_DETACHED_CLICKED, new UpDetached());
        EventHub.listenTo(Events.DOWN_CLICKED, new Down());
        EventHub.listenTo(Events.OPEN_TERMINAL_CLICKED, new OpenTerminal());
        EventHub.listenTo(Events.EDIT_COMPOSE_CLICKED, new EditCompose());
    }
}
