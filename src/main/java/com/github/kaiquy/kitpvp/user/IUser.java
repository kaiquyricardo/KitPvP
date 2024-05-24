package com.github.kaiquy.kitpvp.user;

import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.kit.KitType;

interface IUser {

    void addKills(int value);

    void removeKills(int value);

    void addDeaths(int value);

    void removeDeaths(int value);

    void addGroup(GroupType groupType);

    void removeGroup(GroupType groupType);

    void addKit(KitType kitType);

    void removeKit(KitType kitType);

    boolean hasKit(KitType kitType);
}
