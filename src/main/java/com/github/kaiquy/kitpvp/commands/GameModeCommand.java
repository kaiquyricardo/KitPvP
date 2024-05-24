package com.github.kaiquy.kitpvp.commands;

import com.github.kaiquy.kitpvp.misc.Helper;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand {

    @Command(
            name = "gamemode",
            aliases = {"gm"},
            target = CommandTarget.ALL,
            permission = "kitpvp.gamemode",
            usage = "gamemode <mode> [player]"
    )

    public void gameMode(Context<CommandSender> context, Integer mode, @Optional Player target) {
        val sender = context.getSender();
        val gameMode = GameMode.getByValue(mode);

        if (gameMode == null) return;

        if (target == null) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cYou must specify a player.");
                return;
            }

            val player = (Player) sender;
            player.setGameMode(gameMode);
            player.sendMessage(Helper.format("§aYour game mode has been updated to %s", gameMode.name()));
            return;
        }

        target.setGameMode(gameMode);
        target.sendMessage(Helper.format("§aYour game mode has been updated to %s", gameMode.name()));
        sender.sendMessage(Helper.format("§aThe game mode of %s has been updated to %s", target.getName(), gameMode.name()));
    }
}
