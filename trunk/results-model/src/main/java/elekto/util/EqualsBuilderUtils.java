package elekto.util;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * {@link EqualsBuilder} util methods.
 */
public class EqualsBuilderUtils {

    /**
     * Returns true if equals could be apply on {@code left} and {@code right} parameters, false else.
     * <p>
     * Useful to replace boilerplate code (see below) for {@link #equals(Object)} that {@link EqualsBuilder} does not
     * even provide.
     * 
     * <pre>
     * if (obj == null) {
     *     return false;
     * }
     * if (obj == this) {
     *     return true;
     * }
     * if (obj.getClass() != getClass()) {
     *     return false;
     * }
     * </pre>
     * 
     * @param left
     *        this object.
     * @param right
     *        the other object.
     * 
     * @return true if equals could be apply on {@code left} and {@code right} parameters, false else.
     * 
     * @see EqualsBuilder
     */
    public static boolean isEqualsCalculable(
            final Object left,
            final Object right)
    {
        if ((left == null) || (right == null)) {
            return false;
        }
        if (left == right) {
            return true;
        }
        if (left.getClass() != right.getClass()) {
            return false;
        }

        return true;
    }

}
