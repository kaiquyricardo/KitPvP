package com.github.kaiquy.kitpvp.warp;

import com.github.kaiquy.kitpvp.misc.Helper;
import com.github.kaiquy.kitpvp.misc.SimpleLocation;
import lombok.val;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WarpCache implements IWarp {

    public final HashMap<String, Warp> WARPS = new HashMap<>();

    @Override
    public void addWarp(String warp, Player player) {
        val type = WarpsType.valueOf(warp.toUpperCase());

        if (WARPS.containsKey(warp)) {
            player.sendMessage(Helper.format("§cWarp %s already exists!", type.name()));
            return;
        }

        val createWarp = new Warp(
                type,
                SimpleLocation.fromLocation(player.getLocation())
        );

        WARPS.put(warp, createWarp);
        player.sendMessage(Helper.format("§aWarp %s created successfully!", type.name()));
    }

    @Override
    public void removeWarp(String warp, Player player) {
        val type = WarpsType.valueOf(warp.toUpperCase());
        val input = (Warp) WARPS.get(warp);

        if (input == null) {
            player.sendMessage(Helper.format("§cWarp %s does not exist!", type.name()));
            return;
        }

        WARPS.remove(warp);
        player.sendMessage(Helper.format("§aWarp %s removed successfully!", type.name()));
    }

    @Override
    public void teleportToWarp(String warp, Player player) {
        val type = WarpsType.valueOf(warp.toUpperCase());
        val input = (Warp) WARPS.get(warp);

        if (input == null) {
            player.sendMessage(Helper.format("§cWarp %s does not exist!", type.name()));
            return;
        }
        val createWarp = new Warp(
                type,
                SimpleLocation.fromLocation(player.getLocation())
        );

        WARPS.put(warp, createWarp);
        player.teleport(input.getLocation().toLocation());

        Helper.sendActionBar(player, Helper.format("§aTeleported to warp %s!", type.getName()));
    }
}
