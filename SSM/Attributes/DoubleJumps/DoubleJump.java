package SSM.Attributes.DoubleJumps;

import SSM.Attributes.Attribute;
import SSM.Events.SmashDamageEvent;
import SSM.GameManagers.GameManager;
import SSM.GameManagers.KitManager;
import SSM.Kits.Kit;
import SSM.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public abstract class DoubleJump extends Attribute {

    protected double height;
    protected double power;
    private int maxDoubleJumps;
    private int remainingDoubleJumps = 0;
    protected Sound doubleJumpSound;

    public DoubleJump(double power, double height, int maxDoubleJumps, Sound doubleJumpSound) {
        super();
        this.power = power;
        this.height = height;
        this.maxDoubleJumps = maxDoubleJumps;
        this.doubleJumpSound = doubleJumpSound;
        task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                Kit kit = KitManager.getPlayerKit(owner);
                if(kit == null || !kit.getGameHotbarEquipped()) {
                    owner.setAllowFlight(false);
                    owner.setFlying(false);
                    return;
                }
                if (Utils.entityIsOnGround(owner)) {
                    resetDoubleJumps();
                }
            }
        }, 0L, 0L);
    }

    /**
     * Ensure the player is able to double jump and pass jump method.
     */
    @EventHandler
    public void playerFlightEvent(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (!player.equals(owner)) {
            return;
        }

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        e.setCancelled(true);
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setFallDistance(0);

        if (remainingDoubleJumps <= 0) {
            Bukkit.broadcastMessage("Remaining jumps were 0 even though you toggled flight... what?");
        }

        if (remainingDoubleJumps > 0) {
            remainingDoubleJumps--;

            playDoubleJumpSound();

            jump();

            if (remainingDoubleJumps <= 0) {
                player.setAllowFlight(false);
            }
        }
    }

    public void playDoubleJumpSound() {
        owner.getWorld().playSound(owner.getLocation(), doubleJumpSound, 1f, 1f);
    }

    public void resetDoubleJumps() {
        remainingDoubleJumps = maxDoubleJumps;
        owner.setAllowFlight(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void smashDamageEvent(SmashDamageEvent e) {
        if (e.getDamageCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    public void activate() {
        jump();
    }

    protected abstract void jump();

    @Override
    public void setOwner(Player owner) {
        super.setOwner(owner);
    }

}
