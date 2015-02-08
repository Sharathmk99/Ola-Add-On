
package com.hack.hotelpojo;

import com.google.gson.annotations.Expose;


public class Geometry {

    @Expose
    private Location location;

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
