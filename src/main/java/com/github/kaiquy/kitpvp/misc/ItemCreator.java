package com.github.kaiquy.kitpvp.misc;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class ItemCreator {

    private ItemStack itemStack;
    private ItemMeta meta;

    public ItemCreator(Material material) {
        itemStack = new ItemStack(material);
        meta = itemStack.getItemMeta();
    }

    public ItemCreator display(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemCreator amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator skullOwner(String owner) {
        val skull = (SkullMeta) meta;

        itemStack.setDurability((short) 3);
        skull.setOwner(owner);
        return this;
    }

    public ItemCreator durability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }


    public ItemCreator addLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }


    public ItemCreator addLore(List<String> lore) {
        meta.setLore(Lists.newArrayList(lore));
        return this;
    }

    public ItemStack create() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
