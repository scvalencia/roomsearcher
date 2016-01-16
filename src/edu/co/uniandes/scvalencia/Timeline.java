package edu.co.uniandes.scvalencia;

import java.util.ArrayList;

/**
 * Created by scvalencia606 on 1/15/16.
 */
public class Timeline {

    private String initHour;
    private String finalHour;
    private Course course;

    public Timeline(String initHour, String finalHour, Course course) {
        this.initHour = initHour;
        this.finalHour = finalHour;
        this.course = course;
    }

    public String getInitHour() {
        return initHour;
    }

    public void setInitHour(String initHour) {
        this.initHour = initHour;
    }

    public String getFinalHour() {
        return finalHour;
    }

    public void setFinalHour(String finalHour) {
        this.finalHour = finalHour;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
