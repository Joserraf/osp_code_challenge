package com.osp.codechallenge.configuration.validation;

import com.osp.codechallenge.documents.PositionItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PositionItemValidator implements ConstraintValidator<PositionItemConstraint, List<PositionItem>> {

    @Override
    public void initialize(PositionItemConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<PositionItem> positionItems, ConstraintValidatorContext constraintValidatorContext) {
        Set<PositionItem> items = new HashSet<>();

        Optional<PositionItem> optionalPositionItem = positionItems.stream().filter(n -> !items.add(n)).findFirst();
        return !optionalPositionItem.isPresent();
    }
}
