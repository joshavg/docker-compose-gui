package de.joshavg.dockercomposeui.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;

public class CompositionLoader {

    private final Composition comp;

    public CompositionLoader(final Composition comp) {
        this.comp = comp;
    }

    public Map<String, Object> load() throws IOException {
        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader(new File(this.comp.getPath())));
            @SuppressWarnings("unchecked")
            final Map<String, Object> parsed = (Map<String, Object>) reader.read();
            System.out.println(parsed);
            return parsed;
        } catch (final IOException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
