package de.joshavg.dockercomposeui.ui.swing;

import java.io.File;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import de.joshavg.dockercomposeui.process.CommandExecutor;

@SuppressWarnings("serial")
public class ConfirmCommandDialog extends JDialog {

    public static void run(final String wd, final String... command) {
        final String approxCommand = Arrays.asList(command).stream().reduce((a, b) -> a + " " + b).get();

        String useWd = wd;
        if (!wd.endsWith(File.separator)) {
            useWd += File.separator;
        }
        final int chosenOption = JOptionPane.showConfirmDialog(null, useWd + approxCommand, "Confirm Action",
                                                               JOptionPane.OK_CANCEL_OPTION);

        if (chosenOption == JOptionPane.OK_OPTION) {
            CommandExecutor.run(wd, command);
        }
    }

}
