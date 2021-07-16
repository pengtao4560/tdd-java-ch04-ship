package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    private final Location location;

    public Location getLocation() {
        return location;
    }

    private Planet planet;

    public Planet getPlanet() {
        return planet;
    }

    //    public Ship(Location location) {
//        this.location = location;
//    }
    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public boolean moveForward() {
//        return location.forward();
        return location.forward(planet.getMax(), planet.getObstacles());
    }

    public boolean moveBackward() {
//        return location.backward();
        return location.backward(planet.getMax(), planet.getObstacles());
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public String receiveCommands(String commands) {
        String result = "";
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'f':
               /*     if(moveForward()){
                        result = result + "O";
                    } else {
                        result = result + "X";
                    }
*/
                    result = moveForward() ? result + 'O' : result + 'X';
                    break;
                case 'b':
           /*         if(moveBackward()){
                        result = result + "O";
                    } else {
                        result = result + "X";
                    }*/
                    result = moveBackward() ? result + 'O' : result + 'X';
                    break;
                case 'l':
                    turnLeft();
                    result += "O";
                    break;
                case 'r':
                    result += "O";
                    turnRight();
                    break;
            }
        }
        return result;
    }

}
