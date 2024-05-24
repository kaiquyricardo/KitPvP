package com.github.kaiquy.kitpvp.misc;

import lombok.val;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Helper {

    public static String format(String input, Object... args) {
        return String.format(input, args);
    }

    public static void sendActionBar(Player player, String message) {
        val connect = ((CraftPlayer) player).getHandle().playerConnection;
        val packet = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);

        connect.sendPacket(packet);
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
