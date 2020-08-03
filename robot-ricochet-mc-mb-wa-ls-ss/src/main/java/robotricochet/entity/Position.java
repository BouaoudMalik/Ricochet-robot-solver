package robotricochet.entity;

/**
 * the class Position is used to define position of an object in grid
 */
public class Position {


    private int x;
    private int y;

    /**
     * class constructor
     * @param x value of line
     * @param y value of column
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter of x
     * @return line index
     */
    public int getX() {
        return this.x;
    }
    /**
     * getter of y
     * @return column index
     */
    public int getY() {
        return this.y;
    }

    /**
     * setter of x
     * @param x the neuw value of x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * setter of y
     * @param y the neuw value of x
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * redifinition of tiString
     * @return String of position
     */
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * compare two positions
     * @param position the second position to make comparison
     * @return boolean: true  if the two positions are equal  else false
     */
    public boolean isTheSameAs(Position position) {
        return (this.getX() == position.getX() && this.getY() == position.getY());
    }

    /**
     * redifinition of equals
     * @param o Object
     * @return true if the current position is the same as Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    /**
     * redifinition of hashCode
     * @return position code
     */
    @Override
    public int hashCode() {
        return x * 1000000000 + y;
    }
}