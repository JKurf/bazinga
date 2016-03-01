public class Location {
    private int x;
    private int y;
    private int xLast;
    private int yLast;
    private String direction;

    public Location() {
        this(0, 0, "DOWN");
    }

    public Location(int x, int y) {
        this(x, y, "DOWN");
    }

    public Location(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.xLast = x;
        this.yLast = y;
        this.direction = direction;
    }

    public int xPos() {
        return x;
    }

    public int yPos() {
        return y;
    }

    public int xPrev() {
        return xLast;
    }

    public int yPrev() {
        return yLast;
    }

    public String getDirection() {
        return direction;
    }

    public void setX(int coordinate) {
        x = coordinate;
    }

    public void setY(int coordinate) {
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