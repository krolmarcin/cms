package pl.com.marcinkrol.cms.domain;

import java.util.Set;

public class CreateMovieCommand implements Validatable {

    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private Integer minAge;
    private Integer length;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (title == null || isEmpty(title))
            errors.add("title", REQUIRED_FIELD);
        if (description == null || isEmpty(description))
            errors.add("description", REQUIRED_FIELD);
        if (actors == null || isEmpty(actors))
            errors.add("actors", REQUIRED_FIELD);
        if (genres == null || isEmpty(genres))
            errors.add("genres", REQUIRED_FIELD);
        if (minAge == null || isEmpty(minAge))
            errors.add("minAge", REQUIRED_FIELD);
        if (minAge != null && minAge <= 0)
            errors.add("minAge", GREATER_THAN_ZERO);
        if (length == null || isEmpty(length) || (length <= 0))
            errors.add("length", REQUIRED_FIELD);
        if (length != null && length <= 0)
            errors.add("length", GREATER_THAN_ZERO);
    }

}
