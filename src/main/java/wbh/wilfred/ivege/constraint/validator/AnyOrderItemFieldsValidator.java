package wbh.wilfred.ivege.constraint.validator;

import wbh.wilfred.ivege.constraint.AnyOrderItemFields;
import wbh.wilfred.ivege.model.OrderItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnyOrderItemFieldsValidator implements
        ConstraintValidator<AnyOrderItemFields, Object> {
    @Override
    public void initialize(AnyOrderItemFields constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof OrderItem) {
            OrderItem item = (OrderItem) value;
            if ((item.getDiscount() != null && item.getDiscount().getId() >= 1) ||
                    item.getQuantity() != null ||
                    item.getUnit() != null ||
                    item.getPrice() != null ||
                    item.getSubtotal() != null) {
                return true;
            }
        }
        return false;
    }
}
