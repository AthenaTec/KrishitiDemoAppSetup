package com.realappraiser.gharvalue.model;

/**
 * Created by kaptas on 2/1/18.
 */

@SuppressWarnings("ALL")
public class InternalFloorModel {

    public int id;
    public int floorid;
    public String Name;


    //to display object as a string in spinner
    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof InternalFloorModel){
            InternalFloorModel c = (InternalFloorModel ) obj;
            if(c.getName().equals(Name) && c.getId()==id ) return true;
        }

        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getFloorid() {
        return floorid;
    }

    public void setFloorid(int floorid) {
        this.floorid = floorid;
    }
}
