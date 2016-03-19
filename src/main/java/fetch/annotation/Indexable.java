package fetch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Indexable {
    /**
     * Returns true if annotated interface must have only one instance in
     * runtime.
     *
     * @return true if annotated interface must have only one instance in
     *         runtime
     */
    boolean isSingleInstance() default false;
}
