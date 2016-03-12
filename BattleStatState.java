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
        int x = 0;
        int y = 0;
        graphics.drawText(String.format("%d", y), x, y+=8);
        graphics.drawText(String.format("%d", y), x, y+=8);
        graphics.drawText(String.format("%d", y), x, y+=8);
    }

    @Override
    public String check() {
        return action;
    }
}
