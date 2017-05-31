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
    public void validate(ValidationError errors) {
        if (name == null || name.isEmpty())
            errors.add("name", "can't be blanked");
        if (city == null || city.isEmpty())
            errors.add("city", "can't be blanked");
    }

}
