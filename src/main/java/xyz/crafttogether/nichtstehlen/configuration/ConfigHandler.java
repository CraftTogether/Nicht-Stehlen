package xyz.crafttogether.nichtstehlen.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.crafttogether.nichtstehlen.NichtStehlen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConfigHandler.class);
    private static Config config;
    private static File file;
    private static final int latestConfigVersion = 1;

    public static void loadConfig() {
        file = new File(NichtStehlen.getPlugin().getDataFolder() + "/config.yml");
        reloadConfig();
    }


    public static void reloadConfig() {
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        config = new Config(fc.getInt("version"));
        validateConfigVersion();
    }

    public static Config getConfig() {
        return config;
    }

    public static void validateConfigVersion() {
        logger.warn("Config is outdated, regenerating configuration");
        if (config.getConfigVersion() != latestConfigVersion) {
            InputStream defaultConfig = NichtStehlen.class.getResourceAsStream("/config.yml");
            assert defaultConfig != null;
            try {
                Files.copy(defaultConfig, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
