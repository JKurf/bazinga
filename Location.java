public class Location {
    private float x;
    private float y;
    private float xLast;
    private float yLast;
    private Direction direction;

    public Location() {
        this(0, 0, Direction.DOWN);
    }

    public Location(float x, float y) {
        this(x, y, Direction.DOWN);
    }

    public Location(float x, float y, Direction direction) {
        this.x = x;
        this.y = y;
        this.xLast = x;
        this.yLast = y;
        this.direction = direction;
    }

    public Location(Location loc) {this(loc.xPos(), loc.yPos(), Direction.DOWN);}

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

    public Direction getDirection() {
        return direction;
    }

    public void setX(float coordinate) {
        x = coordinate;
    }

    public void setY(float coordinate) {
        y = coordinate;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }

    /*public void move(Direction dir) {
        if (dir == direction) {
            switch (direction) {
                case DOWN:
                    y--;
                    break;
                case UP:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
        } else {
            direction = dir;
        }
    }*/
}