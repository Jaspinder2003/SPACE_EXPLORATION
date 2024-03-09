/**
 * The abstract class Spaceship represents a generic spaceship entity in the galactic space.
 * It defines common attributes and behaviors for different types of spaceships.
 * @author Parisa Daeijavad
 */
public abstract class Spaceship {

    // attributes:
private int x;
private int y;
private String id;
private SpaceshipType type;


    // The unique identifier of the spaceship
    // The current coordinates of the spaceship
    // The type of the spaceship

    // methods:


    // constructor goes here...
    public Spaceship(String id, int x, int y, SpaceshipType type) {
        /**
         * "this" is used to assign the insatnce variables to the parameters of the constructor
         */
        this.id = id;   // Assign the id parameter to the id field of the object
        this.x = x;     // Assign the x parameter to the x field of the object
        this.y = y;     // Assign the y parameter to the y field of the object
        this.type = type; // Assign the type parameter to the type field of the object
    }
    /**
     * Abstract method to define the movement behavior of the spaceship.
     */
    public abstract void move(GalacticMap galacticMap);
    // move ....

    /**
     * Abstract method to define the interaction behavior of the spaceship with another spaceship.
     */
    public abstract void interact(GalacticMap galacticMap,Spaceship other);
    // interact ...


    // Getters and setters

    /**
     * Retrieves the unique identifier of the spaceship.
     */
    protected String getID(){
        return id;
    }
    // getID

    /**
     * Retrieves the current x-coordinate of the spaceship.
     */
    protected int getX(){
        return x;
    }
    // getX

    /**
     * Retrieves the current y-coordinate of the spaceship.
     */
    protected  int getY(){
        return y;
    }
    // getY

    /**
     * Retrieves the type of the spaceship.
     */
    protected SpaceshipType getType(){
        return type;
    }
    // getType

    /**
     * Sets the x-coordinate of the spaceship to the specified value.
     */
    public void setX(int newX){
        x=newX;
    }
    // setX

    /**
     * Sets the y-coordinate of the spaceship to the specified value.
     */
    public void setY(int newY){
        y=newY;
    }
    // setY


    /**
     * Calculates the distance between this spaceship and another spaceship.
     *
     * @param other The other spaceship to calculate the distance to.
     * @return The distance between this spaceship and the other spaceship.
     */
    public int calculateDistance(Spaceship other) {
        int deltaX = Math.abs(this.getX() - other.getX());
        int deltaY = Math.abs(this.getY() - other.getY());
        return Math.max(deltaX, deltaY);
    }

    /**
     * Retrieves the name of the spaceship.
     *
     * @return The name of the spaceship, which includes its type and ID.
     */
    public String getName() {
        return this.getType() + " " + this.getID();
    }
}
