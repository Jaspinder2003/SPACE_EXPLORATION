import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * The Game class simulates a space exploration game where spaceships interact within a galactic map.
 * It initializes the game, executes the game loop, and checks for end conditions.
 * @author Parisa Daeijavad
 */
public class Game {
    /**
     * The main method initializes the game, executes the game loop, and checks for end conditions.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        GalacticMap galacticMap = FileReader.readFromFile("config.txt");

        // Display initial configuration
        System.out.println("Initial Configuration:");
        System.out.println(galacticMap.toString());

        // Simulate space exploration game loop with random order
        while (true) {
            // Get a random spaceship from the GalacticMap
            Spaceship randomSpaceship = galacticMap.getRandomSpaceship();
            System.out.println("Player Spaceship: " + randomSpaceship.getName());

            // Execute movement logic for the random spaceship
            randomSpaceship.move(galacticMap);

            // Execute interaction logic for the random spaceship
            Spaceship otherSpaceship = galacticMap.getRandomSpaceship();
            randomSpaceship.interact(galacticMap, otherSpaceship);

            // Check if all cargoes have reached their destinations
            if (galacticMap.allCargoesReachedDestination()) {
                System.out.println("All cargoes have reached their destinations. Game over!");
                break;
            }

            // Check if all explorers and cargoes have been removed by fighters
            if (galacticMap.allExplorersAndCargoesRemoved()) {
                System.out.println("All explorers and cargoes have been removed by fighters. Game over!");
                break;
            }

            // Check if all fighters are reported by explorers
            if (galacticMap.allFightersReported()) {
                System.out.println("All fighters have been reported by explorers. Game over!");
                break;
            }

        }
    }
}
