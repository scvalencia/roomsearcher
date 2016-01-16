package edu.co.uniandes.scvalencia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by scvalencia606 on 12/26/15.
 */
public class URLRetriever {

    final static String BASE_URL = "https://registroapps.uniandes.edu.co/scripts/semestre/adm_con_horario_joomla.php";

    List<String> codes;

    public URLRetriever() throws IOException {

        codes = new ArrayList<>();

        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL(BASE_URL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        Document document = Jsoup.parse(content);
        Elements table = document.select("table[width=600][bgcolor=#FFFFFF]");

        for (Element row : table.select("tr")) {
            for (Element col : row.select("td")) {
                Element subject = col.select("a").first();
                String link = subject.attr("href");

                //String name = subject.text();

                String code = link.substring(46, 50);
                codes.add(code);
            }
        }
    }

    public List<String> getSubjectsScheduleURLS() {
        String url = "https://registroapps.uniandes.edu.co/scripts/semestre/adm_con_horario1_joomla.php?depto=";
        List<String> ans = new ArrayList<>();

        for(String code : this.codes) {
            ans.add(url + code);
        }

        return ans;
    }
}
