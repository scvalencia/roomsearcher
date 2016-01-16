package edu.co.uniandes.scvalencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scvalencia606 on 1/15/16.
 */
public class Course {

    private Long CRN;
    private String subject;
    private int section;
    private double credits;
    private String title;
    private int capacity;
    private int enrolled;
    private int available;
    private List<String> instructors;

    public Course(Long CRN, String subject, int section, double credits, String title, int capacity, int enrolled, int available) {
        this.CRN = CRN;
        this.subject = subject;
        this.section = section;
        this.credits = credits;
        this.title = title;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.available = available;
        this.instructors = new ArrayList<String>();
    }

    public Long getCRN() {
        return CRN;
    }

    public void setCRN(Long CRN) {
        this.CRN = CRN;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }
}
