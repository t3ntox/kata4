package software.ulpgc.is2.kata4.io;

import software.ulpgc.is2.kata4.model.Movie;

import java.util.List;
import java.util.stream.Stream;

public interface Store {
    Stream<Movie> movies();
}
