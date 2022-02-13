package xyz.crafttogether.nichtstehlen.configuration;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.crafttogether.nichtstehlen.NichtStehlen;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ChestOwnershipHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConfigHandler.class);
    private static File file;
    private static FileConfiguration claims;

    public static void load() throws IOException {
        file = new File(NichtStehlen.getPlugin().getDataFolder() + "/claimedChests.yml");
        if (!file.exists()) {
            boolean successful = file.createNewFile();
            if (successful) {
                logger.info("Created new claimedChest.yml file");
            } else {
                logger.error("Failed to create new claimedChest.yml file");
            }
        }
        reload();
    }

    public static void reload() {
        claims = YamlConfiguration.loadConfiguration(file);
    }

    public static UUID getChestOwnership(Block block) {
        return UUID.fromString(claims.getString(String.format("%s.%s,%s,%s", block.getWorld().getName(), block.getX(), block.getY(), block.getZ())));
    }

    public static void addChestOwnership(Block block, UUID uuid) {
        String world = block.getWorld().getName();
        String blockString = String.format("%s,%s,%s", block.getX(), block.getY(), block.getZ());
        claims.set(String.format("%s.%s", world, blockString), uuid.toString());
        save();
    }

    public static void removeChestOwnership(Block block) {
        String world = block.getWorld().getName();
        String blockString = String.format("%s,%s,%s", block.getX(), block.getY(), block.getZ());
        claims.set(String.format("%s.%s", world, blockString), null);
        save();
    }

    public static synchronized void save() {
        try {
            claims.save(file);
        } catch (IOException e) {
            logger.error("Failed to save claimed chests to disk");
            e.printStackTrace();
        }
    }
}
