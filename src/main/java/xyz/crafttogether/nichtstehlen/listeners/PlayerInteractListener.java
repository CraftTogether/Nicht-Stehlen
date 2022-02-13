package xyz.crafttogether.nichtstehlen.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.crafttogether.nichtstehlen.configuration.ChestOwnershipHandler;

import java.util.UUID;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent event) {
        if (!event.getClickedBlock().getType().equals(Material.CHEST)) return;
        UUID chestOwner = ChestOwnershipHandler.getChestOwnership(event.getClickedBlock());
        System.out.println(chestOwner);
        if (!event.getPlayer().getUniqueId().equals(chestOwner)) {
            event.getPlayer().sendMessage(ChatColor.RED + "You failed to unlock the chest, you do not own it");
            event.setCancelled(true);
        } else {
            event.getPlayer().sendMessage(ChatColor.GREEN + "You have unlocked your chest");
        }
    }
}
