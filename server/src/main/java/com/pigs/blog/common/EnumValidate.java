package com.pigs.blog.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface EnumValidate {

    /**
     * 校验报错信息
     *
     * @return
     */
    String message() default "";

    /**
     * 校验分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 附件 用于扩展
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 允许的枚举值，所有类型转成String 存储
     * @return
     */
    String[] enums() default {};

}

