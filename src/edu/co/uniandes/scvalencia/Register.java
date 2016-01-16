package edu.co.uniandes.scvalencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scvalencia606 on 1/15/16.
 */
public class Register {

    private Long CRN;
    private String subject;
    private int section;
    private double credits;
    private String title;
    private int capacity;
    private int enrolled;
    private int available;
    private List<Schedule> schedules;
    private List<String> instructors;

    public Register() {
    }

    public Register(Long CRN, String subject, int section, double credits, String title, int capacity, int enrolled, int available) {
        this.CRN = CRN;
        this.subject = subject;
        this.section = section;
        this.credits = credits;
        this.title = title;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.available = available;
        this.schedules = new ArrayList<Schedule>();
        this.instructors = new ArrayList<String>();
    }

    public Register(Long CRN, String subject, int section, double credits, String title, int capacity, int enrolled, int available, List<Schedule> schedules, List<String> instructors) {
        this.CRN = CRN;
        this.subject = subject;
        this.section = section;
        this.credits = credits;
        this.title = title;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.available = available;
        this.schedules = schedules;
        this.instructors = instructors;
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }
}

class Room {

    private String building;
    private String number;

    public Room(String building, String number) {
        this.building = building;
        this.number = number;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Room parseRoom(String str) {
        if(str.length() <= 1)
            return null;
        else {
            str = str.substring(1);
            String[] parse = str.split("_");

            if(parse.length == 2) {
                String building = parse[0].trim();
                String number = parse[1].trim();

                Room r = new Room(building, number);
                return r;
            } else
                return null;
        }
    }
}

class Schedule {

    private List<String> days;
    private Room room;
    private String initHour;
    private String finalHour;

    public Schedule(List<String> days, Room room, String initHour, String finalHour) {
        this.days = days;
        this.room = room;
        this.initHour = initHour;
        this.finalHour = finalHour;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public void addDay(String day) {
        this.days.add(day);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public static String[] parseInterval(String interval) {
        String[] ans = new String[2];
        String[] parse = interval.split("-");
        if(parse.length == 2 && parse[0] != "" && parse[1] != "") {
            ans[0] = parse[0].trim();
            ans[1] = parse[1].trim();
            return ans;
        }
        return null;
    }
}
