import javax.swing.*;

import java.util.List;
import java.util.Stack;

import static org.lwjgl.glfw.GLFW.*;

public class BattleState implements IState {
    boolean Initialized = false;
    String action = null;

    Mob e;
    Player p;

    public BattleState(Player P, Mob E) {
        //System.out.println(ID);
        System.out.println("Battling ");
        e = E;
        p = P;
        //System.out.println("Enemy Health: " + e.getHealth());
        System.out.println("Enemy Type: " + e.name);
        System.out.println("\tLevel: " + e.level);
        System.out.println("\tHealth: " + e.health);

        System.out.println("Player Type: "  + p.getSpec());
        System.out.println("\tLeveL: "      + p.getLevel());
        System.out.println("\tEXP left "    + p.getExpNeeded());
        System.out.println("\tHealth: "     + p.health);
    }

    @Override
    public void Init() {
        if(!Initialized) {


            Initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.keyPress(GLFW_KEY_F)) {
                e.damage(p.getLevel()*20);
                System.out.println("\n\n\n\n");
                System.out.println("Enemy Type: " + e.name);
                System.out.println("\tLevel: " + e.level);
                System.out.println("\tHealth: " + e.health);

                System.out.println("Player Type: "  + p.getSpec());
                System.out.println("\tLeveL: "      + p.getLevel());
                System.out.println("\tEXP left "    + p.getExpNeeded());
                System.out.println("\tHealth: "     + p.health);

                if(e.getHealth() <= 0) {
                    action = "resume";
                    System.out.println("dead");
                    e.alive = false;
                    //p.levelUp();
                    p.gainExp(e.level / 20);
            }
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawTextMenu("press f to pay respects!", GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/4, false);
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
