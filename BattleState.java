import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    boolean Initialized = false;
    String action = "none";

    BattleStack battleStack;

    Menu root;

    Mob e;
    Player p;

    public BattleState(Player P, Mob E) {
        System.out.println("Battle Begun");
        e = E;
        p = P;

        p.currentAnimation = Animation.RIGHT;
        e.currentAnimation = Animation.LEFT;

        root = new Menu();

        root.setType(1);
        root.Add("attack");
        root.Add("action");
        root.Add("surrender");
        root.Add("execute");

        battleStack = new BattleStack();
    }

    @Override
    public void Init() {
        if(!Initialized) {
            Initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {

        String battleAction = battleStack.Update(elapsedTime);

        e.Animations[e.currentAnimation].Update(elapsedTime);
        p.Animations[p.currentAnimation].Update(elapsedTime);

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
                AttackState atk = new AttackState();
                battleStack.Push(atk, p, e);
            }
            if (action.equals("execute")) {
                while(!battleStack.mStack.empty())
                    battleStack.Pop();
            }
        }

        if(e.getHealth() <= 0) {
            action = "resume";
            System.out.println("dead");
            e.alive = false;
            p.gainExp(e.level / 20);
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawEntityScreen(p, 0, 0, 4.0f);
        graphics.drawEntityScreen(e, GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*GraphicsClass.zoom*4.0f, 0, 4.0f);
        graphics.drawText(String.format("%d HP", p.health), 0, 64);
        graphics.drawText(String.format("%d HP", e.health), GraphicsClass.WIDTH - 128, 64);

        graphics.drawText(p.name, 128, 0);
        graphics.drawText("why", 0, 64+8);
        graphics.drawText(e.name, GraphicsClass.WIDTH - 128 - e.name.length()*GraphicsClass.zoom*8.0f, 0);

        battleStack.Render(graphics);

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
