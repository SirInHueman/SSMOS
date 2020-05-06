package SSM.GameManagers;

import SSM.SSM;
import SSM.Kit;
import SSM.Utilities.DamageUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MeleeManager implements Listener {

    @EventHandler
    public void melee(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Kit playerKit = SSM.playerKit.get(e.getDamager().getUniqueId());
            e.setCancelled(true);
            DamageUtil.dealDamage((Player)e.getDamager(), (LivingEntity)e.getEntity(), playerKit.getMelee(), true, false);
        }
    }
}
