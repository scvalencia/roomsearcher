package edu.co.uniandes.scvalencia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by scvalencia606 on 12/26/15.
 */
public class SubjectParser {

    String url;
    String code;
    boolean persistence;
    String path;

    public SubjectParser(String url, boolean persistence, String path) {
        this.url = url;
        this.code = url.substring(url.length() - 4, url.length());
        this.persistence = persistence;
        this.path = path;
    }

    public void parseContent() {
        String content = null;
        URLConnection connection = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            connection = new URL(this.url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Document document = Jsoup.parse(content);
        Element table = document.select("table[width=550][cellspacing=1]").first();
        Elements rows = table.select("tr");

        String html =
                "<html>                        " +
                        "  <body>                      " +
                        "    <table id='myTable'>      " +
                        "      <tbody>                 " +
                        "        <tr>                  " +
                        "          <th>header</th>     " +
                        "          <td>1</td>          " +
                        "            <table>           " +
                        "              <tbody>         " +
                        "                <tr>          " +
                        "                  <td>a1</td> " +
                        "                  <td>a2</td> " +
                        "                  <td>a3</td> " +
                        "                </tr>         " +
                        "              </tbody>        " +
                        "            </table>          " +
                        "          </td>               " +
                        "          <td>high level2</td>" +
                        "          <td>high level3</td>" +
                        "        </tr>                 " +
                        "      </tbody>                " +
                        "    </table>                  " +
                        "  </body>                     " +
                        "</html>                       ";

        Document doc = Jsoup.parse(html);
        Element dummy = doc.select("#myTable > tbody > tr > td").first();

        List<Element> cleaned = new ArrayList<Element>();

        for(int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            if(row.toString().contains("Magistral")) {
                cleaned.add(dummy);
                i += 2;
            }
            cleaned.add(row);
        }

        if(persistence) {

            List<Register> registers = new ArrayList<Register>();

            Long CRN = null;
            String subject = null;
            int section = 0;
            double credits = 0.0;
            String title = null;
            int capacity = 0;
            int enrolled = 0;
            int available = 0;
            List<Schedule> schedules = new ArrayList<Schedule>();
            List<String> instructors = new ArrayList<String>();

            boolean flag = true;

            for (int i = 0; i < cleaned.size(); i++) {

                Element row = cleaned.get(i);
                Elements trs = row.select("tr");
                if (trs.size() == 1) {
                    if (row.toString().contains(this.code)) {
                        List<String> parsedSubject = parseSubject(row);
                        try {
                            CRN = Long.parseLong(parsedSubject.get(0));
                            subject = parsedSubject.get(1);
                            section = Integer.parseInt(parsedSubject.get(2));
                            credits = Double.parseDouble(parsedSubject.get(3).replace(',', '.'));
                            title = parsedSubject.get(4);
                            capacity = Integer.parseInt(parsedSubject.get(5));
                            enrolled = Integer.parseInt(parsedSubject.get(6));
                            available = Integer.parseInt(parsedSubject.get(7));
                            flag = true;
                        } catch(Exception e) {
                            flag = false;
                        }
                        //System.out.println(Arrays.toString(parseSubject(row).toArray()));
                    } else if (row.toString().contains("18/01/16")) {
                        Schedule sc = parseSchedule(row);
                        if(sc != null)
                            schedules.add(sc);
                        //System.out.println(Arrays.toString(parseSchedule(row).toArray()));
                    } else if (row.toString().contains("Instructor(es)")) {
                        instructors = parseInstructor(row);
                        Register r = new Register(CRN, subject, section, credits, title, capacity, enrolled, available, schedules, instructors);
                        if(flag)
                            registers.add(r);
                        schedules = new ArrayList<Schedule>();
                        //System.out.println(Arrays.toString(parseInstructor(row).toArray()));
                    }
                }
            }

            try {
                mapper.writeValue(new File(path + "/" + this.code + "_courses.json"), registers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> parseSubject(Element row) {
        List<String> parsedResult = new ArrayList<String>();
        String code = row.select("td[width=\"39\"]").text();
        parsedResult.add(code);
        String subject = row.select("td[width=\"60\"]").text();
        parsedResult.add(subject);
        String section = row.select("td[width=\"53\"]").text();
        parsedResult.add(section);
        String credits = row.select("td[width=\"55\"]").text();
        parsedResult.add(credits);
        String title = row.select("td[width=\"156\"]").text();
        parsedResult.add(title);
        String capacity = row.select("td[width=\"69\"]").text();
        parsedResult.add(capacity);
        String enrolled = row.select("td[width=\"57\"]").text();
        parsedResult.add(enrolled);
        String available = row.select("td[width=\"77\"]").text();
        parsedResult.add(available);
        return parsedResult;
    }

    private Schedule parseSchedule(Element row) {
        List<String> days = Arrays.asList(row.select("td[width=\"172\"]").text().trim().split(" "));
        Elements timeLocation = row.select("td[width=\"171\"]");
        String hour = timeLocation.get(0).text();
        String[] interval = Schedule.parseInterval(hour);
        String location = timeLocation.get(1).text();
        Room room = Room.parseRoom(location);

        if(room == null || interval == null)
            return null;

        Schedule s = new Schedule(days, room, (interval == null) ? null : interval[0], (interval == null) ? null : interval[1]);
        return s;
    }

    private List<String> parseInstructor(Element row) {
        List<String> parsedResult = new ArrayList<String>();
        String professor = row.select("td[width=\"172\"]").text().trim();
        parsedResult.add(professor);
        Elements professors = row.select("td[width=\"171\"]");
        for(Element e : professors) {
            String name = e.text().trim();
            if(name.split(" ").length != 1)
                parsedResult.add(name);
        }
        return parsedResult;
    }
}
