package src.Ent;

import java.util.Random;

/**
 * @author Jack Kurfman, jkurfman@purdue.edu
 * @version 3/12/2016.
 */
public class Stat {

    private static final float q1 = 5.0f; //Precision Constant
    private static final float q2 = 4.0f; //Damping Constant
    public static final float aStat = 0.0025f; //Linearity (Stats)
    private static final float aExp = 0.01f; //Linearity (Exp)
    private static final float expFactor = 75.0f; //Exp Factor


    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            System.out.println("Atk Lvl " + 1 + ": " + genFunction(5.0f, 10, aStat) + "+" + genFunction(1.0f, 10, 0));
            System.out.println("Prof: " + (i / 10.0));
            //System.out.println("Level " + i + ": " + expFunction(i));
            //System.out.println("Mob Level " + i + ": " + expDropFunction(i));
            System.out.println("Damage: " + Math.round(attackFunction(genFunction(5.0f, 10, aStat), genFunction(1.0f, 10, 0), (i / 10.0f), 1, 0)));
            System.out.println();
        }
    }

    public static int statFunction(float classFactor, int level) {
        return genFunction(classFactor, level, aStat);
    }

    public static int genFunction(float classFactor, int level, float linearityConstant) {
        return (int) ((classFactor * level * Math.pow(1 + linearityConstant, level)));
    }

    public static int expFunction(int level) {
        return (25 + genFunction(expFactor, level, aExp)); //How much more you needed from the more you needed before
    }

    public static int expDropFunction(int level) {
        return expFunction(level) / (4 + 2 * level);
    }

    public static float attackFunction(int Atk, int WpnAtk, float Prof, float buffM, float buffC) {
        float mean = Atk + WpnAtk + (Prof * WpnAtk) / (q1 * q2);
        float std = (float) Math.sqrt(1 / ((Prof + 1.0f) * q1));
        Random random = new Random();
        random.setSeed(System.nanoTime());
        return (float) (random.nextGaussian() * std + mean) * buffM + buffC;
    }

    public static float defenseFunction(int Def, int ArmDef, float buffM, float buffC) {
        return (Def + ArmDef) * buffM + buffC;
    }
}
