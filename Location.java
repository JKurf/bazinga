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

    public void move(String dir) {
        if (dir.toUpperCase().equals(direction)) {
            switch (direction) {
                case "DOWN":
                    y--;
                    break;
                case "UP":
                    y++;
                    break;
                case "LEFT":
                    x--;
                    break;
                case "RIGHT":
                    x++;
                    break;
            }
        } else {
            direction = dir.toUpperCase();
        }
    }
}