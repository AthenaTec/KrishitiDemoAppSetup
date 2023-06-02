package com.realappraiser.gharvalue.model;

@SuppressWarnings("ALL")
public class Nameofpersons {

    public int NameofVendorId;
    public String Name;

    //to display object as a string in spinner
    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Nameofpersons){
            Nameofpersons c = (Nameofpersons) obj;
            if(c.getName().equals(Name) && c.getNameofVendorId()==NameofVendorId) return true;
        }

        return false;
    }

    public int getNameofVendorId() {
        return NameofVendorId;
    }

    public void setNameofVendorId(int nameofVendorId) {
        NameofVendorId = nameofVendorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
