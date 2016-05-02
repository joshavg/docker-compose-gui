package de.joshavg.dockercomposeui.ui.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LambdaButton extends JButton {

    public LambdaButton(final String title, final Consumer<MouseEvent> onclick) {
        super(title);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                onclick.accept(e);
            }
        });
    }

}
