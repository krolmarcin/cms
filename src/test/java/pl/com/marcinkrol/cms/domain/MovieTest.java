package pl.com.marcinkrol.cms.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MovieTest {

    @Test
    public void shouldCreateMovie() {
        CreateMovieCommand cmd = new CreateMovieCommand();
        cmd.setTitle("test title");

        Movie movie = new Movie(cmd);

        assertEquals(movie.getTitle(), "test title");
    }
}
