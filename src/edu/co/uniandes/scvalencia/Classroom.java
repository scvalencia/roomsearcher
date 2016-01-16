package edu.co.uniandes.scvalencia;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

/**
 * Created by scvalencia606 on 1/15/16.
 */
public class Classroom {

    private Building building;
    private String number;
    private HashMap<Character, List<Timeline>> classes;

    public Classroom(Building building, String number) {
        this.building = building;
        this.number = number;
        this.classes = new HashMap<Character, List<Timeline>>();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public HashMap<Character, List<Timeline>> getClasses() {
        return classes;
    }

    public void setClasses(HashMap<Character, List<Timeline>> classes) {
        this.classes = classes;
    }
}
