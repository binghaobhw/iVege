package wbh.wilfred.ivege.constraint.validator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import wbh.wilfred.ivege.constraint.AnyOrderFields;
import wbh.wilfred.ivege.model.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnyOrderFieldsValidator implements ConstraintValidator<AnyOrderFields, Object> {
    @Override
    public void initialize(AnyOrderFields constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof Order) {
            Order order = (Order) value;
            if (StringUtils.isNotBlank(order.getName()) ||
                    StringUtils.isNotBlank(order.getPhone()) ||
                    StringUtils.isNotBlank(order.getAddress()) ||
                    CollectionUtils.isNotEmpty(order.getItems()) ||
                    order.getOriginalTotal() != null ||
                    order.getTotal() != null ||
                    order.getDeliverTime() != null ||
                    order.getCompleteTime() != null ||
                    order.getStatus() != null ||
                    order.getDiscount() != null ||
                    order.getGift() != null) {
                return true;
            }
        }
        return false;
    }
}
