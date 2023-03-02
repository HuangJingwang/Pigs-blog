package com.pigs.blog.common;

import com.google.common.collect.Lists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EnumStringValidator implements ConstraintValidator<EnumValidate,String> {

    private List<String> enumStrings;

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        enumStrings = Lists.newArrayList(constraintAnnotation.enums());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // 对于Integer，将其转化为String 后比较
        return enumStrings.contains(value);
    }
}


