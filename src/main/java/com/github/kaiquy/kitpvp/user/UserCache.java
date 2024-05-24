package com.github.kaiquy.kitpvp.user;

import com.github.kaiquy.kitpvp.coins.Coins;
import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.level.LevelType;
import lombok.val;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserCache {

    public static final Map<String, User> USER = new LinkedHashMap<>();

    public User getByPlayer(String playerName) {
        return USER.get(playerName);
    }

    public void addUser(
            String playerName,
            LevelType levelType,
            double balance,
            int kills,
            int deaths
    ) {
        val groupType = GroupType.getGroupType(10);

        if (groupType == null) {
            throw new IllegalArgumentException("Invalid group type");
        }

        USER.put(playerName, new User(
                        playerName,
                        new Coins(balance),
                        levelType,
                        groupType.getIndex(),
                        new ArrayList<>(),
                        kills,
                        deaths,
                        EnumSet.noneOf(KitType.class)
                )
        );

        USER.get(playerName).getGroupTypes().add(GroupType.DEFAULT);
    }

    public void removeUser(String playerName) {
        USER.remove(playerName);
    }
}
