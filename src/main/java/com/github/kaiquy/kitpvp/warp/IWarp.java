package com.github.kaiquy.kitpvp.warp;


import org.bukkit.entity.Player;

interface IWarp {

    void addWarp(String warp, Player player);

    void removeWarp(String warp, Player player);

    void teleportToWarp(String warp, Player player);


}
