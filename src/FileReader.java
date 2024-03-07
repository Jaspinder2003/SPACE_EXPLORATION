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
            int i=1;
            HashMap<Integer, String> shipType = new HashMap<Integer, String>();
            HashMap<Integer, String> shipID = new HashMap<Integer, String>();
            HashMap<Integer, String> shipX = new HashMap<Integer, String>();
            HashMap<Integer, String> shipY = new HashMap<Integer, String>();
            while(i<=Counter){
                String line=br.readLine();
                if(line!=null){
                String[] values=line.split(" ");
                if(values.length>5){
                shipType.put(i,values[0]);
                shipID.put(i,values[1]);
                shipX.put(i,values[2]);
                shipY.put(i,values[3]);
                i++;}
                else{throw new IllegalArgumentException("invalid data format. missing spaceship attributes");}
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
