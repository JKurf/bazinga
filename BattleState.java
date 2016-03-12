import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    boolean Initialized = false;
    String action = "none";

    BattleStack battleStack;

    Menu root;

    //Entity E1,E2;
    Entity[] E;
    boolean current = false;

    public BattleState(Player p, Mob e) {
        System.out.println("Battle Begun");

        //E1 = p;
        //E2 = e;

        E = new Entity[2];
        E[0] = p;
        E[1] = e;

        E[0].currentAnimation = Animation.RIGHT;
        E[1].currentAnimation = Animation.LEFT;

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
        //Audio.play(Audio.KAZOO);
    }

    @Override
    public void Update(double elapsedTime) {
                String battleAction = battleStack.Update(elapsedTime);
        if(battleAction != null) {
            if(battleAction.equals("back")) {
                battleStack.Pop();
            }
        }

        E[0].Animations[E[0].currentAnimation].Update(elapsedTime);
        E[1].Animations[E[1].currentAnimation].Update(elapsedTime);
        action = null;

        if(current) {
            System.out.println('\n' + E[1].name + "'s turn");
            current = !current;
            System.out.println('\n' + E[0].name + "'s turn");
        }
        else {
            if(InputClass.keyPress(GLFW_KEY_SPACE)) {
                E[0].levelUp();
            }
            else if (InputClass.keyPress(GLFW_KEY_W)) {
                root.active--;
            } else if (InputClass.keyPress(GLFW_KEY_S)) {
                root.active++;
            } else if (InputClass.keyPress(GLFW_KEY_D)) {
                action = root.items[root.active].contents;
            } else if (InputClass.keyPress(GLFW_KEY_I)) {
                if (battleStack.mStack.isEmpty()) {
                    BattleStatState info = new BattleStatState();
                    battleStack.Push(info, E[0], E[1]);
                }
            }
        }


        if(root.active < 0) root.active = 3;
        if(root.active > 3) root.active = 0;

        if(action != null) {
            if (action.equals("attack")) {
                AttackState atk = new AttackState();
                //battleStack.Push(atk, E[(current) ? 1 : 0], E[(current) ? 0 : 1]);
                battleStack.Push(atk, E[0], E[1]);
                battleStack.Pop();
                current = !current;
            }
            if (action.equals("execute")) {
                while(!battleStack.mStack.empty())
                    battleStack.Pop();
            }
        }

        if(E[1].health <= 0) {
            action = "resume";
            System.out.println(E[1].name + " was killed by " + E[0].name);
            E[1].alive = false;
            E[0].gainExp(E[1]);
            //Audio.end();
            //E1.gainExp(E2.level / 20);
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        float Z = 4.0f;
        graphics.drawEntityScreen(E[0], 0, 0, Z);
        graphics.drawEntityScreen(E[1], GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*GraphicsClass.zoom*Z, 0, Z);

        graphics.drawText(String.format("%d HP", E[0].health), 0, GraphicsClass.TILE_HEIGHT*GraphicsClass.zoom*Z/2.0f);
        graphics.drawText(String.format("%d HP", E[1].health), GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*GraphicsClass.zoom*Z, GraphicsClass.TILE_HEIGHT*GraphicsClass.zoom*Z/2.0f);

        graphics.drawText(E[0].name, GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom, 0);
        graphics.drawText(E[1].name, GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom - E[1].name.length() * 16.0f, 0);

        graphics.drawText(String.format("Lvl: %d", E[0].level), GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom, 8.0f);
        graphics.drawText(String.format("Lvl: %d", E[1].level), GraphicsClass.WIDTH - GraphicsClass.TILE_WIDTH*Z*GraphicsClass.zoom - E[1].name.length() * 16.0f, 8.0f);

        battleStack.Render(graphics);

        root.Render(graphics);
    }

    @Override
    public void OnEnter(String[] params) {
    }

    @Override
    public void OnExit() {
        //Audio.end();
    }

    @Override
    public String check() {
        if(action != null) {
            return action;
        }

        return null;
    }
}
