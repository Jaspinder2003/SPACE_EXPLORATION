
# Space Exploration Simulation

This project is a simulation of a Space Exploration Game developed in Java. The game is designed with Object-Oriented Programming (OOP) concepts, utilizing file I/O, inheritance, polymorphism, and encapsulation.

## Project Overview

In the Space Exploration Game, players control different types of spaceships that explore a galaxy represented by a 2D grid. Spaceships move and interact with one another based on their specific attributes and roles. The game supports three types of spaceships: **ExplorerShip**, **FighterShip**, and **CargoShip**. The program simulates spaceship movements and interactions until one of the three win conditions is met.

## Features

- **Object-Oriented Design**: Implements abstract classes, inheritance, polymorphism, and enums.
- **File I/O**: Reads spaceship configuration from an input file (`config.txt`) and loads the galaxy map accordingly.
- **Spaceship Interactions**: Each spaceship type interacts differently with others:
    - **FighterShips** engage in combat.
    - **ExplorerShips** scan for other ships.
    - **CargoShips** transport goods to target destinations.
- **Win Conditions**: The game ends when:
    1. All CargoShips reach their destination.
    2. All ExplorerShips and CargoShips are destroyed by FighterShips.
    3. All FighterShips are reported by ExplorerShips.

## File Structure

- `Spaceship.java`: Abstract class defining common attributes and methods for all spaceship types.
- `ExplorerShip.java`: Extends `Spaceship`, implementing unique behavior for explorer ships.
- `FighterShip.java`: Extends `Spaceship`, implementing unique behavior for fighter ships.
- `CargoShip.java`: Extends `Spaceship`, implementing unique behavior for cargo ships.
- `GalacticMap.java`: Represents the grid map where spaceships move and interact.
- `FileReader.java`: Reads the configuration file to initialize the galaxy map.
- `Test.java`: Unit tests to ensure the correctness of implemented methods.

## Functions Implemented

The following core methods are implemented across various classes:

1. **FileReader**:
    - `readFromFile()`: Reads spaceship configuration from a file and initializes the galaxy map.
    
2. **GalacticMap**:
    - `toString()`: Visualizes the current state of the galaxy map.
    - `moveSpaceshipTo()`: Moves spaceships within the galaxy map.
    - `placeSpaceship()`: Places spaceships based on the configuration file.
    - Win condition checkers: `allCargoesReachedDestination()`, `allExplorersAndCargoesRemoved()`, `allFightersReported()`.

3. **ExplorerShip**, **FighterShip**, **CargoShip**:
    - `move()`: Defines movement patterns specific to each spaceship type.
    - `interact()`: Implements interactions between spaceships based on their types.

## Unit Testing

- **FileReader**, **GalacticMap**, and each spaceship type are unit tested to ensure correct behavior.
- A total of 15 unit tests are required, covering the key methods in each class.

## Technology Stack

- **Language**: Java 21
- **Unit Testing**: JUnit 5
- **Version Control**: Git (GitLab hosted on the university server)

## Setup & Running the Project

1. **Clone the repository**:
    ```bash
    git clone <repository_link>
    ```
2. **Compile the Java files**:
    ```bash
    javac *.java
    ```
3. **Run the game**:
    ```bash
    java Game
    ```
4. **Run the unit tests**:
    ```bash
    java -jar junit-platform-console-standalone-1.8.1.jar --class-path . --scan-class-path
    ```

## Git Version Control

- The repository is hosted on **csgit.ucalgary.ca**.
- The codebase contains multiple regular commits to demonstrate the development process, as per the course requirements.

## Author

- **Jaspinder Singh Maan**

