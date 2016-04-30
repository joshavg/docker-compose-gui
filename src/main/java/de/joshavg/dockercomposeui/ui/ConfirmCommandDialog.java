package de.joshavg.dockercomposeui.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ConfirmCommandDialog extends JDialog {

    public static void run(final String command, final String wd) {
        final String userCommand = JOptionPane.showInputDialog("Confirm command to run in working directory: " + wd,
                                                               command);
        if (userCommand != null && userCommand.length() > 0) {
            System.out.println("executing command " + command);
            final ProcessBuilder builder = new ProcessBuilder("x-terminal-emulator", "-e", userCommand);
            builder.directory(new File(wd));
            try {
                builder.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

}
