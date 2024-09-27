package com.back.producttrial.validator;

import com.back.producttrial.enumeration.InventoryReference;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InventoryReferenceValidator implements ConstraintValidator<InventoryReferencePattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(InventoryReference.values()).anyMatch(inventoryReference -> inventoryReference.name().equals(value));
    }
}