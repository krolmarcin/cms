package pl.com.marcinkrol.cms.infrastructure;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.com.marcinkrol.cms.domain.InvalidCommandException;
import pl.com.marcinkrol.cms.domain.Validatable;

@Component
@Aspect
public class ValidationAspect {

    @Before("execution(* pl.com.marcinkrol.cms.application..*.*(..)) && args(validatable,..)")
    public void validate(Validatable validatable) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        validatable.validate(errors);
        if (!errors.isValid())
            throw new InvalidCommandException(errors);
    }

}
