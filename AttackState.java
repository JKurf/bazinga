/**
 * Created by graf on 3/7/2016.
 */
public class AttackState implements IBattleState{
    Entity a;
    Entity b;

    @Override
    public void Init(Entity A, Entity B) {
        a = A;
        b = B;
        System.out.println("Attack queued");
    }

    @Override
    public void Execute() {
        int dmg = a.getDamage();
        b.damage(dmg);
        System.out.println(a.name + " attacks " + b.name + " for " + dmg + " damage.");
        double dmgT = Math.ceil(((float)dmg / (float)b.defense));
        System.out.println("Base Damage: " + dmg + "   Damage Taken: " + (dmgT));
    }

    @Override
    public void Update(double elapsedTime) {

    }

    @Override
    public void Render(GraphicsClass graphics, int n) {
        float x = GraphicsClass.WIDTH/4.0f * 2.0f;
        float y = GraphicsClass.HEIGHT / 8.0f * 3.0f;
        graphics.drawText("Attack Queued", x, y + 8*n);
    }

    @Override
    public String check() {
        return null;
    }
}
