package de.joshavg.dockercomposeui.process.context;

import java.util.Optional;

public class LanternaWindowContext implements MainWindowContext {

    @Override
    public Optional<String> getSelectedPath() {
        return Optional.empty();
    }

}
