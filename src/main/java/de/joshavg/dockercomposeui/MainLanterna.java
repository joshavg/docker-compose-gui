package de.joshavg.dockercomposeui;

import java.io.IOException;
import java.util.HashMap;

import de.joshavg.dockercomposeui.process.context.LanternaWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.Events;
import de.joshavg.dockercomposeui.process.events.listener.ListenersMapper;
import de.joshavg.dockercomposeui.ui.lanterna.LanternaWindow;

public class MainLanterna {

    public static void main(final String... args) throws IOException {
        final LanternaWindowContext context = new LanternaWindowContext();

        final LanternaWindow window = new LanternaWindow(context);
        ListenersMapper.registerListeners();
        EventHub.fire(Events.MAIN_INIT, new HashMap<String, Object>() {
            {
                put("context", context);
            }
        });

        window.start();
    }

}
