import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by graf on 3/11/2016.
 */
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
        if(InputClass.keyPress(GLFW_KEY_B)) {
            action = "back";
        }
        else {
            action = null;
        }
    }

    @Override
    public void Render(GraphicsClass graphics, int n) {
        int x = GraphicsClass.WIDTH/2 - 128;
        int y = GraphicsClass.HEIGHT/4;

        graphics.drawText(String.format("%s", a.name), x, y);
        graphics.drawText(String.format("Level: %d", a.level), x, y+=8);
        graphics.drawText(String.format("EXP:   %d", a.exp), x, y+=8);
        graphics.drawText(String.format("ATK:   %d", a.attack), x, y+=8);

        x = GraphicsClass.WIDTH/2 + 128;
        y = GraphicsClass.HEIGHT/4;

        graphics.drawText(String.format("%s", b.name), x, y);
        graphics.drawText(String.format("Level: %d", b.level), x, y+=8);
        graphics.drawText(String.format("EXP:   %d", b.exp), x, y+=8);
        graphics.drawText(String.format("DEF:   %d", b.defense), x, y+=8);
    }

    @Override
    public String check() {
        return action;
    }
}
