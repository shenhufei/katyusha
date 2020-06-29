package Katyusha.anntion;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodVersion {
    public String[] value() default { "" };
}
