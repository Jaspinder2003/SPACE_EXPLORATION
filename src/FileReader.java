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
            String size=line1;
            int Counter=1;
            while(br.readLine()!=null){
                Counter++;
            }
            /**
             * this counts how many lines are there in the file that are not empty
             * and the part below does operations on every line*/
            int i=1;//initialized a starting point
            HashMap<Integer, String[]> ship = new HashMap<Integer, String[]>();
            /**this hashmap conatains a ships attributes and in the order given
             * 1st element contains id
             * 2nd 3rd contain x and y postions
             * and the rest contain the rest attributes
             */
            HashMap<Integer, String> ShipType=new HashMap<Integer, String>();
            //this hashmap contain ship type
            while(i<=Counter){
                String line=br.readLine();
                if(line!=null){
                    String[] values=line.split(" ");
                String[] values1=line.split(" ",1);
                String ShipFormat= values1[0];
                String[] ShipAttributes=values1[1].split(" ");
                    /**
                     * till here I have first separated the line into 2parts
                     * 1st part = type, stored in ShipType
                     * 2nd part= an array of ship attributes which are stored in ship
                     */
                if(values.length>5){//error checking for length of the whol;e line
                    ShipType.put(i,ShipFormat);
                    ship.put(i,ShipAttributes);
                    if(ShipType.get(i)=="CARGOSHIP"){
                        if(ship.get(i).length!=8){
                            throw new IllegalArgumentException("invalid data format. missing Cargoship attributes");
                            break;
                        }
                    }
                    if(ship.get(i)[0].length()!=5){
                        throw new IllegalArgumentException("Invalid ID length: " + ship.get(i)[0]);
                    }

                }
                else{throw new IllegalArgumentException("invalid data format. missing spaceship attributes");
                break;}//for if length of the line si less than 5
                }
                else{continue;}//for if a line is empty
            i++;
            }
        }
        catch(IOException e){
            throw new UncheckedIOException(new IOException("File not found: "+fileName));
        }

        return null;
        // hint: you need to call placeSpaceship method....

    }
}
