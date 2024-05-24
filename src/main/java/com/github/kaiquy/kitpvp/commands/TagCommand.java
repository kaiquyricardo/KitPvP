package com.github.kaiquy.kitpvp.commands;

import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.misc.Helper;
import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.TreeMap;

@RequiredArgsConstructor
public class TagCommand {

    private final UserCache userCache;

    @Command(
            name = "tag",
            target = CommandTarget.PLAYER,
            usage = "tag <tag>"

    )

    public void tag(Context<CommandSender> context, @Optional String tag) {
        val player = (Player) context.getSender();
        val user = userCache.getByPlayer(player.getName());

        if (tag == null) {
            val groupTypes = new TreeMap<Integer, String>();
            for (GroupType group : user.getGroupTypes()) {
                val groupTag = (group.getColor() + group.getPrefix());
                groupTypes.put(group.getIndex(), groupTag);
            }
            player.sendMessage(Helper.format("§aAvailable tags: %s", String.join("§7, ", groupTypes.values())));
            return;
        }
        val groupId = GroupType.getGroupType(tag);
        if (groupId == null) {
            player.sendMessage(Helper.format("§cThe tag §e%s§c does not exist!", tag));
            return;
        }

        if (!user.getGroupTypes().contains(groupId)) {
            player.sendMessage(Helper.format("§cYou do not have access to the tag §e%s§c!", groupId.getPrefix()));
            return;
        }

        user.setGroupId(groupId.getIndex());
        player.sendMessage(Helper.format("§aYou have successfully set your tag to §e%s§a!", groupId.getPrefix()));
    }

    @Command(
            name = "tag.set",
            target = CommandTarget.PLAYER,
            usage = "tag set <tag> [player]"
    )

    public void setTag(Context<CommandSender> context, String tag, Player target) {
        val player = (Player) context.getSender();
        val user = userCache.getByPlayer(target.getName());

        val groupType = GroupType.getGroupType(tag);
        if (groupType == null) {
            player.sendMessage(Helper.format("§cThe tag §e%s§c does not exist!", tag));
            return;
        }

        user.addGroup(groupType);
        player.sendMessage(Helper.format("§aYou have successfully added the tag §e%s§a to §e%s§a!", groupType.getPrefix(), target.getName()));
    }
}
