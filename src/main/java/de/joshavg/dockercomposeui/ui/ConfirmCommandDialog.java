package de.joshavg.dockercomposeui.ui;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ConfirmCommandDialog extends JDialog {

    public static void run(final String command, final String wd) {
        run(wd, new String[] { command });
    }

    public static void run(final String wd, final String... command) {
        final String approxCommand = Arrays.asList(command).stream().reduce((a, b) -> a + " " + b).get();

        final String userCommand = JOptionPane.showInputDialog("Confirm command to run in working directory: " + wd,
                                                               approxCommand);
        if (userCommand != null && userCommand.length() > 0) {
            System.out.println("executing command " + Arrays.toString(command));
            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(new File(wd));
            try {
                builder.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

}
