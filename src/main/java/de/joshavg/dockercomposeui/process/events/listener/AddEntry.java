package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.joshavg.dockercomposeui.config.Composition;
import de.joshavg.dockercomposeui.config.Config;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.process.events.Events;

public class AddEntry implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final String name = JOptionPane.showInputDialog(null, "Name");
        if (name == null || name.length() == 0) {
            return;
        }

        final JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final int retval = chooser.showOpenDialog(null);
        if (retval == JFileChooser.CANCEL_OPTION) {
            return;
        }

        final String path = chooser.getSelectedFile().getAbsolutePath();

        final Composition comp = new Composition();
        comp.setName(name);
        comp.setPath(path);
        Config.getInstance().getCompositions().add(comp);
        Config.getInstance().save();

        EventHub.fire(Events.MAIN_INIT, event);
    }

}
