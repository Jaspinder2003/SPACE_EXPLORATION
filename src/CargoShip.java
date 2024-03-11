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
    private int checkX=0;//to check if X or Target X is higher.

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
/**
 * uses super() to inherit from spaceship class
 */
        this.targetX=targetX;
        this.targetY=targetY;
        this.currentCargo=currentCargo;
        this.cargoCapacity=cargoCapacity;
        checkX=getX()-targetX;
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
        /**
         * use if and else to see if a move is even valid or possible by checking
         * if there is already a spaceship at the new position
         */
        if(getX()==targetX&&getY()==targetY){
            System.out.println("CargoShip: c-"+id.charAt(1)+id.charAt(2)+id.charAt(3)+id.charAt(4)+" is already in destination");
        }
        else {
            System.out.print("........Moving.......");
            if (checkX > 0) {
                if(galacticMap.getSpaceshipAt(getX()-1,getY())==null){
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX()-1)+","+ getY()+")\n");
                change(galacticMap, getX() - 1, getY());} else if (galacticMap.getSpaceshipAt(getX()-1,getY())!=null&&getY()>targetY) {
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX())+","+ (getY()-1)+")\n");
                    change(galacticMap,getX(),getY()-1);
                } else if (galacticMap.getSpaceshipAt(getX()-1,getY())!=null&&getY()<targetY){
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX())+","+ (getX()+1)+")\n");
                    change(galacticMap,getX(),getY()+1);
                }

            } else if(checkX<0) {
                if(galacticMap.getSpaceshipAt(getX()+1,getY())==null){
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX()+1)+","+ getY()+")\n");
                    change(galacticMap, getX() + 1, getY());} else if (galacticMap.getSpaceshipAt(getX()+1,getY())!=null&&getY()>targetY) {
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX())+","+ (getY()-1)+")\n");
                    change(galacticMap,getX(),getY()-1);
                } else if (galacticMap.getSpaceshipAt(getX()+1,getY())!=null&&getY()<targetY){
                    System.out.println("Start moving CARGOSHIP"+ id+" to the position: "+"("+(getX())+","+ (getY()+1)+")\n");
                    change(galacticMap,getX(),getY()+1);
                }
            }

            else{
                if(getY()<targetY){
                    change(galacticMap,getX(),getY()+1);
                } else if (getY()>targetY) {
                    change(galacticMap,getX(),getY()-1);
                }
            }
            // CargoShip-specific movement logic
        }
        /**
         * used to print th last statement
         */
        System.out.println("Move Configuration\n"+galacticMap.toString());
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
        if(other.getX()==getX() && other.getY()==getY()){
            System.out.println("CargoShip cannot interact with itself");
        }
        /**
         * Cargoship cannot interact with itself or fighter or explorer
         */
        else if(other.getType()==SpaceshipType.FIGHTER){
            System.out.println("CargoShip cannot interact with FIGHTER");
        }
        else if(other.getType()==SpaceshipType.EXPLORER){
            System.out.println("CargoShip cannot interact with EXPLORER");
        }
        /**
         * used to load or unload depending on the difference between the currentload
         */
        else if(other.getType()==SpaceshipType.CARGOSHIP){
            CargoShip ship=(CargoShip) other;
            double cc=ship.currentCargo;
            if(cc<currentCargo){
                x=(currentCargo-cc)/2;
                if(ship.currentCargo+x>ship.cargoCapacity){
                    System.out.println("Cargo capacity exceeded! Cannot load cargo onto C-" + ship.getID());
                }
                else{
                ship.loadCargo(x);
                if(currentCargo<x){
                    System.out.println("Cannot unload more cargo than what's currently onboard.");
                }else{
                unloadCargo(x);}}
            }
            else if (cc>currentCargo) {
               x=(cc-currentCargo)/2;
               if(ship.currentCargo<x){
                   System.out.println("Cannot unload more cargo than what's currently onboard.");
               }else{
               ship.unloadCargo(x);
               if(currentCargo+x>cargoCapacity){
                   System.out.println("Cargo capacity exceeded! Cannot load cargo onto C-" + id);
               }else{
               loadCargo(x);}
            }}
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

    /**
     *
     * @param galacticMap
     * @param newX
     * @param newY
     */
    private void change(GalacticMap galacticMap,int newX,int newY){
        Spaceship f= galacticMap.getSpaceshipAt(getX(),getY());
        galacticMap.moveSpaceshipTo(f,newX,newY);
    }
    /**
     * this function is used in changing the coordinates of the spaceship
     */
}

