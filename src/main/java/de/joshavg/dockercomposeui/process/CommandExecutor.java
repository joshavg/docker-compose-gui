package de.joshavg.dockercomposeui.process;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CommandExecutor {

    public static void run(final String wd, final String[] command) {
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
