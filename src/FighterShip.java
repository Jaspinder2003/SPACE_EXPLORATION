import java.util.Random;
/**
 * The FighterShip class represents a spaceship specialized in combat within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class FighterShip extends Spaceship {
    private int damage;// The damage inflicted by the fighter ship during combat

    /**
     * Constructs a FighterShip object with the specified attributes.
     *
     * @param id     The unique identifier of the fighter ship.
     * @param x      The initial x-coordinate of the fighter ship.
     * @param y      The initial y-coordinate of the fighter ship.
     * @param damage The damage inflicted by the fighter ship during combat.
     */
    public FighterShip(String id, int x, int y, int damage) {
        // Initialize FighterShip attributes
    }

    /**
     * Generates a random direction for the fighter ship to move.
     *
     * @return A random integer representing the direction (0 to 7).
     */
    private int getRandomDirection() {
        Random random = new Random();
        return random.nextInt(8); // Generates a random integer between 0 and 7
    }

    /**
     * Implements the movement behavior of the fighter ship within the galactic map.
     * The movement of the fighter ship is random, as it changes direction randomly.\
     *
     * ..............
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");

        // Implementation for fighter ship movement

    }

    /**
     * Implements the interaction behavior of the fighter ship with another spaceship.
     * The fighter ship engages in combat with other spaceships during interaction.
     *
     * .........
     *
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());

        // Implementation for fighter ship interaction (e.g., combat)

    }
}

