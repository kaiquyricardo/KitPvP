package com.github.kaiquy.kitpvp.kit.listener;

import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@RequiredArgsConstructor
public class ArcherListener implements Listener {

    private final UserCache userCache;
    private final HashMap<Arrow, Player> arrows = new HashMap<>();

    @EventHandler
    void onProjectile(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            val player = (Player) event.getEntity().getShooter();
            val user = userCache.getByPlayer(player.getName());

            if (user.getKitTypes().contains(KitType.ARCHER)) {
                if (event.getEntity().getType() == EntityType.ARROW) {
                    arrows.put((Arrow) event.getEntity(), player);
                }
            }
        }
    }

    @EventHandler
    void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Arrow) {
                val arrow = (Arrow) event.getDamager();
                if (arrows.containsKey(arrow)) {
                    arrows.get(arrow).getInventory().addItem(new ItemStack(Material.ARROW));
                    arrows.remove(arrow);
                }
            }
        }
    }
}
