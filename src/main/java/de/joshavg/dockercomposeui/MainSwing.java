package de.joshavg.dockercomposeui;

import java.util.HashMap;

import javax.swing.UnsupportedLookAndFeelException;

import de.joshavg.dockercomposeui.process.context.SwingWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.Events;
import de.joshavg.dockercomposeui.process.events.listener.ListenersMapper;
import de.joshavg.dockercomposeui.process.events.listener.SwingInitialMainTableLoader;
import de.joshavg.dockercomposeui.ui.swing.SwingWindow;

public class MainSwing {

    @SuppressWarnings("serial")
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException,
                                                 IllegalAccessException, UnsupportedLookAndFeelException {
        final SwingWindowContext context = new SwingWindowContext();
        final SwingWindow window = new SwingWindow(context);
        window.setVisible(true);

        ListenersMapper.registerListeners();
        EventHub.listenTo(Events.MAIN_INIT, new SwingInitialMainTableLoader());
        EventHub.fire(Events.MAIN_INIT, new HashMap<String, Object>() {
            {
                put("context", context);
            }
        });
    }

}
