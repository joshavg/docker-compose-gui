package de.joshavg.dockercomposeui;

import java.util.HashMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.joshavg.dockercomposeui.process.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.listener.Down;
import de.joshavg.dockercomposeui.process.events.listener.InitialMainTableLoader;
import de.joshavg.dockercomposeui.process.events.listener.OpenTerminal;
import de.joshavg.dockercomposeui.process.events.listener.UpAttached;
import de.joshavg.dockercomposeui.process.events.listener.UpDetached;
import de.joshavg.dockercomposeui.ui.MainWindow;

public class Main {

    public static final String EVENT_MAIN_INIT = "main.init";

    @SuppressWarnings("serial")
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException,
                                                 IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        final MainWindowContext context = new MainWindowContext();
        final MainWindow window = new MainWindow(context);
        window.setVisible(true);

        registerListeners();
        EventHub.fire(EVENT_MAIN_INIT, new HashMap<String, Object>() {
            {
                put("context", context);
            }
        });
    }

    private static void registerListeners() {
        EventHub.listenTo(EVENT_MAIN_INIT, new InitialMainTableLoader());
        EventHub.listenTo(MainWindow.EVENT_UP_ATTACHED_CLICKED, new UpAttached());
        EventHub.listenTo(MainWindow.EVENT_UP_DETACHED_CLICKED, new UpDetached());
        EventHub.listenTo(MainWindow.EVENT_DOWN_CLICKED, new Down());
        EventHub.listenTo(MainWindow.EVENT_OPEN_TERMINAL_CLICKED, new OpenTerminal());
    }

}
