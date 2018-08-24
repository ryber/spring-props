package ryber;


import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class EncryptedPropertiesLoader implements PropertySourceLoader, PriorityOrdered {

    public EncryptedPropertiesLoader(){
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        final Properties props = PropertiesLoaderUtils.loadProperties(resource);

        if (props.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<PropertySource<?>> collect =  new ArrayList<>();
            System.out.println("propertySources = " + props.stringPropertyNames());
            for (String key : props.stringPropertyNames()) {
                collect.add(new EncryptedProperties(key, props.getProperty(key)));
            }
            return collect;
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
