package dev.schim.chester;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.EnumSet;
import java.util.Set;

public class SignListener implements Listener {
    private static final Set<Material> WALL_SIGN_TYPES = EnumSet.of(
            Material.ACACIA_WALL_SIGN,
            Material.SPRUCE_WALL_SIGN,
            Material.BAMBOO_WALL_SIGN,
            Material.CHERRY_WALL_SIGN,
            Material.BIRCH_WALL_SIGN,
            Material.CRIMSON_WALL_SIGN,
            Material.JUNGLE_WALL_SIGN,
            Material.MANGROVE_WALL_SIGN,
            Material.WARPED_WALL_SIGN,
            Material.DARK_OAK_WALL_SIGN,
            Material.OAK_WALL_SIGN
    );

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event) {
        if (event.getAction().isLeftClick()) {
            return;
        }
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        if (!WALL_SIGN_TYPES.contains(clickedBlock.getType())) {
            return;
        }
        Sign sign = (Sign) clickedBlock.getState();
        if (!sign.isWaxed()) {
            return;
        }
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            return;
        }
        WallSign signData = (WallSign) clickedBlock.getBlockData();
        BlockFace attachedFace = signData.getFacing().getOppositeFace();
        Block attachedBlock = clickedBlock.getRelative(attachedFace);
        if (attachedBlock.getState() instanceof InventoryHolder ih) {
            event.setCancelled(true);
            player.openInventory(ih.getInventory());
        }
    }
}
