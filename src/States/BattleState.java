package src.States;

import src.Ent.Animation;
import src.Ent.Entity;
import src.Graphics.Renderer;
import src.Input;

import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    private boolean Initialized = false;
    public String action = "none";

    public BattleStack battleStack;

    private Menu root;

    private Entity[] E;
    private boolean current = false;
    private boolean swap = true;

    public BattleState(Entity e1, Entity e2) {
        E = new Entity[2];
        E[0] = e1;
        E[1] = e2;

        E[0].currentAnimation = Animation.RIGHT;
        E[1].currentAnimation = Animation.LEFT;

        root = new Menu();

        root.setType(1);
        root.Add("attack");
        root.Add("info");

        battleStack = new BattleStack();
    }

    @Override
    public void Init() {
        if(!Initialized) {
            Initialized = true;
            battleStack.Pop();
        }
        //Audio.play(Audio.KAZOO);
    }

    @Override
    public void Update(double elapsedTime) {
        Entity E1 = E[( current) ? 1 : 0];
        Entity E2 = E[(!current) ? 1 : 0];

        E1.Animations[E1.currentAnimation].Update(elapsedTime);
        E2.Animations[E2.currentAnimation].Update(elapsedTime);
        action = null;

        String battleAction = battleStack.Update(elapsedTime);
        if(battleAction == null) {
            battleAction = "-";
        }
        if(battleAction.equals("back")) {
            battleStack.Pop();
        }
        if (battleAction.equals("execute")) {
            while(!battleStack.mStack.empty())
                battleStack.Pop();
        }

        if(E1.player) {
            if(swap) {
                System.out.println(E1.name + "'s Turn");
                swap = false;
            }

            if (Input.keyPress(GLFW_KEY_W)) {
                root.active--;
            } else if (Input.keyPress(GLFW_KEY_S)) {
                root.active++;
            } else if (Input.keyPress(GLFW_KEY_D)) {
                action = root.items[root.active].contents;
            }

            if(root.active < 0) root.active = root.n-1;
            if(root.active > root.n-1) root.active = 0;
        } else {
            if(swap) {
                System.out.println(E1.name + "'s Turn");
                swap = false;
            }

            AttackState atk = new AttackState();
            battleStack.Push(atk, E1, E2);
            battleStack.Pop();

            current = !current;
            swap = true;
        }

        if(action != null) {
            if (action.equals("attack")) {
                if (battleStack.mStack.isEmpty()) {
                    AttackState atk = new AttackState();
                    battleStack.Push(atk, E1, E2);
                    //battleStack.Pop();
                    current = !current;
                    swap = true;
                }
            }
            if (action.equals("info")) {
                if (battleStack.mStack.isEmpty()) {
                    BattleStatState info = new BattleStatState();
                    battleStack.Push(info, E1, E2);
                }
            }
        }

        if(E2.health <= 0) {
            action = "resume";
            System.out.println(E2.name + " was killed by " + E1.name);
            E2.alive = false;
            E1.gainExp(E2);
        }
        if(E1.health <= 0) {
            action = "resume";
            System.out.println(E1.name + " was killed by " + E2.name);
            E1.alive = false;
            E2.gainExp(E2);
        }
    }

    @Override
    public void Render(Renderer graphics) {
        float Z = 4.0f;
        graphics.drawEntityScreen(E[0], 0, 0, Z);
        graphics.drawEntityScreen(E[1], Renderer.WIDTH - Renderer.TILE_WIDTH* Renderer.zoom*Z, 0, Z);

        graphics.drawText(String.format("%d HP", E[0].health), 0, Renderer.TILE_HEIGHT* Renderer.zoom*Z);
        graphics.drawText(String.format("%d HP", E[1].health), Renderer.WIDTH - Renderer.TILE_WIDTH* Renderer.zoom*Z, Renderer.TILE_HEIGHT* Renderer.zoom*Z);

        graphics.drawText(E[0].name, Renderer.TILE_WIDTH*Z* Renderer.zoom, 0);
        graphics.drawText(E[1].name, Renderer.WIDTH - Renderer.TILE_WIDTH*Z* Renderer.zoom - E[1].name.length() * 16.0f, 0);

        graphics.drawText(String.format("Lvl: %d", E[0].level), Renderer.TILE_WIDTH*Z* Renderer.zoom, 16.0f);
        graphics.drawText(String.format("Lvl: %d", E[1].level), Renderer.WIDTH - Renderer.TILE_WIDTH*Z* Renderer.zoom - E[1].name.length() * 16.0f, 16.0f);

        int x = Renderer.WIDTH / 2;
        int y = Renderer.HEIGHT / 2;

        graphics.drawText(String.format("VIT:  %d %d", E[0].vitality,   E[1].vitality), x, y);
        graphics.drawText(String.format("ATK:  %d %d", E[0].attack,     E[1].attack), x, y+=16);
        graphics.drawText(String.format("DEF:  %d %d", E[0].defense,    E[1].defense), x, y+=16);
        graphics.drawText(String.format("SKL:  %d %d", E[0].skill,      E[1].skill), x, y+=16);

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
