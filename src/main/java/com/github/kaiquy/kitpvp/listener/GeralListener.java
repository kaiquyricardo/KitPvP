package com.github.kaiquy.kitpvp.listener;

import com.github.kaiquy.kitpvp.KitPvPPlugin;
import com.github.kaiquy.kitpvp.group.GroupType;
import com.github.kaiquy.kitpvp.item.Item;
import com.github.kaiquy.kitpvp.level.LevelType;
import com.github.kaiquy.kitpvp.misc.Helper;
import com.github.kaiquy.kitpvp.user.UserCache;
import com.github.kaiquy.kitpvp.view.KitView;
import com.github.kaiquy.kitpvp.view.RecaftView;
import com.github.kaiquy.kitpvp.view.SoupsView;
import com.github.kaiquy.kitpvp.view.WarpView;
import com.google.common.collect.ImmutableMap;
import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.mrmicky.fastboard.FastBoard;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import static com.github.kaiquy.kitpvp.scoreboard.ScoreboardFactory.boards;

@RequiredArgsConstructor
public class GeralListener implements Listener {

    private final KitPvPPlugin plugin;
    private final UserCache userCache;

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        val player = event.getPlayer();

        userCache.addUser(player.getName(),
                LevelType.ROOKIE,
                0,
                0,
                0
        );

        new Item().addItem(player);

        val board = new FastBoard(player);
        boards.put(player.getUniqueId(), board);

        event.setJoinMessage(null);
    }

    @EventHandler
    void onQuit(PlayerQuitEvent event) {
        val player = event.getPlayer();

        event.setQuitMessage(null);

        new Item().removeItem(player);
        val board = boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }


    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        val player = event.getPlayer();

        val itemInHand = player.getItemInHand();
        if (itemInHand == null) return;
        if (itemInHand.getType() == null) return;
        if (itemInHand.getType() == Material.AIR) return;

        val nbt = new NBTItem(itemInHand);
        if (nbt.hasTag("kitSelector")) {
            plugin.getViewFrame().open(
                    KitView.class,
                    player,
                    ImmutableMap.of("kitFactory", plugin.getKitFactory())
            );
            event.setCancelled(true);
            return;
        }
        if (nbt.hasTag("warp")) {
            plugin.getViewFrame().open(WarpView.class, player);
            event.setCancelled(true);
            return;
        }
        if (nbt.hasTag("shop")) {
            player.sendMessage("Open shop");
            event.setCancelled(true);
            return;
        }

        val action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR) {
            if (itemInHand.getType() == Material.MUSHROOM_SOUP) {
                val damageable = (Damageable) player;
                if (damageable.getHealth() < 20) {
                    damageable.setHealth(damageable.getHealth() + 7 > 20 ? damageable.getMaxHealth() :
                            damageable.getHealth() + 7);
                    itemInHand.setType(Material.BOWL);
                }
            }
        }
    }


    @EventHandler
    void onInventory(InventoryClickEvent event) {
        val player = event.getWhoClicked();
        val item = event.getCurrentItem();

        if (item == null) return;
        if (item.getType() == Material.AIR) return;

        if (player instanceof Player) {
            val nbt = new NBTItem(item);

            if (nbt.hasTag("kitSelector")) {
                event.setCancelled(true);
                return;
            }
            if (nbt.hasTag("warp")) {
                event.setCancelled(true);
                return;
            }
            if (nbt.hasTag("shop")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onBlockPlace(BlockPlaceEvent event) {
        val player = event.getPlayer();

        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    void onDeath(PlayerDeathEvent event) {
        val player = (Player) event.getEntity();
        val user = userCache.getByPlayer(player.getName());

        val killer = player.getKiller();
        val killerUser = userCache.getByPlayer(killer.getName());

        if (killerUser.getLevel().getRequiredKills() == killerUser.getKills()) {
            killerUser.setLevel(LevelType.values()[killerUser.getLevel().ordinal() + 1]);
            return;
        }

        killerUser.addKills(1);
        killer.sendMessage(new String[]{
                "§aVocê matou " + player.getName() + "§a!",
                "§6+8 coins.",
                "§b-14 XP."
        });

        user.addDeaths(1);
        player.sendMessage(
                new String[]{
                        "§cVocê foi morto por " + killer.getName() + "§c!",
                        "§4-5 XP."
                }
        );

    }

    @EventHandler
    void onAsync(AsyncPlayerChatEvent event) {
        val player = event.getPlayer();
        val user = userCache.getByPlayer(player.getName());

        val group = user.getGroupId();
        val groupType = GroupType.getGroupType(group);

        if (groupType == null) return;

        val groupTag = groupType.getColor() + groupType.getSuffix();

        event.setFormat(LevelType.getLevelColoredSymbol(user.getKills()) +
                " " +
                groupTag +
                player.getName() + "§f: " +
                Helper.color(event.getMessage())
        );
    }

    @EventHandler
    void onSign(SignChangeEvent event) {
        val lines = event.getLines();

        if (lines[0].equalsIgnoreCase("[SOUPS]")) {
            event.setLine(0, "§c═§e═§6═§a═§b═");
            event.setLine(1, "§6§lKIT PVP");
            event.setLine(2, "§7§lSOUPS");
            event.setLine(3, "§c═§e═§6═§a═§b═");
        }

        if (lines[0].equalsIgnoreCase("[RECRAFT]")) {
            event.setLine(0, "§c═§e═§6═§a═§b═");
            event.setLine(1, "§6§lKIT PVP");
            event.setLine(2, "§7§lRECRAFT");
            event.setLine(3, "§c═§e═§6═§a═§b═");
        }
    }

    @EventHandler
    void onInteractSign(PlayerInteractEvent event) {
        val player = event.getPlayer();
        val clickedBlock = event.getClickedBlock();

        if (clickedBlock == null) return;
        if (clickedBlock.getType() == null) return;

        if (clickedBlock.getType() == Material.WALL_SIGN || clickedBlock.getType() == Material.SIGN_POST) {
            val sign = (Sign) clickedBlock.getState();
            val lines = sign.getLine(2);

            if (lines.equalsIgnoreCase("§7§lSOUPS")) {
                plugin.getViewFrame().open(SoupsView.class, player);
                event.setCancelled(true);
                return;
            }

            if (lines.equalsIgnoreCase("§7§lRECRAFT")) {
                plugin.getViewFrame().open(RecaftView.class, player);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onDrop(ItemSpawnEvent event) {
        val item = event.getEntity();
        Bukkit.getScheduler().runTaskLater(plugin, item::remove, 500);
    }

    @EventHandler
    void onFoodLevel(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
