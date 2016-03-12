public class Player extends Entity {
    int dosh;

    public Player(String ID, int x, int y, Direction dir, boolean mob) {
        super(ID, x, y, dir, mob);
        dosh = 2147483647;
    }

    public Player(String ID) {
        super(ID, 1, 1, Direction.UP, false);
        dosh = 2147483647;
    }
    public Player(String Name, String ID) {
        super(ID, 1, 1, Direction.UP, false);
        this.name = Name;
        dosh = 2147483647;
    }
}