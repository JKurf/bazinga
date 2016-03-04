public class Location {
    private float x;
    private float y;
    private float xLast;
    private float yLast;
    private String direction;

    public Location() {
        this(0, 0, "DOWN");
    }

    public Location(float x, float y) {
        this(x, y, "DOWN");
    }

    public Location(float x, float y, String direction) {
        this.x = x;
        this.y = y;
        this.xLast = x;
        this.yLast = y;
        this.direction = direction;
    }

    public Location(Location loc) {this(loc.xPos(), loc.yPos(), "DOWN");}

    public float xPos() {
        return x;
    }

    public float yPos() {
        return y;
    }

    public float xPrev() {
        return xLast;
    }

    public float yPrev() {
        return yLast;
    }

    public String getDirection() {
        return direction;
    }

    public void setX(float coordinate) {
        x = coordinate;
    }

    public void setY(float coordinate) {
        y = coordinate;
    }

    public void setDirection(String dir) {
        direction = dir;
    }

    public void move(float dx, float dy, World world) {
        try {
            if (canMoveX(dx, world)) { x += dx; }
        } catch (Exception ignored) {}
        try {
            if (canMoveY(dy, world)) { y += dy; }
        } catch (Exception ignored) {}
    }

    public boolean canMoveX(float dx, World world) {
        if (dx < 0) {
            return !world.clip[(int) (y / 16)][(int) ((x + dx) / 16)];
        } else {
            return !world.clip[(int) (y / 16)][(int) ((x + 16 + dx) / 16)];
        }
    }

    public boolean canMoveY(float dy, World world) {
        if (dy < 0) {
            return !world.clip[(int) ((y + dy) / 16)][(int) (x / 16)];
        } else {
            return !world.clip[(int) ((y + 16 + dy) / 16)][(int) (x / 16)];
        }
    }
}