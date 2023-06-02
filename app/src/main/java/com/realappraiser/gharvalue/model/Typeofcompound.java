package com.realappraiser.gharvalue.model;

@SuppressWarnings("ALL")
public class Typeofcompound {

    public int TypeOfCompoundId;
    public String Name;

    //to display object as a string in spinner
    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Typeofcompound){
            Typeofcompound c = (Typeofcompound ) obj;
            if(c.getName().equals(Name) && c.getTypeOfCompoundId()==TypeOfCompoundId ) return true;
        }

        return false;
    }

    public int getTypeOfCompoundId() {
        return TypeOfCompoundId;
    }

    public void setTypeOfCompoundId(int typeOfCompoundId) {
        TypeOfCompoundId = typeOfCompoundId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
