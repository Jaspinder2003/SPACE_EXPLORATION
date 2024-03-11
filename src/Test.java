import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class Test {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
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

            int initialX = fighter1.getX();
            int initialY = fighter1.getY();
            fighter1.move(galacticMap);
            // The ship should have moved; thus, either X or Y should be different.
            assertTrue(fighter1.getX() != initialX || fighter1.getY() != initialY);

    }
    @org.junit.jupiter.api.Test
    void fightershipmove2(){
        GalacticMap galacticMap=new GalacticMap(5);
        Spaceship fighter1=new FighterShip("f1234",0,0,1);
        Spaceship explorer1 = new ExplorerShip("e1234",0,1,1);
        Spaceship explorer2 = new ExplorerShip("e1235",1,0,1);
        Spaceship explorer3 = new ExplorerShip("e1236",1,1,1);
        galacticMap.placeSpaceship(fighter1);
        galacticMap.placeSpaceship(explorer1);
fighter1.move(galacticMap);
        String output = outContent.toString();
        boolean b=output.contains("Moving Failed! the position is filled with another spaceship")||output.contains("Moving Failed! out of bound x or y!");
        assertTrue(b);
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
    public void testInteractionWithinScanRange() {
        GalacticMap map = new GalacticMap(10);
        ExplorerShip explorer = new ExplorerShip("E1245", 5, 5, 3);
        Spaceship Fighter = new FighterShip("S1234", 5, 6, 5);
        map.placeSpaceship(explorer);
        map.placeSpaceship(Fighter);

        explorer.interact(map, Fighter);

        String output = outContent.toString();
        assertTrue(output.contains("Found: FIGHTER S1234 at distance 1"));
    }
    @org.junit.jupiter.api.Test
    public void testInteractionoutsideScanRange() {
        GalacticMap map = new GalacticMap(10);
        ExplorerShip explorer = new ExplorerShip("E1245", 5, 5, 1);
        Spaceship Fighter = new FighterShip("S1234", 8, 8, 5);
        map.placeSpaceship(explorer);
        map.placeSpaceship(Fighter);

        explorer.interact(map, Fighter);

        String output = outContent.toString();
        assertTrue(output.contains("Spaceship: " + Fighter.getType() + Fighter.getID()+" is not in the scan-range"));
    }

    @org.junit.jupiter.api.Test
    void File_readertest(@TempDir Path tempDir) throws IOException {

        Path file = tempDir.resolve("invalidSizeFile.txt");
        Files.write(file, List.of(
                "notAnInteger", // Invalid size value
                "FIGHTER ID1 1 1 5" // Example spaceship entry
        ));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FileReader.readFromFile(file.toString());
        });

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
        Spaceship cargo1= new CargoShip("c1234",1,1,2,1,1,0);
        Spaceship cargo2=new CargoShip("c1243",1,0,2,3,1,1);
        CargoShip ship=(CargoShip) cargo1;
        CargoShip ship2=(CargoShip) cargo2;
        cargo1.interact(map,cargo2);
        double y=ship.getCurrentCargo();
        double y2=ship.getCurrentCargo();
        assertEquals(2,y2);
    }
    @org.junit.jupiter.api.Test
    public void spaceshipmovetes() {
        // Setup: Create a GalacticMap and a FighterShip placed within the map
        int mapSize = 10; // Assuming a 10x10 map for simplicity
        GalacticMap map = new GalacticMap(mapSize);
        FighterShip fighter = new FighterShip("F1", 5, 5, 2); // Starting near the center
        map.placeSpaceship(fighter);


        for (int i = 0; i < 100; i++) {
            fighter.move(map);
            // Verify the ship remains within the map boundaries after each move
            assertTrue(fighter.getX() >= 0 && fighter.getX() < mapSize && fighter.getY() >= 0 && fighter.getY() < mapSize,
                    "FighterShip moved out of map boundaries to (" + fighter.getX() + ", " + fighter.getY() + ")");
        }

}}