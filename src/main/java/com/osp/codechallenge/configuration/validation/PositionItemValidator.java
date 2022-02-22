package com.osp.codechallenge.configuration.validation;

import com.osp.codechallenge.dto.PositionItemDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PositionItemValidator implements ConstraintValidator<PositionItemConstraint, List<PositionItemDTO>> {

    @Override
    public void initialize(PositionItemConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<PositionItemDTO> positionItems, ConstraintValidatorContext constraintValidatorContext) {
        Set<PositionItemDTO> items = new HashSet<>();
        boolean result = false;
        if(positionItems != null) {
            Optional<PositionItemDTO> optionalPositionItem = positionItems.stream().filter(n -> !items.add(n)).findFirst();
            result = !optionalPositionItem.isPresent();
        }
        return result;
    }
}
