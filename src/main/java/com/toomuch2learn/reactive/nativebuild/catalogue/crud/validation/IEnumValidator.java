package com.toomuch2learn.reactive.nativebuild.catalogue.crud.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IEnumValidator {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
