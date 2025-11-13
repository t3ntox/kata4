package software.ulpgc.is2.kata4.tasks;

import software.ulpgc.is2.kata4.model.Movie;
import software.ulpgc.is2.kata4.viewmodel.Histogram;

import java.util.List;
import java.util.function.Function;

public class HistogramBuilder {
    private final List<Movie> movies;

    public HistogramBuilder(List<Movie> movies) {
        this.movies = movies;
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram();
        for (Movie movie: movies) {
          histogram.put(binarize.apply(movie));
        }
        return histogram;
    }
}
