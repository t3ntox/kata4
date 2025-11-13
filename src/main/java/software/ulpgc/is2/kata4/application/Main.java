package software.ulpgc.is2.kata4.application;

import software.ulpgc.is2.kata4.model.Movie;
import software.ulpgc.is2.kata4.tasks.HistogramBuilder;
import software.ulpgc.is2.kata4.viewmodel.Histogram;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Movie> movieList = new RemoteMovieLoader(Main::fromTsv).loadAll();
        Histogram histogram = new HistogramBuilder(movieList).build(Movie::year);
        for (int bin: histogram) {
            System.out.println(bin + ": " + histogram.count(bin));
        }
    }

    private static Movie fromTsv(String line) {
        return fromTsv(line.split("\t"));
    }

    private static Movie fromTsv(String[] split) {
        return new Movie(
                split[2],
                toInt(split[5]),
                toInt(split[7])
        );
    }

    private static int toInt(String s) {
        if (s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }
}
