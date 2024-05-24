package com.github.kaiquy.kitpvp;

import com.github.kaiquy.kitpvp.commands.*;
import com.github.kaiquy.kitpvp.kit.KitFactory;
import com.github.kaiquy.kitpvp.kit.listener.AnchorListener;
import com.github.kaiquy.kitpvp.kit.listener.ArcherListener;
import com.github.kaiquy.kitpvp.kit.listener.BerserkerListener;
import com.github.kaiquy.kitpvp.kit.listener.BoxerListener;
import com.github.kaiquy.kitpvp.scoreboard.ScoreboardFactory;
import com.github.kaiquy.kitpvp.user.UserCache;
import com.github.kaiquy.kitpvp.listener.GeralListener;
import com.github.kaiquy.kitpvp.view.KitView;
import com.github.kaiquy.kitpvp.view.RecaftView;
import com.github.kaiquy.kitpvp.view.SoupsView;
import com.github.kaiquy.kitpvp.view.WarpView;
import com.github.kaiquy.kitpvp.warp.WarpCache;
import lombok.Getter;
import lombok.val;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewFrame;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class KitPvPPlugin extends JavaPlugin {

    private ViewFrame viewFrame;
    private KitFactory kitFactory;
    private WarpCache warpCache;
    private UserCache userCache;

    public void onEnable() {
        viewFrame = ViewFrame.create(this);
        warpCache = new WarpCache();
        userCache = new UserCache();
        kitFactory = new KitFactory(userCache);

        registerCommands(
                new GameModeCommand(),
                new LevelCommand(),
                new TagCommand(userCache),
                new WarpCommand(warpCache),
                new SpawnCommand(warpCache)
        );

        registerView(
                new RecaftView(),
                new SoupsView(),
                new WarpView(),
                new KitView()
        );

        registerListeners(
                new GeralListener(this, userCache),
                new AnchorListener(this, userCache),
                new ArcherListener(userCache),
                new BerserkerListener(userCache),
                new BoxerListener(userCache)
        );

        ScoreboardFactory.start(this, userCache);

        Bukkit.getWorlds().forEach(world -> {
            world.setTime(6_000L);
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("doDaylightCycle", "false");
        });
    }


    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public void registerCommands(Object... commands) {
        val frame = new BukkitFrame(this);
        frame.registerCommands(commands);
    }

    public void registerView(View... views) {
        viewFrame.with(views).register();
    }
}