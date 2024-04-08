package br.com.willyan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class FootballScoreCrawler {
    private static final String URL_BASE = "https://www.placardefutebol.com.br/";

    public void proximosJogosDoTime(String time) throws IOException {
        String url = URL_BASE+ "time/"+time.toLowerCase()+"/proximo-jogos";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements matches = doc.select(".match__lg");

            for (Element match : matches) {
                String league = match.select(".match__lg_card--league").text().trim();
                String homeTeam = match.select(".match__lg_card--ht-name").text().trim();
                String awayTeam = match.select(".match__lg_card--at-name").text().trim();
                String dateTime = match.select(".match__lg_card--datetime").text().trim();

                String homeTeamLogo = match.select(".match__lg_card--ht-logo img").attr("src");
                String awayTeamLogo = match.select(".match__lg_card--at-logo img").attr("src");

                System.out.println("Liga: " + league);
                System.out.println("Time da casa: " + homeTeam);
                System.out.println("Time visitante: " + awayTeam);
                System.out.println("Data e hora: " + dateTime);

                System.out.println("Logo do time da casa: " + homeTeamLogo);
                System.out.println("Logo do time visitante: " + awayTeamLogo);
                System.out.println("-".repeat(50));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void titulosDoTime(String time) {
        try {

            Document doc = Jsoup.connect(URL_BASE+ "time/"+time+"/titulos").get();

            Element content = doc.selectFirst("div.content__body");

            Elements rows = content.select("div.table__row");

            for (Element row : rows) {
                Element yearElement = row.selectFirst("div.table__row-cell.text");
                String year = yearElement.text();

                Element championshipElement = row.selectFirst("span.table__row-cell--text");
                String championship = championshipElement.text();

                System.out.println("Ano: " + year + ", Campeonato: " + championship);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jogdoresDoTime(String time) throws IOException {
        String url = URL_BASE + "time/"+time+"/jogadores";

        try {
            // Fazendo uma solicitação GET para a página da web
            Document doc = Jsoup.connect(url).get();

            // Encontrando todos os elementos da tabela de jogadores
            Elements rows = doc.select(".table__row");

            // Iterando sobre os elementos da tabela
            for (Element row : rows) {
                // Encontrando os elementos de cada linha da tabela
                Elements cells = row.select(".table__row-cell");

                // Iterando sobre os elementos da linha e imprimindo seus textos
                for (Element cell : cells) {
                    System.out.print(cell.text() + "\t");
                }
                System.out.println(); // Nova linha para a próxima entrada
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ultimosJogosDoTime(String time) throws IOException {
        String url = URL_BASE+ "time/"+time.toLowerCase()+"/ultimos-jogos";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements matches = doc.select(".match__lg");

            for (Element match : matches) {
                String league = match.select(".match__lg_card--league").text().trim();
                String homeTeam = match.select(".match__lg_card--ht-name").text().trim();
                String awayTeam = match.select(".match__lg_card--at-name").text().trim();
                String dateTime = match.select(".match__lg_card--datetime").text().trim();

                String homeTeamLogo = match.select(".match__lg_card--ht-logo img").attr("src");
                String awayTeamLogo = match.select(".match__lg_card--at-logo img").attr("src");

                String[] score = match.select(".match__lg_card--scoreboard").text().trim().split(" - ");
                int homeScore = Integer.parseInt(score[0]);
                int awayScore = Integer.parseInt(score[1]);

                System.out.println("Liga: " + league);
                System.out.println("Time da casa: " + homeTeam);
                System.out.println("Time visitante: " + awayTeam);
                System.out.println("Data e hora: " + dateTime);
                System.out.println("Placar: " + homeScore + " - " + awayScore);
                System.out.println("Logo do time da casa: " + homeTeamLogo);
                System.out.println("Logo do time visitante: " + awayTeamLogo);
                System.out.println("-".repeat(50));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}