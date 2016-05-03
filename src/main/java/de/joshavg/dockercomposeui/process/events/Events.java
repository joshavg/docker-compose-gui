package de.joshavg.dockercomposeui.process.events;

public interface Events {

    String MAIN_INIT = "main.init";

    String UP_ATTACHED = "main.window.up.attached";

    String UP_DETACHED = "main.window.up.detached";

    String DOWN = "main.window.down";

    String OPEN_TERMINAL = "main.window.open.terminal";

    String EDIT_COMPOSE = "main.window.edit.compose";

    String ADD_ENTRY = "main.window.add.entry";

    String REMOVE_ENTRY = "main.window.remove.entry";

}