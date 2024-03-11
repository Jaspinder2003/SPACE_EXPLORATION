/**
 * The ExplorerShip class represents a spaceship specialized in exploration within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class ExplorerShip extends Spaceship {
    private int scanRange; // The range within which the explorer ship can scan for nearby spaceships
    private boolean moveHorizontally = true; // Flag to track horizontal movement

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
        this.scanRange = scanRange;
/**
 * used to inherit from the super class
 */
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
        /**
         * used to check where the spaceship will move according to the logic given in the assignment
         */
        if(moveHorizontally){
            System.out.println("Start moving EXPLORER"+ getID() +"to the position: "+"("+getX()+","+ (getY()+1)+")\n");
            change(galacticMap,getX(),getY()+1);
            moveHorizontally=false;
        }
        else{
            System.out.println("Start moving EXPLORER"+ getID() +"to the position: "+"("+(getX()+1)+","+ getX()+")\n");
            change(galacticMap,getX()+1,getY());
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

        /**
         * used to explore the surroundings upto the scan range.
         */
        if(getX()==other.getX()&&getY()==other.getY()){
            System.out.println("the spaceship cannot interact with itself");
        }
        else {
            System.out.println("Start exploring: "+other.getName());
            if (calculateDistance(other)<=scanRange) {
                int distance=calculateDistance(other);

                System.out.println("Found: "+other.getName()+ " at distance " + distance);
                if(other.getType().equals(SpaceshipType.FIGHTER)){
                    FighterShip ship=(FighterShip) other;
                    galacticMap.AddReportedFighter(ship);
                }
            } else {
                System.out.println("Spaceship: " + other.getType() + other.getID()+" is not in the scan-range");
            }
            // Implementation for explorer ship interaction

        }
    }
    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(getX(),getY());
        galacticMap.moveSpaceshipTo(f,newX,newY);
        /**
         * again the same function is used to change the coordinates of the spaceship
         */
    }
}
