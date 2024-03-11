/**
 * The ExplorerShip class represents a spaceship specialized in exploration within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class ExplorerShip extends Spaceship {
    private int scanRange; // The range within which the explorer ship can scan for nearby spaceships
    private boolean moveHorizontally = true; // Flag to track horizontal movement
    private int Y;
    private int X;
    private String id;

    /**
     * Constructs an ExplorerShip object with the specified attributes.
     *
     * @param id        The unique identifier of the explorer ship.
     * @param x         The initial x-coordinate of the explorer ship.
     * @param y         The initial y-coordinate of the explorer ship.
     * @param scanRange The range within which the explorer ship can scan for nearby spaceships.
     */
    public ExplorerShip(String id, int x, int y, int scanRange) {
        super(id, x, y, SpaceshipType.EXPLORER);
        X=x;
        Y=y;
        this.scanRange = scanRange;
        this.id=id;
    }

    /**
     * Implements the movement behavior of the explorer ship within the galactic map.
     * The explorer ship moves in a zigzag pattern, alternating between horizontal and vertical movements.
     *
     * .........
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");

        if(moveHorizontally){
            System.out.println("Start moving EXPLORER"+ id +"to the position: "+"("+X+","+ (Y+1)+")\n");
            change(galacticMap,X,Y+1);
            moveHorizontally=false;
        }
        else{
            System.out.println("Start moving EXPLORER"+ id +"to the position: "+"("+(X+1)+","+ Y+")\n");
            change(galacticMap,X+1,Y);
            moveHorizontally=true;
        }
System.out.println("Move Configuration\n"+galacticMap.toString());
        // Implementation for explorer ship movement
        // they move in a zigzag pattern, alternating between horizontal and vertical movements.

    }

    /**
     * Implements the interaction behavior of the explorer ship with another spaceship.
     * The explorer ship reports nearby spaceships found within its scan range during interaction.
     *
     * ......
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());

        if(X==other.getX()&&Y==other.getY()){
            System.out.println("the spaceship cannot interact with itself");
        }
        else {
            System.out.println("Start exploring: "+other.getName());
            if (calculateDistance(other)<=scanRange) {
                int distance=calculateDistance(other);

                System.out.println("Found: "+other.getName()+ " at distance " + distance);
                if(other.getType().equals(SpaceshipType.FIGHTER)){
                    FighterShip ship=(FighterShip) other;

                }
            } else {
                System.out.println("Spaceship: " + other.getType() + other.getID()+" is not in the scan-range");
            }
            // Implementation for explorer ship interaction

        }
    }
    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(X,Y);
        galacticMap.moveSpaceshipTo(f,newX,newY);

    }
}
