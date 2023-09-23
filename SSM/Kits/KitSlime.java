package SSM.Kits;

import SSM.Abilities.SlimeSlam;
import SSM.Attributes.DoubleJumps.GenericDoubleJump;
import SSM.Attributes.Regeneration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class KitSlime extends Kit {

    public KitSlime() {
        super();
        this.damage = 6;
        this.armor = 4;
        this.regeneration = 0.35;
        this.knockback = 1.75;
        this.name = "Slime";
        this.menuItem = Material.SLIME_BALL;
    }

    public void equipKit(Player player) {
        super.equipKit(player);

        setArmor(Material.CHAINMAIL_BOOTS, 0);
        setArmor(Material.CHAINMAIL_CHESTPLATE, 2);
        setArmor(Material.CHAINMAIL_HELMET, 3);

        setItem(Material.IRON_SWORD, 0, null);
        setItem(Material.IRON_AXE, 1, new SlimeSlam());

        addAttribute(new Regeneration(regeneration, 1));
        addAttribute(new GenericDoubleJump(1.2, 1.0, 1, Sound.GHAST_FIREBALL));

    }

}
