package model;

public class Coordinate {
    private int x;
    private int y;

    @Override
    public String toString() {
        return String.format("Coordinate[ (%d,%d) ]", this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if ( ! ( o instanceof Coordinate coordinate)) {return false;}
        return this.x==coordinate.x && this.y==coordinate.y;
    }

    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }


}
