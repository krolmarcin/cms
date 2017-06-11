package pl.com.marcinkrol.cms.integration;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CmsApplicationTest {

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
    public void createCinema() throws Exception {
        Cinema cinema = new Cinema("Olimp", "Lublin");

        mockMvc.perform(put("/cinemas").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(cinema))
        ).andExpect(status().isOk());
    }

    @Test
    public void validationErrorMissingParamsWhenCreateCinema() throws Exception {
        Cinema cinema = new Cinema(null, null);
        mockMvc.perform(
                put("/cinemas").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(cinema))
        ).andExpect(status().isUnprocessableEntity()).
                andExpect(jsonPath("$.errors.name").value("is required")).
                andExpect(jsonPath("$.errors.city").value("is required"));
    }

    @Test
    public void shouldShowErrorWhenTryToPutCinemaWithTheSameNameAndCity() throws Exception {
        saveCinema("Olimp", "Lublin");

        Cinema cinema = new Cinema("Olimp", "Lublin");
        mockMvc.perform(
                put("/cinemas").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(cinema))
        ).andExpect(status().isUnprocessableEntity()).
                andExpect(jsonPath("$.error").value("Cinema has already been created"));
    }

    private void saveCinema(String name, String city) throws Exception {
        Cinema cinema = new Cinema(name, city);
        mockMvc.perform(
                put("/cinemas").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(cinema))
        ).andExpect(status().isOk());
    }

    class Cinema {

        private String name;
        private String city;

        public Cinema(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }
    }

}
