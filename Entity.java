import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Entity {
    boolean alive = true;
    public  String lineEntry; //Entity's Line in Entities.txt
    public String name; //Entity's Name
    Location location;
    Texture tex;

    int maxHealth;
    int health;
    int level = 1;
    int exp = 0;
    int expNeeded = 100;

    int attack = 1;
    int defense = 2;
    int vitality = 1;
    int skill = 1;

    Animation[] Animations;
    int currentAnimation = 0;
    String[] AnimationsNames;

    public void calcStats() {
        if (canLevel()) {
            exp -= expNeeded;
            levelUp();
            expNeeded += Stat.expFunction(level);
        }

        maxHealth = vitality * 10;
        health = maxHealth;
    }

    public float getDamage() {
        return Stat.attackFunction(attack, 1, 0, 1, 0);
    }
    public void damage(float dmg) {
        health -= (dmg - Stat.defenseFunction(defense, 1, 1, 0));
    }

    /**
     * This Constructor will Give the Entity a Cartesian Position, an ID Number, and a Name
     * @param ID Identification number (4 digit String)
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Entity(String ID, int x, int y, Direction dir, boolean mob) {
        //name = ID;
        this.location = new Location(x, y, dir);
        String line;
        File file = new File("Data/Entities.txt");
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                if (line.contains(ID)) {
                    lineEntry = line;
                    if (mob) {
                        return;
                    } else {
                        this.name = line.substring(5);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("\nEntity File not Found\n");
        }

        calcStats();
    }

    public void loadTexture(String filename) {
        tex = GraphicsClass.loadTexture(filename);
    }

    public void loadAnimationData(String filename) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject json = (JSONObject) obj;
            loadTexture((String) json.get("SpriteSheet"));

            JSONArray animations = (JSONArray) json.get("Animations");
            AnimationsNames = new String[animations.size()];
            Animations = new Animation[animations.size()];

            int count = 0;
            for(Object i : animations) {
                AnimationsNames[count++] = (String) i;
            }
            int numAnim = count;

            int whichAnimation = 0;
            int whichFrame;
            for(int n = 0; n < numAnim; n ++) {
                JSONArray anim = (JSONArray) json.get(AnimationsNames[n]);

                Animations[whichAnimation] = new Animation(anim.size());
                whichFrame = 0;
                for (Object i : anim) {
                    int[] data = new int[4];
                    int iii = 0;
                    for (Object j : (List) i) {
                        data[iii++] = Integer.valueOf((String) j);
                    }

                    Animations[whichAnimation].frames[whichFrame++] = new Frame(data[0], data[1], data[2], data[3]);
                }
                whichAnimation++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void levelUp() {
        level++;
        attack = Stat.statFunction(5.0f, level);
        defense = Stat.statFunction(5.0f, level);
        vitality = Stat.statFunction(5.0f, level);
        skill = Stat.statFunction(5.0f, level);
        System.out.println(name + " is now level " + level);
        calcStats();
    }

    public boolean canLevel() {
        return (exp >= expNeeded);
    }
    public void gainExp(Entity killed) {
        exp += Stat.expDropFunction(killed.level);
        System.out.println("Gained: " + Stat.expDropFunction(killed.level) + "exp");
        calcStats();
    }
}
