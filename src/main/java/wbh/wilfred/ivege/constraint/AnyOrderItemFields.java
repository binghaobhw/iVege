package wbh.wilfred.ivege.constraint;

import wbh.wilfred.ivege.constraint.validator.AnyOrderItemFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AnyOrderItemFieldsValidator.class})
@Target(TYPE)
@Retention(RUNTIME)
public @interface AnyOrderItemFields {
    String message() default "{wbh.wilfred.ivege.constraint.AnyOrderItemFields.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
