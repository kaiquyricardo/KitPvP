package com.github.kaiquy.kitpvp.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupType {

    MASTER(0, "§6", "Master", "[Master] "),
    MANAGER(1, "§4", "Gerente", "[Gerente] "),
    ADMIN(2, "§c", "Admin", "[Admin] "),
    MOD(3, "§2", "Mod", "[Mod] "),
    HELPER(4, "§e", "Helper", "[Helper] "),
    BUILDER(5, "§3", "Builder", "[Builder] "),
    YOUTUBER(6, "§4", "Youtuber", "[Youtuber] "),
    MINI_YOUTUBER(7, "§d", "MiniYT", "[MiniYT] "),
    MVP_PLUS(8, "§9", "MVP+", "[MVP+] "),
    MVP(9, "§b", "MVP", "[MVP] "),
    VIP(10, "§a", "VIP", "[VIP] "),
    DEFAULT(11, "§7", "Membro", "");

    private final int index;
    private final String color;
    private final String prefix;
    private final String suffix;

    public static GroupType getGroupType(String name) {
        for (GroupType groupType : values()) {
            if (groupType.getPrefix().equalsIgnoreCase(name)) {
                return groupType;
            }
        }
        return null;
    }

    public static GroupType getGroupType(int index) {
        for (GroupType groupType : values()) {
            if (groupType.getIndex() == index) {
                return groupType;
            }
        }
        return null;
    }

    public static String getColoredPrefix(int index) {
        for (GroupType groupType : values()) {
            if (groupType.getIndex() == index) {
                return groupType.getColor() + groupType.getPrefix();
            }

        }
        return null;
    }
}
