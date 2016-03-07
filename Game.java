//#bangpoundben

import static org.lwjgl.glfw.GLFW.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;

public class Game {
    GraphicsClass graphics = new GraphicsClass();

    StateStack SM = new StateStack();

    MenuState pause = new MenuState(new String[] {
            "resume",
            "settings",
            "change world",
            "quit"
    });

    MenuState settings = new MenuState(new String[] {
            "game",
            "video",
            "audio",
            "back"
    });

    MenuState mainMenu = new MenuState(new String[] {
            "start game",
            "quit"
    });
    
    WorldState[] worlds;
    int world = 0;
    int numWorlds = 0;

    public Game() {
    }

    public void Init() {
        graphics.Init();

        loadWorlds("WorldFiles/Worlds.json");

        SM.Push(mainMenu);
    }

    public void Update(double elapsedTime) {
        InputClass.Update(elapsedTime);
        if(InputClass.isKeyDown(GLFW_KEY_Q) || InputClass.isKeyDown(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
        }

        String act = SM.Update(elapsedTime);
        if(act != null) {
            if (act.equals("start game")) {
                SM.Push(worlds[0]);
            }
            if (act.equals("quit")) {
                glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
            }
            if (act.equals("pause")) {
                SM.Push(pause);
            }
            if(act.equals("resume")) {
                SM.Pop();
            }
            if(act.equals("settings")) {
                SM.Push(settings);
            }
            if(act.equals("back")) {
                SM.Pop();
            }
            if(act.equals("change world")) {
                if(++world > numWorlds-1) {
                    world = 0;
                }

                SM.Push(worlds[world]);
            }
            if(act.startsWith("battle:")) {
                String ent = act.substring(7);
                Mob m = worlds[world].world.mobs[Integer.valueOf(ent)];
                System.out.println("Battle Started with " + m.name + " " + ent);

                BattleState battle = new BattleState(worlds[world].player,m);

                SM.Push(battle);
            }
        }
    }

    public void Render() {
        graphics.clearScreen();

        SM.Render(graphics);

        //String FPS = String.format("fps:%.1f", GameClient.fps);
        //graphics.drawText(FPS, graphics.Font, 16, GraphicsClass.WIDTH/2, 0, 8.0f, 8.0f);
        //graphics.drawText(String.format("%d", world), graphics.Font, 16, GraphicsClass.WIDTH/2, 8, 8.0f, 8.0f);

        graphics.updateScreen();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
        System.out.printf("\nQuitso");
    }

    public void loadWorlds(String filename) {
        JSONParser parser = new JSONParser();
        try {
            FileReader a = new FileReader(filename);
            Object obj = parser.parse(a);
            JSONObject json = (JSONObject) obj;
            numWorlds = Integer.valueOf((String) json.get("num_worlds"));
            JSONArray worlds_Data = (JSONArray) json.get("worlds");
            worlds = new WorldState[numWorlds];
            int count = 0;
            for (Object i : worlds_Data) {
                String S = null;
                for (Object j : (List) i) {
                    S = j.toString();
                }
                worlds[count] = new WorldState(S);
                worlds[count].Init();
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
