import javax.imageio.IIOException;
import javax.management.RuntimeErrorException;
import java.io.*;
import java.util.HashMap;

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
             int size=Integer.parseInt(line1);
            int Counter=1;
            while(br.readLine()!=null){
                Counter++;
            }
            /**
             * this counts how many lines are there in the fi*/
            int i=1;
            HashMap<Integer, String> shipType = new HashMap<Integer, String>();
            HashMap<Integer, String> shipID = new HashMap<Integer, String>();
            HashMap<Integer, Integer> shipX = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> shipY = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> damage = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> scan = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> CargoCapacity = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> CurrentCargo = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> TargetX = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> TargetY = new HashMap<Integer, Integer>();
            while(i<=Counter){
                String line=br.readLine();
                if(line!=null){
                    String[] values=line.split(" ");
                shipType.put(i,values[0]);
                shipID.put(i,values[1]);
                int x=Integer.parseInt(values[2]);
                int y=Integer.parseInt(values[3]);
                shipX.put(i,x);
                shipY.put(i,y);
                    if(values.length>5){
                        if(shipType.get(i)=="CARGOSHIP") {
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
                        }
                        if(shipType.get(i)=="FIGHTER"){
                            int d=Integer.parseInt(values[4]);
                            damage.put(i,d);
                        }
                        if(shipType.get(i)=="EXPLORER"){
                            int s=Integer.parseInt(values[4]);
                            scan.put(i,s);
                        }
                    }
                    else{
                    throw new IllegalArgumentException("Invalis data format: Missing spaceship attributes.");
                    }
                    if(values[1].length()<5){
                        throw new IllegalArgumentException("Invalid ID lenggth: "+ values[1]);
                    }
                    if(shipX.get(i)<0||shipX.get(i)>(size-1)){
                        throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map!");
                    }
                    i++;
                }
                else{continue;}
            }

        }
        catch(IOException e){
            throw new UncheckedIOException(new IOException("File not found: "+fileName));
        }

        return null;
        // hint: you need to call placeSpaceship method....

    }
}
