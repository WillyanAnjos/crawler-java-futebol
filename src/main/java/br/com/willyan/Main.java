package br.com.willyan;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String time = "barcelona";

        FootballScoreCrawler fsc = new FootballScoreCrawler();

        fsc.proximosJogosDoTime(time);
        fsc.titulosDoTime(time);
        fsc.jogdoresDoTime(time);
        fsc.ultimosJogosDoTime(time);
    }
}

