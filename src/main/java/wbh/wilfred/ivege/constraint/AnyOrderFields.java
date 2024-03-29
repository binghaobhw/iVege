package wbh.wilfred.ivege.constraint;

import wbh.wilfred.ivege.constraint.validator.AnyOrderFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AnyOrderFieldsValidator.class})
@Target(TYPE)
@Retention(RUNTIME)
public @interface AnyOrderFields {
    String message() default "{wbh.wilfred.ivege.constraint.AnyOrderFields.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
