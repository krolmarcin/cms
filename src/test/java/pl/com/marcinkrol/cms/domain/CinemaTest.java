package pl.com.marcinkrol.cms.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CinemaTest {

    @Test
    public void shouldCreateCinema() {
        CreateCinemaCommand cmd = new CreateCinemaCommand();
        cmd.setName("Olimp");
        cmd.setCity("Lublin");

        Cinema cinema = new Cinema(cmd);

        assertEquals(cinema.getName(), "Olimp");
        assertEquals(cinema.getCity(), "Lublin");
    }

}
