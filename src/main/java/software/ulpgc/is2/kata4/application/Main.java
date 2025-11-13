package software.ulpgc.is2.kata4.application;

import software.ulpgc.is2.kata4.model.Movie;
import software.ulpgc.is2.kata4.tasks.HistogramBuilder;
import software.ulpgc.is2.kata4.viewmodel.Histogram;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Movie> movies = new RemoteStore(Main::fromTsv).movies()
                .filter(m -> m.year() > 1900)
                .filter(m -> m.year() < 2025)
                .limit(10_000);
        Histogram histogram = HistogramBuilder.with(movies)
                .title("Movies per year")
                .x("Year")
                .y("Frequency")
                .legend("Movies")
                .build(Movie::year);
        MainFrame.create()
                .display(histogram)
                .setVisible(true);
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
