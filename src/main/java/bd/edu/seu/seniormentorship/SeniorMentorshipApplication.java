package bd.edu.seu.seniormentorship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SeniorMentorshipApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SeniorMentorshipApplication.class);

        // Load .env file manually to avoid external dependency issues
        try {
            Map<String, Object> envMap = loadEnvFile(".env");

            app.addInitializers(context -> {
                ConfigurableEnvironment env = context.getEnvironment();
                MutablePropertySources propertySources = env.getPropertySources();
                propertySources.addFirst(new MapPropertySource(".env", envMap));
            });
        } catch (Exception e) {
            System.err.println("Could not load .env file: " + e.getMessage());
        }

        app.run(args);
    }

    private static Map<String, Object> loadEnvFile(String filePath) {
        Map<String, Object> envMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int eqIndex = line.indexOf('=');
                if (eqIndex > 0) {
                    String key = line.substring(0, eqIndex).trim();
                    String value = line.substring(eqIndex + 1).trim();
                    // Remove optional quotes around value
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    } else if (value.startsWith("'") && value.endsWith("'")) {
                        value = value.substring(1, value.length() - 1);
                    }
                    envMap.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading .env file: " + e.getMessage());
        }
        return envMap;
    }
}
