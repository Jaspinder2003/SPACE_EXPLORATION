import javax.imageio.IIOException;
import javax.management.RuntimeErrorException;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The FileReader class provides static methods for reading data from a file and constructing a GalacticMap object.
 * It reads a text file containing information about spaceships and their attributes, and initializes a GalacticMap
 * based on the data read from the file.
 *
 * @author Parisa Daeijavad
 *
 */

public class FileReader {

    /**
     * Reads data from a specified file and constructs a GalacticMap object based on the information read.
     *
     * @param fileName the name of the file to read from
     * @return a GalacticMap object initialized with data read from the file
     * @throws RuntimeException if the file specified by fileName is not found or if an error occurs while reading the file
     *
     */
    public static HashMap<Integer, String> shipType = new HashMap<>();
    public static HashMap<Integer, String> shipID = new HashMap<>();
    public static HashMap<Integer, Integer> shipX = new HashMap<>();
    public static HashMap<Integer, Integer> shipY = new HashMap<>();
    public static HashMap<Integer, Integer> damage = new HashMap<>();
    public static HashMap<Integer, Integer> scan = new HashMap<>();
    public static HashMap<Integer, Integer> CargoCapacity = new HashMap<>();
    public static HashMap<Integer, Integer> CurrentCargo = new HashMap<>();
    public static HashMap<Integer, Integer> TargetX = new HashMap<>();
    public static HashMap<Integer, Integer> TargetY = new HashMap<>();
    public static int totalFighters=0;
    public static int totalExplorers=0;
    public static int totalCargoships=0;
    private static int size;
    static GalacticMap map= new GalacticMap(size);

    public static GalacticMap readFromFile(String fileName) throws FileNotFoundException {
        // Your code goes here ....
        java.io.FileReader file_reader;
        try {
            File file = new File(fileName);
            file_reader = new java.io.FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(file_reader);
        try{
            String line1=br.readLine();

            try {
                size = Integer.parseInt(line1);
            }
            catch(NumberFormatException e){
                throw new NumberFormatException("Invalid file format:Missing map size");
            }
            /**
             * converts the size into an Integer from a string.
             */

            /**
             * this counts how many lines are there in the fi*/
            int i=1;
            HashSet<String> id = new HashSet<String>();
            HashSet<String> positions=new HashSet<String>();
            String line;
            while(((line = br.readLine()) != null)){
                if(!line.isEmpty()){
                    /**
                     * this if statement skips the empty lines
                     */
                    String[] values=line.split(" ");
                shipType.put(i,values[0]);
                if(!shipType.get(i).equals("FIGHTER")&&!shipType.get(i).equals("CARGOSHIP")&&!shipType.get(i).equals("EXPLORER")){
                    throw new IllegalArgumentException("Invalid Spcaeship Type"+shipType.get(i));
                }
                    /**
                     * error checking for seeing that the types are consistent
                     */
                if(id.add(values[1])){
                    id.add(values[1]);
                    shipID.put(i,values[1]);
                }
                /**
                 * used in checking if there are any duplicate values for id
                 */
                else{throw new IllegalArgumentException(("Non-unique ID: "+values[1])); }
                    /**
                     * error checking for unique id
                     * */
                    try{
                int x=Integer.parseInt(values[2]);
                int y=Integer.parseInt(values[3]);
                String position=x+","+y;
                if(positions.add(position)){
                    positions.add(position);
                    shipX.put(i,x);
                    shipY.put(i,y);}
                else {
                    throw new IllegalArgumentException("Wrong input file! the position is filled with another item!");
                }
                    if(values.length>5){
                        if(shipType.get(i).equals("CARGOSHIP")) {
                            int limit=Integer.parseInt(values[4]);
                            int CC=Integer.parseInt(values[5]);
                            int Tx=Integer.parseInt(values[6]);
                            int Ty=Integer.parseInt(values[7]);
                            CargoCapacity.put(i,limit);
                            CurrentCargo.put(i,CC);
                            TargetX.put(i,Tx);
                            TargetY.put(i,Ty);
                            if (values.length != 8) {
                                throw new IllegalArgumentException("Invalid data format: Missing cargo ship attributes.");
                            }
                            Spaceship cargo=new CargoShip(shipID.get(i),shipX.get(i),shipY.get(i),CargoCapacity.get(i),CurrentCargo.get(i),TargetX.get(i),TargetY.get(i));
                            map.placeSpaceship(cargo);
                            totalCargoships++;
                        }
                        if(shipType.get(i).equals("FIGHTER")){
                            int d=Integer.parseInt(values[4]);
                            damage.put(i,d);
                            Spaceship fighter=new FighterShip(shipID.get(i),shipX.get(i),shipY.get(i),damage.get(i));
                            map.placeSpaceship(fighter);
                            totalFighters++;
                        }
                        if(shipType.get(i).equals("EXPLORER")){
                            int s=Integer.parseInt(values[4]);
                            scan.put(i,s);
                            Spaceship explorer=new ExplorerShip(shipID.get(i),shipX.get(i),shipY.get(i),scan.get((i)));
                            map.placeSpaceship(explorer);
                            totalExplorers++;
                        }
                    }
                    else{
                    throw new IllegalArgumentException("Invalid data format: Missing spaceship attributes.");
                    }
                    if(values[1].length()<5){
                        throw new IllegalArgumentException("Invalid ID length: "+ values[1]);
                    }
                    if(shipX.get(i)<0||shipX.get(i)>(size-1)){
                        throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map!");
                    }
                    i++;}
                    catch(NumberFormatException e){
                        throw new NumberFormatException("Invalid data format: unable to parse numeric value");
                    }
                }
                else{continue;}
            }

        }
        catch(IOException e){
            throw new UncheckedIOException(new IOException("File not found: "+fileName));
        }



        return map;
        // hint: you need to call placeSpaceship method....

    }
}
