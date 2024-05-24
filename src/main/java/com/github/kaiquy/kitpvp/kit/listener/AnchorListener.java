package com.github.kaiquy.kitpvp.kit.listener;

import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class AnchorListener implements Listener {

    private final Plugin plugin;
    private final UserCache userCache;


    @EventHandler
    void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            val player = event.getEntity();
            val user = userCache.getByPlayer(player.getName());

            if (user.getKitTypes().contains(KitType.ANCHOR)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> player.setVelocity(new Vector()), 1L);
                return;
            }

            val damager = event.getDamager();
            if (event.getDamager() instanceof Player) {
                val userDamager = userCache.getByPlayer(damager.getName());
                if (userDamager.getKitTypes().contains(KitType.ANCHOR)) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> damager.setVelocity(new Vector()), 1L);
                }
            }
        }
    }
}
