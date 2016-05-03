package de.joshavg.dockercomposeui.process.events.listener;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import de.joshavg.dockercomposeui.config.Composition;
import de.joshavg.dockercomposeui.config.Config;
import de.joshavg.dockercomposeui.process.context.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.process.events.Events;

public class RemoveEntry implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");
        final Optional<String> optPath = context.getSelectedPath();

        if (!optPath.isPresent()) {
            return;
        }

        final String path = optPath.get();

        final Config config = Config.getInstance();
        final List<Composition> compositions = config.getCompositions();
        final List<Composition> newComps = compositions.stream().filter(c -> !c.getPath().equals(path))
                                                       .collect(Collectors.toList());
        compositions.clear();
        compositions.addAll(newComps);
        config.save();

        EventHub.fire(Events.MAIN_INIT, event);
    }

}
