package xyz.crafttogether.nichtstehlen.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.crafttogether.nichtstehlen.configuration.ChestOwnershipHandler;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getBlock().getType().equals(Material.CHEST)) return;
        ChestOwnershipHandler.addChestOwnership(event.getBlock(), event.getPlayer().getUniqueId());
    }
}
