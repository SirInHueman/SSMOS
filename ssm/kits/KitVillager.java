package ssm.kits;

import ssm.abilities.CycleArts;
import ssm.abilities.SonicHurr;
import ssm.abilities.TradeScatter;
import ssm.attributes.Compass;
import ssm.attributes.doublejumps.GenericDoubleJump;
import ssm.attributes.Hunger;
import ssm.attributes.Regeneration;
import ssm.managers.DisguiseManager;
import ssm.managers.disguises.VillagerDisguise;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class KitVillager extends Kit {

    public KitVillager() {
        super();
        this.damage = 5.5;
        this.armor = 5;
        this.regeneration = 0.25;
        this.knockback = 1.45;
        this.name = "Villager";
        this.menuItem = Material.WHEAT;
        this.podium_mob_type = EntityType.VILLAGER;
    }

    @Override
    public void initializeKit() {
        setArmorSlot(Material.CHAINMAIL_BOOTS, 0);
        setArmorSlot(Material.CHAINMAIL_LEGGINGS, 1);
        setArmorSlot(Material.CHAINMAIL_CHESTPLATE, 2);

        setAbility(new SonicHurr(), 0);
        setAbility(new TradeScatter(), 1);
        setAbility(new CycleArts(), 2);

        addAttribute(new Regeneration(regeneration));
        addAttribute(new Hunger());
        addAttribute(new Compass());
        addAttribute(new GenericDoubleJump(1.1, 1.1, 1, Sound.GHAST_FIREBALL));

        DisguiseManager.addDisguise(owner, new VillagerDisguise(owner));
    }

    @Override
    public void setPreviewHotbar() {
        setItem(new ItemStack(Material.IRON_AXE), 0);
        setItem(new ItemStack(Material.IRON_HOE), 1);
        setItem(new ItemStack(Material.IRON_SPADE), 2);
        setItem(new ItemStack(Material.NETHER_STAR), 3);
    }

    @Override
    public void setGameHotbar() {
        setItem(new ItemStack(Material.IRON_AXE), 0);
        setItem(new ItemStack(Material.IRON_HOE), 1);
        setItem(new ItemStack(Material.IRON_SPADE), 2);
        setItem(Compass.COMPASS_ITEM, 3);
        setItem(new ItemStack(Material.INK_SACK, 1, (short)(15 - DyeColor.RED.getData())), 7);
    }

    @Override
    public Entity getNewPodiumMob(Location spawn_location) {
        Entity entity = super.getNewPodiumMob(spawn_location);
        if(entity instanceof Villager) {
            Villager villager = (Villager) entity;
            villager.setProfession(Villager.Profession.FARMER);
        }
        return entity;
    }

}
