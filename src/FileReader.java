import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
/**
 * Jaspinder Singh Maan
 * T-15
 * 11-3-2023
 */
public class FileReader {
    /**
     * these are some hashmaps whicih i used to store information about the spaceship
     */
    private static HashMap<Integer,String> shipType = new HashMap<>();
    private static HashMap<Integer, String> shipID = new HashMap<>();
    private static HashMap<Integer, Integer> shipX = new HashMap<>();
    private static HashMap<Integer, Integer> shipY = new HashMap<>();
    private static HashMap<Integer, Integer> damage = new HashMap<>();
    private static HashMap<Integer, Integer> scan = new HashMap<>();
    private static HashMap<Integer, Integer> cargoCapacity = new HashMap<>();
    private static HashMap<Integer, Integer> currentCargo = new HashMap<>();
    private static HashMap<Integer, Integer> targetX = new HashMap<>();
    private static HashMap<Integer, Integer> targetY = new HashMap<>();
    private static int totalFighters = 0;
    private static int totalExplorers = 0;
    private static int totalCargoShips = 0;
    private static int size;
    static GalacticMap map;

    public static GalacticMap readFromFile(String fileName) {

/**
 * use a scanner function to read the lines of a given file
 */
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line1=scanner.nextLine();
            /**
             * used to check the format of size
             */
            try{size = Integer.parseInt(line1);}
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid data format: Unable to parse numeric value");
            }
            map = new GalacticMap(size);
            /**
             * creates an object of given size
             */
            scanner.nextLine();
            int i = 1;
            HashSet<String> id = new HashSet<>();
            HashSet<String> positions = new HashSet<>();//use these hashsets to see if some id is already present
/**
 * next is used to check if there is a line present and if not it goes ahead
 */
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line!=null) {
                    String[] values = line.split(" ");
                    shipType.put(i, values[0]);
                    /**
                     * used to check if there is something else than a fighter
                     */
                    if (!values[0].equals("FIGHTER") && !values[0].equals("CARGOSHIP") && !values[0].equals("EXPLORER")) {
                        throw new IllegalArgumentException("Invalid Spaceship Type: " + values[0]);
                    }
/**
 * used to check if there is a duplicate id present
 */
                    if (id.add(values[1])) {
                        shipID.put(i, values[1]);
                    } else {
                        throw new IllegalArgumentException("Non-unique ID: " + values[1]);
                    }

                    int x = Integer.parseInt(values[2]);
                    int y = Integer.parseInt(values[3]);
                    String position = x + "," + y;
/**
 * used to check if there is a duplicate position present
 */
                    if (positions.add(position)) {
                        shipX.put(i, x);
                        shipY.put(i, y);
                    } else {
                        throw new IllegalArgumentException("Position is filled with another item: " + position);
                    }

                    if (values.length >= 5) {
                        if (shipType.get(i).equals("CARGOSHIP")) {
                            int limit = Integer.parseInt(values[4]);
                            int CC = Integer.parseInt(values[5]);
                            int Tx = Integer.parseInt(values[6]);
                            int Ty = Integer.parseInt(values[7]);

                            cargoCapacity.put(i, limit);
                            currentCargo.put(i, CC);
                            targetX.put(i, Tx);
                            targetY.put(i, Ty);
/**
 * used to check for a length of a line
 */
                            if (values.length != 8) {
                                throw new IllegalArgumentException("Invalid data format: Missing cargo ship attributes.");
                            }

                            Spaceship cargo = new CargoShip(shipID.get(i), shipX.get(i), shipY.get(i), cargoCapacity.get(i), currentCargo.get(i), targetX.get(i), targetY.get(i));
                            map.placeSpaceship(cargo);
                            totalCargoShips++;
                        }

                        if (shipType.get(i).equals("FIGHTER")) {
                            int d = Integer.parseInt(values[4]);
                            damage.put(i, d);

                            Spaceship fighter = new FighterShip(shipID.get(i), shipX.get(i), shipY.get(i), damage.get(i));
                            map.placeSpaceship(fighter);
                            totalFighters++;
                        }

                        if (shipType.get(i).equals("EXPLORER")) {
                            int sc = Integer.parseInt(values[4]);
                            scan.put(i, sc);

                            Spaceship explorer = new ExplorerShip(shipID.get(i), shipX.get(i), shipY.get(i), scan.get(i));
                            map.placeSpaceship(explorer);
                            totalExplorers++;
                        }
                        /**
                         * place all the spaceships
                         */
                    } else {
                        throw new IllegalArgumentException("Invalid data format: Missing spaceship attributes.");
                    }
                    /**
                     * used to check the length of the line
                     */

                    if (values[1].length() < 5) {
                        throw new IllegalArgumentException("Invalid ID length: " + values[1]);
                    }

                    if (shipX.get(i) < 0 || shipX.get(i) >= size || shipY.get(i) < 0 || shipY.get(i) >= size) {
                        throw new ArrayIndexOutOfBoundsException("Position is outside of the map boundaries: " + shipX.get(i) + "," + shipY.get(i));
                    }

                    i++;
                }
            }

        }
         catch (IOException e) {
            throw new UncheckedIOException(new IOException("file not found: " + fileName, e));
        }//error checking for file not found

        return map;
    }
}
