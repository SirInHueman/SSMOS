package SSM.Attributes;

public class Regeneration extends Attribute {

    Double regen;
    double delay;

    public Regeneration(Double regen, double delay) {
        super();
        this.name = "Regeneration";
        this.regen = regen;
        this.delay = delay;
        task = this.runTaskTimer(plugin, 0, (long) delay * 20);
    }

    @Override
    public void run() {
        checkAndActivate();
    }

    public void activate() {
        if (!owner.isDead()) {
            owner.setHealth(Math.min(owner.getHealth() + regen, 20));
        }
    }

}
