package com.github.kaiquy.kitpvp.commands;

import com.github.kaiquy.kitpvp.level.LevelType;
import com.github.kaiquy.kitpvp.misc.Helper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class LevelCommand {


    @Command(
            name = "level",
            target = CommandTarget.PLAYER,
            usage = "level"
    )

    public void level(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        List<String> levels = Lists.newArrayList();
        for (LevelType levelType : LevelType.values()) {
            val level = (levelType.getColor() + levelType.getSymbol());
            levels.add(level);
        }
        player.sendMessage(Helper.format("§aLevels: %s", String.join("§7, ", levels)));

    }
}
