package HistoryAppGradleSecurity.model.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PropertyValidator.class)
public @interface PropMatch {
    String message() default "Fields should match";

    String first();
    String second();

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
