package de.joshavg.dockercomposeui.process.events.listener;

import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.Events;

public class ListenersMapper {

    public static void registerListeners() {
        EventHub.listenTo(Events.UP_ATTACHED, new UpAttached());
        EventHub.listenTo(Events.UP_DETACHED, new UpDetached());
        EventHub.listenTo(Events.DOWN, new Down());
        EventHub.listenTo(Events.OPEN_TERMINAL, new OpenTerminal());
        EventHub.listenTo(Events.EDIT_COMPOSE, new EditCompose());
        EventHub.listenTo(Events.ADD_ENTRY, new AddEntry());
        EventHub.listenTo(Events.REMOVE_ENTRY, new RemoveEntry());
    }
}
