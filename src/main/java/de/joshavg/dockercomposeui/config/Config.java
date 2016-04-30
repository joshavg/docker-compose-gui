package de.joshavg.dockercomposeui.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

public class Config {

    private static final String PATH;

    private static Config INSTANCE;

    static {
        PATH = System.getProperty("user.home") + File.separatorChar + ".docker-compose-ui.yml";
    }

    public static synchronized Config getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        final File file = new File(PATH);
        if (!file.exists()) {
            createEmptyFile();
        }

        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader(file));
            reader.getConfig().setClassTag("config", Config.class);
            INSTANCE = (Config) reader.read();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final YamlException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return INSTANCE;
    }

    private static void createEmptyFile() {
        YamlWriter writer = null;
        try {
            writer = new YamlWriter(new FileWriter(new File(PATH)));
            writer.getConfig().setClassTag("config", Config.class);
            writer.write(new Config());
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final YamlException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Composition> compositions = new ArrayList<>();

    private Config() {
    }

    public void save() {
        YamlWriter writer = null;
        try {
            writer = new YamlWriter(new FileWriter(new File(PATH)));
            writer.getConfig().setClassTag("config", getClass());
            writer.write(this);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final YamlException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Composition> getCompositions() {
        return this.compositions;
    }

    public void setCompositions(final List<Composition> compositions) {
        this.compositions = compositions;
    }

}
