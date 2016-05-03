package de.joshavg.dockercomposeui.ui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import de.joshavg.dockercomposeui.process.context.SwingWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub;
import de.joshavg.dockercomposeui.process.events.Events;

@SuppressWarnings("serial")
public class SwingWindow extends JFrame {

    private final JTable table;

    public SwingWindow(final SwingWindowContext context) throws ClassNotFoundException, InstantiationException,
                                                         IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setTitle();
        setLocationByPlatform(true);

        final Container container = getContentPane();
        container.setLayout(new BorderLayout());

        this.table = buildTable(context);

        final JScrollPane scrollPane = new JScrollPane(this.table);
        container.add(scrollPane, BorderLayout.CENTER);

        final Map<String, Object> eventData = new HashMap<>();
        eventData.put("context", context);

        final JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(new LambdaButton("[A] Up Attached", e -> EventHub.fire(Events.UP_ATTACHED, eventData)));
        southPanel.add(new LambdaButton("[D] Up Detached", e -> EventHub.fire(Events.UP_DETACHED, eventData)));
        southPanel.add(new LambdaButton("[W] Down", e -> EventHub.fire(Events.DOWN, eventData)));
        southPanel.add(new LambdaButton("[T] Open Terminal", e -> EventHub.fire(Events.OPEN_TERMINAL, eventData)));
        southPanel.add(new LambdaButton("[E] Edit Compose", e -> EventHub.fire(Events.EDIT_COMPOSE, eventData)));
        container.add(southPanel, BorderLayout.SOUTH);

        createMenu(eventData);

        addKeyListeners(eventData, this.table);
        Arrays.asList(southPanel.getComponents()).forEach(c -> addKeyListeners(eventData, c));
    }

    private void createMenu(final Map<String, Object> data) {
        final JMenuBar bar = new JMenuBar();
        final JMenu menu = new JMenu("Config");

        final JMenuItem addEntry = new JMenuItem("Add Entry");
        addEntry.addActionListener(e -> EventHub.fire(Events.ADD_ENTRY, data));
        menu.add(addEntry);

        final JMenuItem removeEntry = new JMenuItem("Remove Entry");
        removeEntry.addActionListener(e -> EventHub.fire(Events.REMOVE_ENTRY, data));
        menu.add(removeEntry);

        bar.add(menu);
        setJMenuBar(bar);
    }

    private static void addKeyListeners(final Map<String, Object> data, final Component comp) {
        comp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        EventHub.fire(Events.UP_ATTACHED, data);
                        break;
                    case KeyEvent.VK_D:
                        EventHub.fire(Events.UP_DETACHED, data);
                        break;
                    case KeyEvent.VK_W:
                        EventHub.fire(Events.DOWN, data);
                        break;
                    case KeyEvent.VK_T:
                        EventHub.fire(Events.OPEN_TERMINAL, data);
                        break;
                    case KeyEvent.VK_E:
                        EventHub.fire(Events.EDIT_COMPOSE, data);
                        break;
                }
            }
        });
    }

    private void setTitle() {
        setTitle("docker-compose-ui");
        final Toolkit xToolkit = Toolkit.getDefaultToolkit();
        java.lang.reflect.Field awtAppClassNameField;
        try {
            awtAppClassNameField = xToolkit.getClass().getDeclaredField("awtAppClassName");
            awtAppClassNameField.setAccessible(true);
            awtAppClassNameField.set(xToolkit, "docker-compose-ui");
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static JTable buildTable(final SwingWindowContext context) {
        final DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        model.addColumn("Name");
        model.addColumn("Path");

        final JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        context.setMainTableModel(model);
        context.setMainTable(table);
        return table;
    }

}
