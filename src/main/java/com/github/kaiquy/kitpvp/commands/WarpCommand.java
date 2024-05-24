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
public class WarpCommand {

    private final WarpCache warpCache;

    @Command(
            name = "setwarp",
            aliases = {"setarwarp"},
            target = CommandTarget.PLAYER,
            permission = "kitpvp.setwarp",
            usage = "setwarp <tipo>"
    )


    public void setWarp(Context<CommandSender> context, String warp) {
        val player = (Player) context.getSender();

        warpCache.addWarp(warp, player);
    }


    @Command(
            name = "delwarp",
            aliases = {"deletarwarp"},
            target = CommandTarget.PLAYER,
            permission = "kitpvp.delwarp",
            usage = "delwarp <tipo>"
    )

    public void delWarp(Context<CommandSender> context, String warp) {
        val player = (Player) context.getSender();

        warpCache.removeWarp(warp, player);
    }
}
