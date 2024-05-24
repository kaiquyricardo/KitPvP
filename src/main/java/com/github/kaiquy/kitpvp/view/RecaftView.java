package com.github.kaiquy.kitpvp.view;

import com.github.kaiquy.kitpvp.misc.ItemCreator;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class RecaftView extends View {

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("Recraft")
                .size(4)
                .layout(
                        " ABC ADD ",
                        " ABC ADD ",
                        " ABC ADD ",
                        " ABC ADD "
                );
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        render.layoutSlot('A', new ItemCreator(Material.BOWL)
                .amount(64)
                .create());

        render.layoutSlot('B', new ItemCreator(Material.RED_MUSHROOM)
                .amount(64)
                .create());

        render.layoutSlot('C', new ItemCreator(Material.BROWN_MUSHROOM)
                .amount(64)
                .create());

        render.layoutSlot('D', new ItemCreator(Material.getMaterial(351))
                .durability((short) 3)
                .amount(64)
                .create());
    }
}
