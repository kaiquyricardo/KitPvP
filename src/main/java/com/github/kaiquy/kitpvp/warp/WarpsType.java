package com.github.kaiquy.kitpvp.warp;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum WarpsType {

    FPS(Material.GLASS, "§aFPS", Lists.newArrayList("§7Teleport to the FPS arena.")),
    LAVA(Material.LAVA_BUCKET, "§cLAVA", Lists.newArrayList("§7Teleport to the Lava arena.")),
    DEFAULT(Material.BED, "§eSPAWN", Lists.newArrayList("§7Teleport to the spawn location.")),;

    private final Material material;
    private final String name;
    private final List<String> lore;

}
