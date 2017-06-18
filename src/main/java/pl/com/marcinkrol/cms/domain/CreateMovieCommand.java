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
            errors.add("title", "is required");
        if (description == null || isEmpty(description))
            errors.add("description", "is required");
        if (actors == null || isEmpty(actors))
            errors.add("actors", "is required");
        if (genres == null || isEmpty(genres))
            errors.add("genres", "is required");
        if (minAge == null || isEmpty(minAge))
            errors.add("minAge", "is required");
        if (minAge != null && minAge <= 0)
            errors.add("minAge", "must be greater then 0");
        if (length == null || isEmpty(length) || (length <= 0))
            errors.add("length", "is required");
        if (length != null && length <= 0)
            errors.add("length", "must be greater then 0");
    }

}
