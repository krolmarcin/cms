package pl.com.marcinkrol.cms.domain;

public class CreateCinemaCommand implements Validatable {

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(name))
            errors.add("name", REQUIRED_FIELD);
        if (isEmpty(city))
            errors.add("city", REQUIRED_FIELD);
    }

}
