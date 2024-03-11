/**
 * The CargoShip class represents a spaceship specialized in transporting cargo within the galactic space.
 * It inherits from the Spaceship class and defines specific movement, interaction, and cargo management behaviors.
 * @author Parisa Daeijavad
 */
public class CargoShip extends Spaceship {

    private double cargoCapacity; // Maximum cargo capacity of the CargoShip
    private double currentCargo;  // Current amount of cargo on the CargoShip

    private int targetX; // The x-coordinate of the cargo ship's destination.
    private int targetY; // The y-coordinate of the cargo ship's destination.
    private int X=0;
    private int Y=0;
    private int checkX=0;

    private boolean reachedDestination; // Indicates if the cargo has reached its destination
    String id="";

    /**
     * Constructs a CargoShip object with the specified attributes.
     *
     * @param id            The unique identifier of the cargo ship.
     * @param x             The initial x-coordinate of the cargo ship.
     * @param y             The initial y-coordinate of the cargo ship.
     * @param cargoCapacity The maximum cargo capacity of the cargo ship.
     * @param currentCargo  The current amount of cargo on the cargo ship.
     * @param targetX       The x-coordinate of the cargo ship's destination.
     * @param targetY       The y-coordinate of the cargo ship's destination.
     */
    public CargoShip(String id, int x, int y, double cargoCapacity, double currentCargo, int targetX, int targetY) {
        // Initialize CargoShip attributes properly
        super(id,x,y,SpaceshipType.CARGOSHIP);
        X=x;
        Y=y;
        this.targetX=targetX;
        this.targetY=targetY;
        this.currentCargo=currentCargo;
        this.cargoCapacity=cargoCapacity;
        checkX=X-targetX;
        this.id=id;
    }

    /**
     * Implements the movement behavior of the cargo ship within the galactic map.
     * The cargo ship moves towards its designated target coordinates.
     *
     * ....................
     */
    @Override
    public void move(GalacticMap galacticMap) {
        if(X==targetX&&Y==targetY){
            System.out.println("CargoShip: c-"+id.charAt(1)+id.charAt(2)+id.charAt(3)+id.charAt(4)+" is already in destination");
        }
        else {
            System.out.print("........Moving.......");
            if (checkX > 0) {
                if(galacticMap.getSpaceshipAt(X-1,Y)==null){
                change(galacticMap, X - 1, Y);} else if (galacticMap.getSpaceshipAt(X-1,Y)!=null&&Y>targetY) {
                    change(galacticMap,X,Y-1);
                } else if (galacticMap.getSpaceshipAt(X-1,Y)!=null&&Y<targetY){
                    change(galacticMap,X,Y+1);
                }

            } else if(checkX<0) {
                if(galacticMap.getSpaceshipAt(X+1,Y)==null){
                    change(galacticMap, X + 1, Y);} else if (galacticMap.getSpaceshipAt(X+1,Y)!=null&&Y>targetY) {
                    change(galacticMap,X,Y-1);
                } else if (galacticMap.getSpaceshipAt(X+1,Y)!=null&&Y<targetY){
                    change(galacticMap,X,Y+1);
                }
            }

            else{
                if(Y<targetY){
                    change(galacticMap,X,Y+1);
                } else if (Y>targetY) {
                    change(galacticMap,X,Y-1);
                }
            }
            // CargoShip-specific movement logic
        }
    }

    /**
     * Implements the interaction behavior of the cargo ship with another spaceship.
     * The cargo ship can exchange cargo with other cargo ships during interaction.
     *
     * ............
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        double x;
        System.out.println(".........interacting...........with.... " + other.getName());
        if(other.getX()==X && other.getY()==Y){
            System.out.println("CargoShip cannot interact with itself");
        }
        else if(other.getType()==SpaceshipType.FIGHTER){
            System.out.println("CargoShip cannot interact with FIGHTER");
        }
        else if(other.getType()==SpaceshipType.EXPLORER){
            System.out.println("CargoShip cannot interact with EXPLORER");
        }
        else if(other.getType()==SpaceshipType.CARGOSHIP){
            CargoShip ship=(CargoShip) other;
            double cc=ship.currentCargo;
            if(cc<currentCargo){
                x=(currentCargo-cc)/2;
                ship.loadCargo(x);
                unloadCargo(x);
            }
            else if (cc>currentCargo) {
               x=(cc-currentCargo)/2;
               ship.unloadCargo(x);
               loadCargo(x);
            }
        }
        // CargoShip interaction logic
    }

    /**
     * Loads cargo onto the CargoShip up to its maximum capacity.
     *
     * ...........
     */
    public void loadCargo(double cargoAmount) {
        if(currentCargo+cargoAmount<=cargoCapacity){
            currentCargo=currentCargo+cargoAmount;
        }
    }

    /**
     * Unloads cargo from the CargoShip.
     *
     * ..............
     */
    public void unloadCargo(double cargoAmount) {
        if(cargoAmount<=currentCargo){
            currentCargo=currentCargo-cargoAmount;
        }
    }

    /**
     * Retrieves the current amount of cargo on the CargoShip.
     *
     * @return The current cargo amount.
     */
    public double getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Retrieves the x-coordinate of the CargoShip's destination.
     *
     * @return The x-coordinate of the destination.
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Retrieves the y-coordinate of the CargoShip's destination.
     *
     * @return The y-coordinate of the destination.
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Sets the status of whether the CargoShip has reached its destination.
     *
     * @param b True if the CargoShip has reached its destination, false otherwise.
     */
    public void setReachedDestination(boolean b) {
        reachedDestination = b;
    }

    /**
     * Checks if the CargoShip has reached its destination.
     *
     * @return True if the CargoShip has reached its destination, false otherwise.
     */
    public boolean isReachedDestination() {
        return reachedDestination;
    }

    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(X,Y);
        galacticMap.moveSpaceshipTo(f,newX,newY);
        galacticMap.removeSpaceshipAt(X,Y);
    }
}

