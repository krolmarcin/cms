package pl.com.marcinkrol.cms.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MovieTest {

    @Test
    public void shouldCreateMovie() {
        CreateMovieCommand cmd = new CreateMovieCommand();
        cmd.setTitle("test title");
        cmd.setDescription("desc");
        cmd.setLength(120);
        cmd.setMinAge(12);

        Movie movie = new Movie(cmd);

        assertEquals(movie.getTitle(), "test title");
        assertEquals(movie.getDescription(), "desc");
        assertEquals(movie.getLength(), (Integer) 120);
        assertEquals(movie.getMinAge(), (Integer) 12);
    }
}
