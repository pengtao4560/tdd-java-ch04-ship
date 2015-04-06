package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    private final Location location;
    public Location getLocation() {
        return location;
    }

    public Ship(Location location) {
        this.location = location;
    }

    public boolean moveForward() {
        return location.forward();
    }

    public boolean moveBackward() {
        return location.backward();
    }

}
