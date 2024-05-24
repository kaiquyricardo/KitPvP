package com.github.kaiquy.kitpvp.view;

import com.github.kaiquy.kitpvp.misc.ItemCreator;
import com.github.kaiquy.kitpvp.warp.WarpsType;
import lombok.RequiredArgsConstructor;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class WarpView extends View {

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("Warp")
                .size(3)
                .layout(
                        "         ",
                        " A  B  C ",
                        "         ")
                .cancelOnClick()
                .cancelOnDrag()
                .cancelOnPickup()
                .cancelOnDrop();
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        render.layoutSlot('A', new ItemCreator(WarpsType.DEFAULT.getMaterial())
                .display(WarpsType.DEFAULT.getName())
                .addLore(WarpsType.DEFAULT.getLore())
                .create());

        render.layoutSlot('B', new ItemCreator(WarpsType.FPS.getMaterial())
                .display(WarpsType.FPS.getName())
                .addLore(WarpsType.FPS.getLore())
                .create());

        render.layoutSlot('C', new ItemCreator(WarpsType.LAVA.getMaterial())
                .display(WarpsType.LAVA.getName())
                .addLore(WarpsType.LAVA.getLore())
                .create());
    }
}
