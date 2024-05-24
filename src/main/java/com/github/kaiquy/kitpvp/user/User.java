package com.github.kaiquy.kitpvp.user;

import com.github.kaiquy.kitpvp.coins.Coins;
import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.kit.KitType;
import com.github.kaiquy.kitpvp.level.LevelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumSet;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class User implements IUser {

    private final String playerName;

    private Coins coins;
    private LevelType level;

    private int groupId;
    private List<GroupType> groupTypes;

    private int kills;
    private int deaths;

    private EnumSet<KitType> kitTypes;

    @Override
    public void addKills(int value) {
        this.kills += value;
    }

    @Override
    public void removeKills(int value) {
        this.kills -= value;
    }

    @Override
    public void addDeaths(int value) {
        this.deaths += value;
    }

    @Override
    public void removeDeaths(int value) {
        this.deaths -= value;
    }

    @Override
    public void addGroup(GroupType groupType) {
        this.groupTypes.add(groupType);
    }

    @Override
    public void removeGroup(GroupType groupType) {
        this.groupTypes.remove(groupType);
    }

    @Override
    public void addKit(KitType kitType) {
        this.kitTypes.add(kitType);
    }

    @Override
    public void removeKit(KitType kitType) {
        this.kitTypes.remove(kitType);
    }

    @Override
    public boolean hasKit(KitType kitType) {
        return kitTypes.contains(kitType);
    }
}
