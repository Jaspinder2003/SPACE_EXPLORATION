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
            change(galacticMap,X,Y+1);
            moveHorizontally=false;
        }
        else{
            change(galacticMap,X+1,Y);
            moveHorizontally=true;
        }

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
            if (Math.abs(X - other.getX()) <= scanRange && Math.abs(Y = other.getY()) <= scanRange) {
                int distance;
                if (X - other.getX() > Y - other.getY()) {
                    distance = X - other.getX();
                } else if (X - other.getX() < Y - other.getY()) {
                    distance = Y - other.getY();
                } else {
                    distance = X - other.getX();
                }
                System.out.println("Found: " + other.getType() + other.getID() + "at distnace " + distance);
            } else {
                System.out.println("Spaceship: " + other.getType() + other.getID()+"is not in the scan-range");
            }
            // Implementation for explorer ship interaction

        }
    }
    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(X,Y);
        galacticMap.moveSpaceshipTo(f,newX,newY);
        galacticMap.removeSpaceshipAt(X,Y);
    }
}
