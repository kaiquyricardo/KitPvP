package com.github.kaiquy.kitpvp.item;

import com.github.kaiquy.kitpvp.misc.ItemCreator;
import de.tr7zw.changeme.nbtapi.NBT;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Item {

    public void addItem(Player player) {
        val kitSelector = new ItemCreator(Material.CHEST)
                .display("§aKit Selector")
                .addLore(
                        "§7Select your kit.",
                        "§7Right click to open the menu.")
                .create();

        NBT.modify(
                kitSelector,
                server -> {
                    server.setString("kitSelector", "kitSelector");
                }
        );

        val compass = new ItemCreator(Material.COMPASS)
                .display("§aWarp Selector")
                .addLore(
                        "§7Select your warp.",
                        "§7Right click to open the menu.")
                .create();


        NBT.modify(
                compass,
                server -> {
                    server.setString("warp", "warp");
                }
        );

        val shop = new ItemCreator(Material.BLAZE_ROD)
                .display("§aShop")
                .addLore(
                        "§7Buy items.",
                        "§7Right click to open the menu.")
                .create();


        NBT.modify(
                shop,
                server -> {
                    server.setString("shop", "shop");
                }
        );

        player.getInventory().setItem(4, kitSelector);
        player.getInventory().setItem(2, compass);
        player.getInventory().setItem(6, shop);
    }

    public void removeItem(Player player) {
    }
}
