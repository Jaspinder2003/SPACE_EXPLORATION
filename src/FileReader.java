import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FileReader {

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
        map = new GalacticMap(size);

        try (Scanner scanner = new Scanner(new File(fileName))) {
            size = scanner.nextInt();
            scanner.nextLine();
            int i = 1;
            HashSet<String> id = new HashSet<>();
            HashSet<String> positions = new HashSet<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line!=null) {
                    String[] values = line.split(" ");
                    shipType.put(i, values[0]);
                    if (!values[0].equals("FIGHTER") && !values[0].equals("CARGOSHIP") && !values[0].equals("EXPLORER")) {
                        throw new IllegalArgumentException("Invalid Spaceship Type: " + values[0]);
                    }

                    if (id.add(values[1])) {
                        shipID.put(i, values[1]);
                    } else {
                        throw new IllegalArgumentException("Non-unique ID: " + values[1]);
                    }

                    int x = Integer.parseInt(values[2]);
                    int y = Integer.parseInt(values[3]);
                    String position = x + "," + y;

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
                    } else {
                        throw new IllegalArgumentException("Invalid data format: Missing spaceship attributes.");
                    }

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
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid data format: Unable to parse numeric value", e);
        }

        return map;
    }
}
