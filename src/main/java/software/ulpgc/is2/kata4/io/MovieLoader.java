package software.ulpgc.is2.kata4.io;

import software.ulpgc.is2.kata4.model.Movie;

import java.util.List;

public interface MovieLoader {
    List<Movie> loadAll();
}
