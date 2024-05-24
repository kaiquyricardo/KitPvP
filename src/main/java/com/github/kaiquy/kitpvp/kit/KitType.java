package com.github.kaiquy.kitpvp.kit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public enum KitType {

    ANCHOR(Material.ANVIL),
    ARCHER(Material.BOW),
    BERSERKER(Material.POTION),
    BOXER(Material.STONE_SWORD),
    C4(Material.SLIME_BALL),
    CAMEL(Material.DIRT),
    CANNIBAL(Material.ROTTEN_FLESH),
    CHECKPOINT(Material.BED),
    COOKIEMONSTER(Material.COOKIE),
    FPS(Material.GLASS),
    FIREMAN(Material.LAVA_BUCKET),
    FISHERMAN(Material.FISHING_ROD),
    FORCEFIELD(Material.BARRIER),
    GLADIATOR(Material.IRON_BARDING),
    GRANDPA(Material.WATCH),
    GRAPPLER(Material.LEASH),
    HULK(Material.DIAMOND_CHESTPLATE),
    KANGAROO(Material.FIREWORK),
    THOR(Material.SPIDER_EYE),
    VIPER(Material.POISONOUS_POTATO),
    STOMPER(Material.IRON_BOOTS),
    PVP(Material.DIAMOND_SWORD),
    NINJA(Material.NETHER_STAR),
    MLG(Material.WATER_BUCKET);

    private final Material material;
}
