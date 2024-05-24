package com.github.kaiquy.kitpvp.commands;

import com.github.kaiquy.kitpvp.warp.WarpCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SpawnCommand {

    private final WarpCache warpCache;

    @Command(
            name = "spawn",
            target = CommandTarget.PLAYER
    )

    public void spawn(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        warpCache.teleportToWarp("default", player);
    }
}
