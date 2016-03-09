import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    boolean Initialized = false;
    String action = "none";

    BattleStack battleStack;

    boolean who = true;

    Menu root;

    Entity E1,E2;

    public BattleState(Player P, Mob E) {
        System.out.println("Battle Begun");

        E1 = P;
        E2 = E;

        E1.currentAnimation = Animation.RIGHT;
        E2.currentAnimation = Animation.LEFT;

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
        Audio.play(Audio.KAZOO);
    }

    @Override
    public void Update(double elapsedTime) {

        if (Audio.isOver()) Audio.play(Audio.KAZOO);

        String battleAction = battleStack.Update(elapsedTime);

        E2.Animations[E2.currentAnimation].Update(elapsedTime);
        E1.Animations[E1.currentAnimation].Update(elapsedTime);

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
                if(who) {
                    battleStack.Push(atk, E1, E2);
                    battleStack.Pop();
                    //who = !who;
                }
                else {
                    battleStack.Push(atk, E2, E1);
                    battleStack.Pop();
                    //who = !who;
                }
            }
            if (action.equals("execute")) {
                while(!battleStack.mStack.empty())
                    battleStack.Pop();
            }
        }

        if(E2.health <= 0) {
            action = "resume";
            System.out.println(E2.name + " was killed by " + E1.name);
            E2.alive = false;
            Audio.end();
            //E1.gainExp(E2.level / 20);
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        float Z = 4.0f;
        graphics.drawEntityScreen(E1, 0, 0, Z);
        graphics.drawEntityScreen(E2, GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*GraphicsClass.zoom*Z, 0, Z);

        graphics.drawText(String.format("%d HP", E1.health), 0, GraphicsClass.TILE_HEIGHT*GraphicsClass.zoom*Z/2.0f);
        graphics.drawText(String.format("%d HP", E2.health), GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*GraphicsClass.zoom*Z, GraphicsClass.TILE_HEIGHT*GraphicsClass.zoom*Z/2.0f);

        graphics.drawText(E1.name, GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom, 0);
        graphics.drawText(E2.name, GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom - E2.name.length() * 16.0f, 0);

        battleStack.Render(graphics);

        root.Render(graphics);
    }

    @Override
    public void OnEnter(String[] params) {
    }

    @Override
    public void OnExit() {
        Audio.end();
    }

    @Override
    public String check() {
        if(action != null) {
            return action;
        }

        return null;
    }
}
