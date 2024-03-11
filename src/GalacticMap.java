import java.util.HashSet;
import java.util.Random;

/**
 * The GalacticMap class represents the grid-based map of the galactic space.
 * It stores information about the positions of spaceships and provides methods
 * for managing the entities within the map.
 *
 * <p>This class contains functionality to add fighters, retrieve a list of reported spaceships,
 * and check three game winning conditions for the galactic space.</p>
 *
 * @author Parisa Daeijavad
 */
public class GalacticMap {
    private Spaceship[][] grid; // 2D array representing the grid of the galactic map
    private int fighterNumber = 0; // Counter for the number of fighters in the map
    private HashSet<Spaceship> reportList = new HashSet<Spaceship>(); // Set to store reported fighter spaceships

    /**
     * Constructs a GalacticMap object with the specified size.
     *
     * @param size The size of the grid for the galactic map.
     */
    public GalacticMap(int size) {
        this.grid = new Spaceship[size][size];
    }

    /**
     * Adds a fighter spaceship to the report list.
     *
     * @param fighter The fighter spaceship to add to the report list.
     */
    public void AddReportedFighter(FighterShip fighter) {
        reportList.add(fighter);
    }

    /**
     * Adds one to the count of fighters in the GalacticMap.
     */
    public void addOneFighter() {
        fighterNumber++;
    }

    /**
     * Retrieves the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to retrieve the spaceship.
     * @param y The y-coordinate of the position to retrieve the spaceship.
     * @return The spaceship at the specified coordinates.
     */
    public Spaceship getSpaceshipAt(int x, int y) {
        return this.grid[x][y];
    }

    /**
     * Retrieves a random spaceship from the GalacticMap.
     *
     * @return A random spaceship from the GalacticMap.
     */
    public Spaceship getRandomSpaceship() {
        Random random = new Random();
        Spaceship randomSpaceship = null;

        // Keep generating random coordinates until a non-null grid cell is found
        while (randomSpaceship == null) {
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            randomSpaceship = grid[randomX][randomY];
        }

        return randomSpaceship;
    }

    /**
     * Returns a string representation of the GalacticMap.
     *
     * ...............
     *
     */
    @Override
    public String toString() {
        String st="";//initialized a string st with null

        /**
         * looping through the whole grid
         */
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                /**
                 * checking if the specified location at the grid has a spaceship object
                 * and if it does what type it is
                 */
                if(getSpaceshipAt(i,j)!=null){

                    if(getSpaceshipAt(i, j).getID().charAt(0) == 'f'){
                        st=st+"[ "+"F-"+getSpaceshipAt(i, j).getID()+" ]";
                    }
                    else if(getSpaceshipAt(i, j).getID().charAt(0) == 'e'){
                        st=st+"[ "+"E-"+getSpaceshipAt(i, j).getID()+" ]";
                    }
                    else if (getSpaceshipAt(i, j).getID().charAt(0) == 'c') {
                        st=st+"[ "+"C-"+getSpaceshipAt(i, j).getID()+" ]";
                    }
                }
                else{

                        st=st+"[         ]";

                }
            }
            if(i!=grid.length-1){
            st=st+"\n";}
        }
        return st;
    }

    /**
     * Removes the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to remove the spaceship.
     * @param y The y-coordinate of the position to remove the spaceship.
     */
    public void removeSpaceshipAt(int x, int y) {
        this.grid[x][y] = null;
    }

    /**
     * Moves the specified spaceship to the new coordinates in the GalacticMap.
     *
     * ........
     *
     */
    public void moveSpaceshipTo(Spaceship spaceship, int newX, int newY) {
        if(isValidMove(newX,newY)){
            if(!isCollision(newX,newY)){
                spaceship.setX(newX);
                spaceship.setY(newY);
                placeSpaceship(spaceship);
            }
            else{
                System.out.println("Moving Failed! the position is filled with another spaceship!");
            }
        }
        else{
            System.out.println("“Moving Failed! out of bound x or y!");
        }
    }
    /**
     * Checks if the specified coordinates represent a valid move within the GalacticMap grid.
     *
     * ............
     *
     */
    private boolean isValidMove(int newX, int newY) {
        // Check if the new position is within the grid boundaries
        if(newX<= grid.length-1&&newY<=grid[0].length-1){
            return true;
        }
        else{
            return false;
        }
    }



    /**
     * Checks if the specified coordinates represent a collision with another spaceship.
     *
     * .......
     *
     */
    private boolean isCollision(int newX, int newY) {
        // Check if the new position is occupied by another spaceship
            if(grid[newX][newY]!=null){
                return true;
            }
            else{
                return false;
            }
    }


    /**
     * Places the specified spaceship, that is read from the file, in the GalacticMap.
     *
     * .............
     *
     */
    public void placeSpaceship(Spaceship spaceship) {
        if(!isValidMove(spaceship.getX(), spaceship.getY())){
            throw new IllegalArgumentException("Wrong input file! position is outside of the map!");
        }
        if(isCollision(spaceship.getX(), spaceship.getY())){
            throw new IllegalArgumentException("Wrong input file! the position is filled with another item!");
        }
        grid[spaceship.getX()][spaceship.getY()]=spaceship;
    }

    /**
     * Checks if all cargoes have reached their destinations.
     *
     * ..............
     *
     */
    public boolean allCargoesReachedDestination() {
        // Check if all cargoes have reached their destination
        int a=0;
        int b=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j].getType()==SpaceshipType.CARGOSHIP){
                    a++;
                    CargoShip ship=(CargoShip) grid[i][j];
                    if(ship.getX()==ship.getTargetX()&&ship.getY()==ship.getTargetY()){
                        b++;
                    }
                }
            }
        }
        if(a==b) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if all explorers and cargoes have been removed by fighters.
     *
     * ............
     *
     */
    public boolean allExplorersAndCargoesRemoved() {
        int a=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j].getType()==SpaceshipType.EXPLORER||grid[i][j].getType()==SpaceshipType.CARGOSHIP){
                    a++;
                }
            }
        }
        if(a==0){
        return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if all fighters have been reported by explorers.
     *
     * ................
     *
     */
    public boolean allFightersReported() {
        // Check if explorers have interacted/reported all fighters
        if(reportList.size()==fighterNumber){
            return true;
        }
        else{
            return false;
        }
    }
}
