/**
 * The abstract class Spaceship represents a generic spaceship entity in the galactic space.
 * It defines common attributes and behaviors for different types of spaceships.
 * @author Parisa Daeijavad
 */
public abstract class Spaceship {

    // attributes:

    // The unique identifier of the spaceship
    // The current coordinates of the spaceship
    // The type of the spaceship

    // methods:

    /**
     * Constructs a Spaceship object with the specified attributes.
     *
     * @param ....
     *
     */
    // constructor goes here...

    /**
     * Abstract method to define the movement behavior of the spaceship.
     *
     * @param ...
     */
    // move ....

    /**
     * Abstract method to define the interaction behavior of the spaceship with another spaceship.
     *
     * @param ....
     */
    // interact ...


    // Getters and setters

    /**
     * Retrieves the unique identifier of the spaceship.
     *
     * ....
     */
    // getID

    /**
     * Retrieves the current x-coordinate of the spaceship.
     *
     * ....
     */
    // getX

    /**
     * Retrieves the current y-coordinate of the spaceship.
     *
     * .....
     */
    // getY

    /**
     * Retrieves the type of the spaceship.
     *
     * .....
     */
    // getType

    /**
     * Sets the x-coordinate of the spaceship to the specified value.
     *
     * @param ....
     */
    // setX

    /**
     * Sets the y-coordinate of the spaceship to the specified value.
     *
     * @param .....
     */
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
