package com.back.producttrialmaster.validator;

import com.back.producttrialmaster.enumeration.InventoryReference;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InventoryReferenceValidator implements ConstraintValidator<InventoryReferencePattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(InventoryReference.values()).anyMatch(inventoryReference -> inventoryReference.name().equals(value));
    }
}