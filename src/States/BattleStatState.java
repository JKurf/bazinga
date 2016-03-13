package src.States;

import src.Ent.Entity;
import src.Graphics.Renderer;
import src.Input;

import static org.lwjgl.glfw.GLFW.*;

public class BattleStatState implements IBattleState {
    Entity a;
    Entity b;

    String action;

    @Override
    public void Init(Entity A, Entity B) {
        a = A;
        b = B;
    }

    @Override
    public void Execute() {

    }

    @Override
    public void Update(double elapsedTime) {
        if(Input.keyPress(GLFW_KEY_B)) {
            action = "back";
        }
        else {
            action = null;
        }
    }

    @Override
    public void Render(Renderer graphics, int n) {
        int x = Renderer.WIDTH/2 - 128;
        int y = Renderer.HEIGHT/4;

        graphics.drawText(String.format("%s", a.name), x, y);
        graphics.drawText(String.format("Level: %d", a.level), x, y+=8);
        graphics.drawText(String.format("EXP:   %d", a.exp), x, y+=8);
        graphics.drawText(String.format("ATK:   %d", a.attack), x, y);

        x = Renderer.WIDTH/2 + 128;
        y = Renderer.HEIGHT/4;

        graphics.drawText(String.format("%s", b.name), x, y);
        graphics.drawText(String.format("Level: %d", b.level), x, y+=8);
        graphics.drawText(String.format("EXP:   %d", b.exp), x, y+=8);
        graphics.drawText(String.format("DEF:   %d", b.defense), x, y);
    }

    @Override
    public String check() {
        return action;
    }
}
