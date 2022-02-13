package xyz.crafttogether.nichtstehlen.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.crafttogether.nichtstehlen.configuration.ChestOwnershipHandler;

import java.util.UUID;

public class BlockBreakEvent implements Listener {
    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        if (!event.getBlock().getType().equals(Material.CHEST)) return;
        final UUID owner = ChestOwnershipHandler.getChestOwnership(event.getBlock());
        if (owner == null) return;
        if (event.getPlayer().getUniqueId().equals(owner) || !event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            ChestOwnershipHandler.removeChestOwnership(event.getBlock());
        } else {
            event.getPlayer().sendMessage(ChatColor.RED + "You do not own this chest, you can not break it");
            event.setCancelled(true);
        }
    }
}
