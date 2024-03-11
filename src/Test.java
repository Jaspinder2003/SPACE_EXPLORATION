import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    @org.junit.jupiter.api.Test
    void ToStringTest() {

        GalacticMap map=new GalacticMap(2);

        String s=map.toString();
        assertEquals("[         ][         ]\n" +
                "[         ][         ]",s);
    }
    @org.junit.jupiter.api.Test
    void toStringTest2() {
        GalacticMap map=new GalacticMap(2);
        Spaceship sc=new FighterShip("f1234",0,0,1);
        Spaceship sc2=new ExplorerShip("e1234",1,1,1);
        map.placeSpaceship(sc);
        map.placeSpaceship(sc2);
        String s=map.toString();
        assertEquals("[ F-f1234 ][         ]\n" +
                "[         ][ E-e1234 ]",s);
    }

    @org.junit.jupiter.api.Test
    void fightershipmove1(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship fighter1=new FighterShip("f1234",1,1,1);
        galacticMap.placeSpaceship(fighter1);
        fighter1.move(galacticMap);
        Spaceship ship=galacticMap.getSpaceshipAt(2,2);
        assertTrue((fighter1.getX()==1&&fighter1.getY()==2));
    }
    @org.junit.jupiter.api.Test
    void fightershipmove2(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship fighter1=new FighterShip("f1234",0,1,1);
        Spaceship explorer1 = new ExplorerShip("e1234",2,3,1);
        galacticMap.placeSpaceship(fighter1);
        galacticMap.placeSpaceship(explorer1);
        PrintStream Out = System.out;

        // Create a ByteArrayOutputStream so that we can capture the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        // Set System.out to our PrintStream
        System.setOut(newOut);

        galacticMap.moveSpaceshipTo(fighter1,2,3);
        System.setOut(Out);
        String output=baos.toString();//this to string function is different than the one in galactic map
        assertEquals("Moving Failed! the position is filled with another spaceship!" +
                "\n",output);

    }

    @org.junit.jupiter.api.Test
    void fighterinteract1(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship fighter1=new FighterShip("f1232",1,1,2);
        galacticMap.placeSpaceship(fighter1);
        Spaceship cargo1=new CargoShip("C1234",2,3,1,1,1,1);
        galacticMap.placeSpaceship(cargo1);
        fighter1.interact(galacticMap,cargo1);
        Spaceship s=galacticMap.getSpaceshipAt(2,3);
        assertEquals(null,s);
    }
    @org.junit.jupiter.api.Test
    void fighterinteract2(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship fighter1=new FighterShip("f1232",1,1,2);
        galacticMap.placeSpaceship(fighter1);
        Spaceship cargo1=new CargoShip("C1234",2,4,1,1,1,1);
        galacticMap.placeSpaceship(cargo1);
        fighter1.interact(galacticMap,cargo1);
        Spaceship s=galacticMap.getSpaceshipAt(2,4);
        assertEquals(cargo1,s);
    }

    @org.junit.jupiter.api.Test
    void explorermove1(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship explorer1=new ExplorerShip("e1234",2,2,3);
        galacticMap.placeSpaceship(explorer1);
        explorer1.move(galacticMap);


        Spaceship ship=galacticMap.getSpaceshipAt(2,3);
        assertEquals(explorer1,ship);
    }
    @org.junit.jupiter.api.Test
    void explorermove2(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship explorer1=new ExplorerShip("e1234",2,2,3);
        galacticMap.placeSpaceship(explorer1);
        explorer1.move(galacticMap);
        explorer1.move(galacticMap);

        Spaceship ship=galacticMap.getSpaceshipAt(3,3);
        assertEquals(explorer1,ship);
    }
    @org.junit.jupiter.api.Test
    void explorerinteract2(){
        GalacticMap map=new GalacticMap(5);
        Spaceship explorer=new ExplorerShip("e1234",1,1,2);
        map.placeSpaceship(explorer);
        Spaceship cargo=new CargoShip("c1234",2,1,1,1,1,2);
        map.placeSpaceship(cargo);
        explorer.interact(map,cargo);

    }
    @org.junit.jupiter.api.Test
    void File_readertest(@TempDir Path tempDir) throws IOException {
        // Setup: Create a file where the 'size' is not an integer
        Path file = tempDir.resolve("invalidSizeFile.txt");
        Files.write(file, List.of(
                "notAnInteger", // Invalid size value
                "FIGHTER ID1 1 1 5" // Example spaceship entry
        ));

        // Execution & Verification: Expect an IllegalArgumentException for the invalid size format
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FileReader.readFromFile(file.toString());
        });

        // Further verify that the exception message contains specific text about the invalid format
        assertTrue(exception.getMessage().contains("Invalid data format: Unable to parse numeric value"));
    }
    @org.junit.jupiter.api.Test
    void fileReader_test2(@TempDir Path tempDir)throws IOException {
        Path file = tempDir.resolve("validFile.txt");
        Files.write(file, List.of(
                "3", // Size of the GalacticMap
                "FIGHTER ID123 1 1 5", // A fighter spaceship
                "CARGOSHIP ID223 2 2 100 50 3 3", // A cargo ship
                "EXPLORER ID323 0 0 7" // An explorer spaceship
        ));

        FileReader f=new FileReader();
GalacticMap map=f.readFromFile(file.toString());

assertNotNull(map);
    }

    @org.junit.jupiter.api.Test
    void cargo_interact(){
        GalacticMap map= new GalacticMap(5);
        Spaceship cargo1= new CargoShip("c1234",1,1,3,1,1,0);
        Spaceship cargo2=new CargoShip("c1243",1,0,2,3,1,1);
        CargoShip ship=(CargoShip) cargo1;
        cargo1.interact(map,cargo2);
        double y=ship.getCurrentCargo();
        assertEquals(2,y);
    }
    @org.junit.jupiter.api.Test
    void cargo_interact2(){
        GalacticMap map= new GalacticMap(5);
        Spaceship cargo1= new CargoShip("c1234",1,1,1,1,1,0);
        Spaceship cargo2=new CargoShip("c1243",1,0,2,3,1,1);
        CargoShip ship=(CargoShip) cargo1;
        cargo1.interact(map,cargo2);
        double y=ship.getCurrentCargo();
        assertEquals(2,y);
    }
}