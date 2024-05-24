package com.github.kaiquy.kitpvp.level;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelType {

    ROOKIE("§8", "Rookie", "‐", 0),
    TRAINEE("§a", "Trainee", "⋆", 10),
    ROGUE("§2", "Rogue", "✧", 25),
    KNIGHT("§7", "Knight", "✠", 50),
    VETERAN("§9", "Veteran", "✳", 100),
    ELITE("§3", "Elite", "✷", 200),
    HERO("§6", "Hero", "✺", 400),
    MASTER("§d", "Master", "✮", 500),
    GRANDMASTER("§5", "GrandMaster", "❁", 600),
    IMMORTAL("§c", "Immortal", "✵", 700),
    OVERLORD("§0", "Overlord", "✪", 800),
    DIVINITY("§f", "Divinity", "۞", 900),
    PHOENIX("§4", "Phoenix", "❃", 1000);

    private final String color;
    private final String name;
    private final String symbol;
    private final int requiredKills;

    public static String getLevelColoredName(int kills) {
        for (LevelType level : values()) {
            if (kills <= level.getRequiredKills()) {
                return level.getColor() + level.getName();
            }
        }
        return null;
    }

    public static String getLevelColoredSymbol(int kills) {
        for (LevelType level : values()) {
            if (kills <= level.getRequiredKills()) {
                return level.getColor() + level.getSymbol();
            }
        }
        return null;
    }
}
