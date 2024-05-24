package com.github.kaiquy.kitpvp.view;

import com.github.kaiquy.kitpvp.misc.ItemCreator;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class SoupsView extends View {
    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("Soups")
                .size(4)
                .layout(
                        "AAAAAAAAA",
                        "AAAAAAAAA",
                        "AAAAAAAAA",
                        "AAAAAAAAA"
                );
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {

        render.layoutSlot('A', new ItemCreator(Material.MUSHROOM_SOUP)
                .create());
    }
}