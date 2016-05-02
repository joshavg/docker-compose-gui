package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import de.joshavg.dockercomposeui.config.Composition;
import de.joshavg.dockercomposeui.config.Config;
import de.joshavg.dockercomposeui.process.context.SwingWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;

public class SwingInitialMainTableLoader implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final SwingWindowContext context = (SwingWindowContext) event.get("context");
        Config.getInstance().getCompositions().forEach(comp -> loadIntoTable(comp, context.getMainTableModel()));
    }

    private static void loadIntoTable(final Composition comp, final DefaultTableModel model) {
        model.addRow(new Object[] { comp.getName(), comp.getPath() });

        // {apache={image=eboraas/apache-php, volumes=[/home/jgizycki/docker/apache-php-1/:/var/www/html], ports=[80:80,
        // 443:443]}}
        // try {
        // final Map<String, Object> parsed = new CompositionLoader(comp).load();
        // } catch (final IOException e) {
        // e.printStackTrace();
        // }
    }

}
