package de.joshavg.dockercomposeui.process.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHub {

    public interface EventListener {
        void onEvent(Map<String, Object> event);
    }

    private static final Map<String, List<EventListener>> LISTENERS = new HashMap<>();

    public static void listenTo(final String event, final EventListener listener) {
        System.out.println("listener for event " + event + " registered");
        if (LISTENERS.get(event) == null) {
            LISTENERS.put(event, new ArrayList<>());
        }
        LISTENERS.get(event).add(listener);
    }

    public static void fire(final String event, final Map<String, Object> data) {
        System.out.println("event " + event + " fired");
        if (LISTENERS.get(event) == null) {
            return;
        }
        LISTENERS.get(event).forEach(listener -> listener.onEvent(data));
    }

}
