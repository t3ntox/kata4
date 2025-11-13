package software.ulpgc.is2.kata4.application;

import software.ulpgc.is2.kata4.io.MovieLoader;
import software.ulpgc.is2.kata4.model.Movie;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {
    private final Function<String, Movie> deserialize;

    public RemoteMovieLoader(Function<String, Movie> deserialize) {
        this.deserialize = deserialize;
    }

    @Override
    public List<Movie> loadAll() {
        try {
            return loadFrom(new URL("https://datasets.imdbws.com/title.basics.tsv.gz"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movie> loadFrom(URL url) throws IOException {
        return loadFrom(url.openConnection());
    }

    private List<Movie> loadFrom(URLConnection urlConnection) throws IOException {
        InputStream inputStream = unzip(urlConnection.getInputStream());
        return loadFrom(inputStream);
    }

    private List<Movie> loadFrom(InputStream inputStream) throws IOException {
        return loadFrom(toReader(inputStream));
    }

    private List<Movie> loadFrom(BufferedReader reader) throws IOException {
        List<Movie> list = new ArrayList<>();
        reader.readLine();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            list.add(toMovie(line));
        }
        return list;
    }

    private Movie toMovie(String line) {
        return deserialize.apply(line);
    }

    private BufferedReader toReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }
}
