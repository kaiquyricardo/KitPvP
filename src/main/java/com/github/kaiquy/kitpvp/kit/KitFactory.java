package com.github.kaiquy.kitpvp.kit;

    import com.github.kaiquy.kitpvp.user.UserCache;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class KitFactory {

    private final UserCache userCache;

    public void createKit(Player player, KitType kitType) {
        val user = userCache.getByPlayer(player.getName());

        switch (kitType) {
            case ANCHOR:
            case BERSERKER:
            case BOXER:
            case CAMEL:
            case CANNIBAL:
                removeKit(player);
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                addRecraft(player);
                user.getKitTypes().add(kitType);
                break;
            case ARCHER:
                removeKit(player);
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                player.getInventory().addItem(new ItemStack(Material.BOW));
                player.getInventory().addItem(new ItemStack(Material.ARROW, 10));
                addRecraft(player);
                user.getKitTypes().add(kitType);
                break;
            case C4:
                removeKit(player);
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                player.getInventory().addItem(new ItemStack(Material.SLIME_BALL));
                addRecraft(player);
                user.getKitTypes().add(kitType);
                break;
            default:
                break;
        }
    }

    private void addRecraft(Player player) {
        for (int i = 0; i < 36; i++) {
            player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
        }
        player.getInventory().setItem(13, new ItemStack(Material.RED_MUSHROOM, 64));
        player.getInventory().setItem(14, new ItemStack(Material.BOWL, 64));
        player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64));
    }

    public void removeKit(Player player) {
        player.setHealthScale(20d);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }
}
