import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        fighter1.move(galacticMap);
        Spaceship ship=galacticMap.getSpaceshipAt(2,2);
        assertEquals(fighter1,ship);
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
        assertEquals("Moving Failed! the position is filled with another spaceship!\n",output);

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
}