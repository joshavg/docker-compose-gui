package de.joshavg.dockercomposeui;

import java.util.HashMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.joshavg.dockercomposeui.process.context.SwingWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.listener.Down;
import de.joshavg.dockercomposeui.process.events.listener.EditCompose;
import de.joshavg.dockercomposeui.process.events.listener.SwingInitialMainTableLoader;
import de.joshavg.dockercomposeui.process.events.listener.OpenTerminal;
import de.joshavg.dockercomposeui.process.events.listener.UpAttached;
import de.joshavg.dockercomposeui.process.events.listener.UpDetached;
import de.joshavg.dockercomposeui.ui.SwingWindow;

public class Main {

    public static final String EVENT_MAIN_INIT = "main.init";

    @SuppressWarnings("serial")
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException,
                                                 IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        final SwingWindowContext context = new SwingWindowContext();
        final SwingWindow window = new SwingWindow(context);
        window.setVisible(true);

        registerListeners();
        EventHub.fire(EVENT_MAIN_INIT, new HashMap<String, Object>() {
            {
                put("context", context);
            }
        });
    }

    private static void registerListeners() {
        EventHub.listenTo(EVENT_MAIN_INIT, new SwingInitialMainTableLoader());
        EventHub.listenTo(SwingWindow.EVENT_UP_ATTACHED_CLICKED, new UpAttached());
        EventHub.listenTo(SwingWindow.EVENT_UP_DETACHED_CLICKED, new UpDetached());
        EventHub.listenTo(SwingWindow.EVENT_DOWN_CLICKED, new Down());
        EventHub.listenTo(SwingWindow.EVENT_OPEN_TERMINAL_CLICKED, new OpenTerminal());
        EventHub.listenTo(SwingWindow.EVENT_EDIT_COMPOSE_CLICKED, new EditCompose());
    }

}
