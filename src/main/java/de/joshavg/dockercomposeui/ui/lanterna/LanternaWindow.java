package de.joshavg.dockercomposeui.ui.lanterna;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import de.joshavg.dockercomposeui.process.context.LanternaWindowContext;

public class LanternaWindow {

    private final BasicWindow window;

    private final Screen screen;

    public LanternaWindow(final LanternaWindowContext context) throws IOException {
        final Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.screen.startScreen();

        final Panel panel = new Panel();
        final Table<String> table = new Table<>("Name", "Path");
        table.getTableModel().addRow("bla", "blub");
        panel.addComponent(table);

        this.window = new BasicWindow();
        this.window.setComponent(panel);
        this.window.setCloseWindowWithEscape(true);
    }

    public void start() {
        final MultiWindowTextGUI gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(),
                                                              new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindow(this.window);
        gui.moveToTop(this.window);
        this.window.waitUntilClosed();
        gui.getGUIThread().getThread().interrupt();
    }

}
