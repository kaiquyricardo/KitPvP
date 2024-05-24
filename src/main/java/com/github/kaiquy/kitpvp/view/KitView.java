package com.github.kaiquy.kitpvp.view;

import com.github.kaiquy.kitpvp.kit.KitFactory;
import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.misc.ItemCreator;
import lombok.val;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.component.Pagination;
import me.devnatan.inventoryframework.context.RenderContext;
import me.devnatan.inventoryframework.state.State;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class KitView extends View {

    private final State<KitFactory> kitFactoryState = initialState("kitFactory");

    private final State<Pagination> paginationState = lazyPaginationState(
            (context) -> getKits(),
            (context, item, index, kit) -> {
                item.withItem(new ItemCreator(kit.getMaterial())
                        .display(kit.name())
                        .create()
                ).onClick(click -> {
                    val player = click.getPlayer();
                    val kitFactory = (KitFactory) kitFactoryState.get(click);

                    kitFactory.createKit(player, kit);
                    player.sendMessage("§aKit " + kit.name() + " selecionado com sucesso!");
                    click.closeForPlayer();
                });
            }
    );

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("Kits")
                .size(6)
                .layout(
                        "         ",
                        " OOOOOOO ",
                        " OOOOOOO ",
                        " OOOOOOO ",
                        " OOOOOOO ",
                        "         "
                )
                .cancelOnDrop()
                .cancelOnPickup()
                .cancelOnDrag()
                .cancelOnClick();
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
    }

    private List<KitType> getKits() {
        return Arrays.asList(KitType.values());
    }
}
