package edu.co.uniandes.scvalencia;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        URLRetriever urlr = new URLRetriever();
        List<String> subjectsURLS = urlr.getSubjectsScheduleURLS();

        for(String url : subjectsURLS) {
            SubjectParser sp = new SubjectParser(url, true);
            sp.parseContent();
        }
    }
}
