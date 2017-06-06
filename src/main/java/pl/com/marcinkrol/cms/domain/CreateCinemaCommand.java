package pl.com.marcinkrol.cms.domain;

public class CreateCinemaCommand implements Validatable {

    private String name;
    private String city;

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (name == null || isEmpty(name))
            errors.add("name", "is required");
        if (city == null || isEmpty(city))
            errors.add("city", "is required");
    }

}
