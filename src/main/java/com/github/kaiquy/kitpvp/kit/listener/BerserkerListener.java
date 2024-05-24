package com.github.kaiquy.kitpvp.kit.listener;

import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class BerserkerListener implements Listener {

    private final UserCache userCache;

    @EventHandler
    void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            val killer = (Player) event.getEntity().getKiller();
            val user = userCache.getByPlayer(killer.getName());

            if (user.getKitTypes().contains(KitType.BERSERKER)) {
                killer.addPotionEffect(new PotionEffect(
                        PotionEffectType.INCREASE_DAMAGE,
                        30,
                        1
                ));
            }
        }
    }
}
