import java.util.Random;
/**
 * The FighterShip class represents a spaceship specialized in combat within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class FighterShip extends Spaceship {
    private int damage;// The damage inflicted by the fighter ship during combat
    private int x;
    private int y;
    private String id;

    /**
     * Constructs a FighterShip object with the specified attributes.
     *
     * @param id     The unique identifier of the fighter ship.
     * @param x      The initial x-coordinate of the fighter ship.
     * @param y      The initial y-coordinate of the fighter ship.
     * @param damage The damage inflicted by the fighter ship during combat.
     */
    public FighterShip(String id, int x, int y, int damage) {
        super(id,x,y,SpaceshipType.FIGHTER);
        // Initialize FighterShip attributes
        this.damage=damage;
        this.x=x;
        this.y=y;
        this.id=id;
    }

    /**
     * Generates a random direction for the fighter ship to move.
     *
     * @return A random integer representing the direction (0 to 7).
     */
    private int getRandomDirection() {
        Random random = new Random();
        return random.nextInt(8); // Generates a random integer between 0 and 7
    }

    /**
     * Implements the movement behavior of the fighter ship within the galactic map.
     * The movement of the fighter ship is random, as it changes direction randomly.\
     *
     * ..............
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");
        int r=getRandomDirection();
        switch (r){
            case 0://moving up
                change(galacticMap,x-1,y);
                break;

            case 1://moving down
                change(galacticMap,x+1,y);
                break;

            case 2://moving left
                change(galacticMap,x,y-1);
                break;

            case 3://moving right
                change(galacticMap,x,y+1);
                break;

            case 4://moving downright
                change(galacticMap,x+1,y+1);
                break;

            case 5://moving downleft
                change(galacticMap,x+1,y-1);
                break;

            case 6://moving upright
                change(galacticMap,x-1,y+1);
                break;

            case 7://moving upleft
                change(galacticMap,x-1,y-1);
                break;
        }
        // Implementation for fighter ship movement
System.out.println("Move Configuration\n"+galacticMap.toString());
    }

    /**
     * Implements the interaction behavior of the fighter ship with another spaceship.
     * The fighter ship engages in combat with other spaceships during interaction.
     *
     * .........
     *
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());
        if(other.getType()==SpaceshipType.FIGHTER){
            System.out.println("fighters do not fight with fighters!");
        }
        else{
            if(calculateDistance(other)<=damage){
                galacticMap.removeSpaceshipAt(other.getX(),other.getY());
                System.out.println( getName()+ "destroyed spaceship:" +other.getType()+ other.getID());
                System.out.println("Interaction Configuration");
                System.out.println(galacticMap.toString());
            }
            else{
                System.out.println("damage is less than the distance!");
            }
        }
        // Implementation for fighter ship interaction (e.g., combat)

    }
    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(x,y);
        galacticMap.moveSpaceshipTo(f,newX,newY);

    }
}

