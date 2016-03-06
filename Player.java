public class Player extends Entity {
    private String name; //Player's Name
    private String spec; //Player's Class
    private int level; //Player's Level
    int health; //Player's Health Points
    private int maxHealth; //Player's Health Cap
    private int maxHealthBuffed; //Player's Buffed Health Cap
    private int expCurrent; // Player's Experience
    private int expNeeded; //Experience to Level Up
    private int expTotal; //Total Player Experience
    /*
     * Resistance Array as follows:
     * 0 - Fire
     * 1 - Ice
     * 2 - Magic
     */
    int[] resistance = new int[3];

    /**
     * This Constructor is for Initial Character Creation pt I
     * @param name Character Name
     * @param spec Character Class
     */
    public Player(String name, String spec) {
        this(name, spec, 50, 50, 50, 0, 100, 0, 1);
    }

    /**
     * This Constructor is for Creating a Character pt II
     * @param name Character Name
     * @param spec Character Class
     * @param health Character Current Health
     * @param maxHealth Character Max Health
     * @param expCurrent Character Current Experience
     * @param expNeeded Character Needed Experience
     * @param expTotal Character Total Experience
     */
    public Player(String name, String spec,
                  int health, int maxHealth, int maxHealthBuffed,
                  int expCurrent, int expNeeded, int expTotal,
                  int level) {
        super("0001", 0, 0, Direction.DOWN, false);
        this.name = name;
        this.spec = spec;
        this.level = level;
        this.health = health;
        this.maxHealth = maxHealth;
        this.maxHealthBuffed = maxHealthBuffed;
        this.expCurrent = expCurrent;
        this.expNeeded = expNeeded;
        this.expTotal = expTotal;
        this.resistance[0] = specCreationResistance(spec)[0];
        this.resistance[1] = specCreationResistance(spec)[1];
        this.resistance[2] = specCreationResistance(spec)[2];
    }

    /**
     * This Constructor is for Loading a Character
     * @param name Character Name
     * @param spec Character Class
     * @param health Character Current Health
     * @param maxHealth Character Max Health
     * @param expCurrent Character Current Experience
     * @param expNeeded Character Needed Experience
     * @param expTotal Character Total Experience
     * @param resFire Character Fire Resistance
     * @param resIce Character Ice Resistance
     * @param resMagic Character Magic Resistance
     */
    public Player(String name, String spec,
                  int health, int maxHealth, int maxHealthBuffed,
                  int expCurrent, int expNeeded, int expTotal,
                  int level,
                  int resFire, int resIce, int resMagic) {
        super("0001", 0, 0, Direction.DOWN, false);
        this.name = name;
        this.spec = spec;
        this.level = level;
        this.health = health;
        this.maxHealth = maxHealth;
        this.maxHealthBuffed = maxHealthBuffed;
        this.expCurrent = expCurrent;
        this.expNeeded = expNeeded;
        this.expTotal = expTotal;
        this.resistance[0] = resFire;
        this.resistance[1] = resIce;
        this.resistance[2] = resMagic;
    }

    /**
     * This Method Calls the Character Name
     * @return Character Name
     */
    public String getName() {
        return name;
    }

    /**
     * This Method Calls the Character Class
     * @return Character Class
     */
    public String getSpec() {
        return spec;
    }

    /**
     * This Method Determines if the Character has Died
     * @return Dead?
     */
    public boolean isDead() {
        return (health <= 0);
    }

    /**
     * This Method Determines whether the Character can Level
     * @return Can Level?
     */
    public boolean canLevel() {
        return (expCurrent <= expNeeded);
    }

    public int getLevel() {return level;}

    public void levelUp() {
        expCurrent -= expNeeded;
        expNeeded = expNeeded + (100 * level++);
        //TODO Make Leveling Method
    }

    /**
     * This Method will heal the Character (Only Up To Max Health)
     * @param hp Hit Points to add to Character Health
     */
    public void heal(int hp) {
        health += hp;
        if (health > maxHealthBuffed) health = maxHealthBuffed;
    }

    /**
     * This Method will Alter Character's Buffed Health Cap
     * @param hp Number of Hit Points to add to Cap
     */
    public void healthSetBuff(int hp) {
        maxHealthBuffed = maxHealth + hp;
    }

    /**
     * This Method will Remove any Health Buff from Character
     */
    public void healthRemBuff() {
        maxHealthBuffed = maxHealth;
    }

    public void takeDamage(int[] damage) {
        //TODO Make takeDamage Method based on defence and resistances
    }

    public int getExpCurrent() {
        return expCurrent;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public int getExpTotal() {
        return expTotal;
    }

    public void gainExp(int exp) {
        expCurrent += exp;
        expTotal += exp;
    }


    /**
     * This Method is used in Character Creation Only
     * @param spec Character Class
     * @return Array of Resistance Stats
     */
    private int[] specCreationResistance(String spec) {
        switch (spec) {
            case "Warrior":
                return new int[] {0, 0, 0};
            case "Sorcerer":
                return new int[] {0, 0, 1};
            case "Pyromancer":
                return new int[] {1, 0, 0};
            case "Frost Mage":
                return new int[] {0, 1, 0};
            default:
                return new int[] {0, 0, 0};
        }
    }
}
