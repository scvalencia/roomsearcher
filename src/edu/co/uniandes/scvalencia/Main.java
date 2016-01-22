package edu.co.uniandes.scvalencia;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        URLRetriever urlr = new URLRetriever();
        List<String> subjectsURLS = urlr.getSubjectsScheduleURLS();

        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss") ;
        String path = "data/" + dateFormat.format(date);
        File dir = new File(path);
        dir.mkdir();

        for(String url : subjectsURLS) {
            SubjectParser sp = new SubjectParser(url, true, path);
            sp.parseContent();
        }
    }

}
