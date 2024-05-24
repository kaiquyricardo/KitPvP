package com.github.kaiquy.kitpvp.kit.listener;

import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@RequiredArgsConstructor
public class BoxerListener implements Listener {

    private final UserCache userCache;

    @EventHandler
    void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player || event.getDamager() instanceof Player) {
            val player = (Player) event.getEntity();
            val user = userCache.getByPlayer(player.getName());

            if (user.getKitTypes().contains(KitType.BOXER)) {
                event.setDamage(2D);
                return;
            }

            val damager = (Player) event.getDamager();
            val userDamager = userCache.getByPlayer(damager.getName());
            if (userDamager.getKitTypes().contains(KitType.BOXER)) {
                if(damager.getItemInHand().getType() == Material.AIR ||
                        damager.getItemInHand() == null){
                    event.setDamage(5D);
                }
            }
        }
    }
}
