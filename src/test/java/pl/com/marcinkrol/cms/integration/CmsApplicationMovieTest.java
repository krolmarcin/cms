package pl.com.marcinkrol.cms.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CmsApplicationMovieTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DbCleaner dbCleaner;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void cleanDb() {
        dbCleaner.clean();
    }

    @Test
    public void createMovie() throws Exception {
        Set<String> actors = new HashSet<>();
        actors.add("actor1");
        actors.add("actor2");
        Set<String> genres = new HashSet<>();
        genres.add("genre1");
        genres.add("genre2");
        Movie movie = new Movie("test1", "desc1", actors, genres, 16, 120);

        mockMvc.perform(put("/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movie))
        ).andExpect(status().isOk());
    }

    @Test
    public void validationErrorMissingParamsWhenCreateMovie() throws Exception {
        Movie movie = new Movie(null, null, null, null, null, null);

        mockMvc.perform(put("/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movie))
        ).andExpect(status().isUnprocessableEntity()).
                andExpect(jsonPath("$.errors.title").value("is required")).
                andExpect(jsonPath("$.errors.description").value("is required")).
                andExpect(jsonPath("$.errors.actors").value("is required")).
                andExpect(jsonPath("$.errors.genres").value("is required")).
                andExpect(jsonPath("$.errors.minAge").value("is required")).
                andExpect(jsonPath("$.errors.length").value("is required"));
    }

    private void saveMovie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) throws Exception {
        Movie movie = new Movie(title, description, actors, genres, minAge, length);
        mockMvc.perform(put("/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movie))).
                andExpect(status().isOk());
    }

    class Movie {
        private String title;
        private String description;
        private Set<String> actors;
        private Set<String> genres;
        private Integer minAge;
        private Integer length;

        public Movie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) {
            this.title = title;
            this.description = description;
            this.actors = actors;
            this.genres = genres;
            this.minAge = minAge;
            this.length = length;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Set<String> getActors() {
            return actors;
        }

        public Set<String> getGenres() {
            return genres;
        }

        public Integer getMinAge() {
            return minAge;
        }

        public Integer getLength() {
            return length;
        }
    }

}
