package com.github.kaiquy.kitpvp.scoreboard;

import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.level.LevelType;
import com.github.kaiquy.kitpvp.user.UserCache;
import fr.mrmicky.fastboard.FastBoard;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class ScoreboardFactory {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();


    public static void start(Plugin plugin, UserCache userCache) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board, userCache);
            }
        }, 2L, 2L);
    }

    private static void updateBoard(FastBoard board, UserCache userCache) {
        val player = board.getPlayer();
        val user = userCache.getByPlayer(player.getName());

        if (user == null) {
            board.delete();
            boards.remove(board.getPlayer().getUniqueId());
            return;
        }

        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            board.updateTitle("§6§lKITPVP");
            board.updateLines(Arrays.asList(
                    "",
                    " §fGroup: " + GroupType.getColoredPrefix(user.getGroupId()),
                    " §fLevel: " + LevelType.getLevelColoredName(user.getKills()),
                    " §fCoin: §6" + user.getCoins().getBalance(),
                    "",
                    " §fKit: §cNenhum",
                    "",
                    " §fKills: §a" + user.getKills(),
                    " §fDeaths: §c" + user.getDeaths(),
                    "",
                    "§ecom.github.kaiquy")
            );
            return;
        }
        if (player.getWorld().getName().equalsIgnoreCase("lava")) {
            board.updateTitle("§6§lLAVA");
            board.updateLines(Arrays.asList(
                    "",
                    " §fGroup: " + GroupType.getColoredPrefix(user.getGroupId()),
                    " §fLevel: " + LevelType.getLevelColoredSymbol(user.getKills()),
                    "",
                    " §fCoin: §6" + user.getCoins().getBalance(),
                    "",
                    "§ecom.github.kaiquy")
            );
        }
    }
}
