/**
 * The CargoShip class represents a spaceship specialized in transporting cargo within the galactic space.
 * It inherits from the Spaceship class and defines specific movement, interaction, and cargo management behaviors.
 * @author Parisa Daeijavad
 */
public class CargoShip extends Spaceship {

    private double cargoCapacity; // Maximum cargo capacity of the CargoShip
    private double currentCargo;  // Current amount of cargo on the CargoShip

    private int targetX; // The x-coordinate of the cargo ship's destination.
    private int targetY; // The y-coordinate of the cargo ship's destination.

    private boolean reachedDestination; // Indicates if the cargo has reached its destination

    /**
     * Constructs a CargoShip object with the specified attributes.
     *
     * @param id            The unique identifier of the cargo ship.
     * @param x             The initial x-coordinate of the cargo ship.
     * @param y             The initial y-coordinate of the cargo ship.
     * @param cargoCapacity The maximum cargo capacity of the cargo ship.
     * @param currentCargo  The current amount of cargo on the cargo ship.
     * @param targetX       The x-coordinate of the cargo ship's destination.
     * @param targetY       The y-coordinate of the cargo ship's destination.
     */
    public CargoShip(String id, int x, int y, double cargoCapacity, double currentCargo, int targetX, int targetY) {
        // Initialize CargoShip attributes properly
    }

    /**
     * Implements the movement behavior of the cargo ship within the galactic map.
     * The cargo ship moves towards its designated target coordinates.
     *
     * ....................
     */
    @Override
    public void move(GalacticMap galacticMap) {

        System.out.print("........Moving.......");

        // CargoShip-specific movement logic

    }

    /**
     * Implements the interaction behavior of the cargo ship with another spaceship.
     * The cargo ship can exchange cargo with other cargo ships during interaction.
     *
     * ............
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {

        System.out.println(".........interacting...........with.... " + other.getName());

        // CargoShip interaction logic
    }

    /**
     * Loads cargo onto the CargoShip up to its maximum capacity.
     *
     * ...........
     */
    public void loadCargo(double cargoAmount) {

    }

    /**
     * Unloads cargo from the CargoShip.
     *
     * ..............
     */
    public void unloadCargo(double cargoAmount) {

    }

    /**
     * Retrieves the current amount of cargo on the CargoShip.
     *
     * @return The current cargo amount.
     */
    public double getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Retrieves the x-coordinate of the CargoShip's destination.
     *
     * @return The x-coordinate of the destination.
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Retrieves the y-coordinate of the CargoShip's destination.
     *
     * @return The y-coordinate of the destination.
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Sets the status of whether the CargoShip has reached its destination.
     *
     * @param b True if the CargoShip has reached its destination, false otherwise.
     */
    public void setReachedDestination(boolean b) {
        reachedDestination = b;
    }

    /**
     * Checks if the CargoShip has reached its destination.
     *
     * @return True if the CargoShip has reached its destination, false otherwise.
     */
    public boolean isReachedDestination() {
        return reachedDestination;
    }
}

