import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    boolean Initialized = false;
    String action = "none";

    Menu root;

    Mob e;
    Player p;

    public BattleState(Player P, Mob E) {
        System.out.println("Battle Begun");
        e = E;
        p = P;

        root = new Menu();

        root.setType(1);
        root.Add("attack");
        root.Add("action");
        root.Add("surrender");
        root.Add("options");
    }

    @Override
    public void Init() {
        if(!Initialized) {
            Initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.keyPress(GLFW_KEY_W)) {
            root.active--;
        }
        else if(InputClass.keyPress(GLFW_KEY_S)) {
            root.active++;
        }
        else if(InputClass.keyPress(GLFW_KEY_D)) {
            action = root.items[root.active].contents;
        }
        else
            action = null;


        if(root.active < 0) root.active = 3;
        if(root.active > 3) root.active = 0;

        if(action != null) {
            if (action.equals("attack")) {
                e.damage(10);
            }
        }

        if(e.getHealth() <= 0) {
            action = "resume";
            System.out.println("dead");
            e.alive = false;
            p.gainExp(e.level / 20);
        }
    }

    private void attack(Entity p1, Entity p2) {
    }

    @Override
    public void Render(GraphicsClass graphics) {
        //graphics.drawTextMenu("press f to pay respects!", GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/4, false);
        graphics.drawEntityScreen(p, 0, 0, 4.0f);
        graphics.drawEntityScreen(e, GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*graphics.zoom*4.0f, 0, 4.0f);
        graphics.drawText(String.format("%d HP", p.health), 0, 64);
        graphics.drawText(String.format("%d HP", e.health), GraphicsClass.WIDTH - 128, 64);

        graphics.drawText(p.name, 128, 0);
        graphics.drawText("why", 0, 64+8);
        graphics.drawText(e.name, GraphicsClass.WIDTH - 128 - e.name.length()*GraphicsClass.zoom*8.0f, 0);

        root.Render(graphics);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        if(action != null) {
            return action;
        }

        return null;
    }
}
