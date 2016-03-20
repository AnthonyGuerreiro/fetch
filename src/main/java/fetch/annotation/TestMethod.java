package fetch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Used to mark a method exists or is public only to make it easier to test a
 * given feature.
 */
@Target(ElementType.METHOD)
public @interface TestMethod {

}
