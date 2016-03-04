/**
 * @author Jack Kurfman
 * @version 3/2/2016.
 */
public class Mob {

    public static void main(String[] args) {
        Mob[] Skeleton = new Mob[10];
        for (int i = 0; i < 10; i++) {
            Skeleton[i] = new Mob("Zombie", i + 1, 10, 10, 90, 100);
            System.out.printf("\nlvl %d: %d", i + 1, Skeleton[i].health);
        }
    }

    private String name;
    private int level;
    private int levelCap;
    private int health; //Mob Health

    public Mob(String name, int level, int levelCap, int healthStart, int healthMid, int healthCap) {
        this.name = name;
        this.level = level;
        this.levelCap = levelCap;
        this.health = statFunction(healthStart, healthMid, healthCap);
    }

    private int statFunction(int start, int mid, int cap) {
        double a;
        double b;
        b = 2 * (double) (mid - start) / (cap - start);
        b = Math.pow(b, 1.0 / (-0.5 * levelCap));
        b = b - 1;
        a = Math.pow(1.0 + b, levelCap);
        a = (cap - start) / (levelCap * a);
        return (int) (a * level * Math.pow(1.0 + b, level)) + start;
    }
}
