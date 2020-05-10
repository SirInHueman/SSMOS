package SSM.Kits;

import SSM.*;
import SSM.Abilities.*;
import SSM.Attributes.DoubleJumps.GenericDoubleJump;
import SSM.Attributes.Regeneration;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class KitMagmaCube extends Kit {

    public KitMagmaCube() {
        super();

        this.damage = 5;
        this.armor = 4;
        this.speed = 0.22f;
        this.regeneration = 0.35;
        this.knockback = 0;
        this.disguise = DisguiseType.MAGMA_CUBE;
        this.name = "Magma_Cube";
        this.menuItem = Material.FIRE_CHARGE;
    }

    public void equipKit(Player player) {
        super.equipKit(player);

        setArmor(Material.CHAINMAIL_BOOTS, 0);
        setArmor(Material.CHAINMAIL_CHESTPLATE, 2);
        setArmor(Material.CHAINMAIL_HELMET, 3);

        setItem(Material.IRON_AXE, 0, new MagmaBlast());
        setItem(Material.IRON_SHOVEL, 1, new FlameDash());

        addAttribute(new Regeneration(regeneration, 1));
        addAttribute(new GenericDoubleJump(0.61, 1.0, 1, Sound.ENTITY_GHAST_SHOOT));
    }
}
