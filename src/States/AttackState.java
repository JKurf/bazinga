package src.States;

import src.Ent.Entity;
import src.Graphics.Renderer;

public class AttackState implements IBattleState{
    Entity a;
    Entity b;
    float timeLeft = 5.0f;
    String action;

    @Override
    public void Init(Entity A, Entity B) {
        a = A;
        b = B;
    }

    @Override
    public void Execute() {
        float dmg = a.getDamage();
        b.damage(dmg);
        System.out.println(a.name + " attacks " + b.name + " for " + dmg + " damage.");
    }

    @Override
    public void Update(double elapsedTime) {
        timeLeft -= elapsedTime;

        if(timeLeft <= 0.0f) {
            Execute();
            action = "execute";
        } else {
            action = null;
        }

        action = "execute";
    }

    @Override
    public void Render(Renderer graphics, int n) {
        //float x = Renderer.WIDTH/4.0f * 2.0f;
        //float y = Renderer.HEIGHT / 8.0f * 3.0f;
        //graphics.drawText("Attack Queued", x, y + 8*n);
    }

    @Override
    public String check() {
        if(action != null) {
            return action;
        } else {
            return null;
        }
    }
}
