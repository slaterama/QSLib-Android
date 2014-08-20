package com.slaterama.qslib.utils.objectscompat;

import android.os.Build;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This singleton implements the "Initialization-on-demand holder idiom" for singletons in Java
 * as described here: http://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
 */
public abstract class ObjectsCompat {

	/**
	 * Retrieves a singleton instance of ObjectsCompat.
	 * @return ObjectsCompat instance.
	 */
	public static ObjectsCompat getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * Static class that creates the appropriate instance based on Android build version.
	 */
	private static class LazyHolder {
		private static ObjectsCompat createInstance() {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
				return new ObjectsCompatKitKat();
			else
				return new ObjectsCompatBase();
		}

		private static final ObjectsCompat INSTANCE = createInstance();
	}

	/**
	 * Returns {@code true} if the arguments are equal to each other
	 * and {@code false} otherwise.
	 * Consequently, if both arguments are {@code null}, {@code true}
	 * is returned and if exactly one argument is {@code null}, {@code
	 * false} is returned.  Otherwise, equality is determined by using
	 * the {@link Object#equals equals} method of the first
	 * argument.
	 *
	 * @param a an object
	 * @param b an object to be compared with {@code a} for equality
	 * @return {@code true} if the arguments are equal to each other
	 * and {@code false} otherwise
	 * @see Object#equals(Object)
	 */
	abstract public boolean equals(Object a, Object b);

	/**
	 * Returns {@code true} if the arguments are deeply equal to each other
	 * and {@code false} otherwise.
	 * <p/>
	 * Two {@code null} values are deeply equal.  If both arguments are
	 * arrays, the algorithm in {@link Arrays#deepEquals(Object[],
	 * Object[]) Arrays.deepEquals} is used to determine equality.
	 * Otherwise, equality is determined by using the {@link
	 * Object#equals equals} method of the first argument.
	 *
	 * @param a an object
	 * @param b an object to be compared with {@code a} for deep equality
	 * @return {@code true} if the arguments are deeply equal to each other
	 * and {@code false} otherwise
	 * @see Arrays#deepEquals(Object[], Object[])
	 * @see #equals(Object, Object)
	 */
	abstract public boolean deepEquals(Object a, Object b);

	/**
	 * Returns the hash code of a non-{@code null} argument and 0 for
	 * a {@code null} argument.
	 *
	 * @param o an object
	 * @return the hash code of a non-{@code null} argument and 0 for
	 * a {@code null} argument
	 * @see Object#hashCode
	 */
	abstract public int hashCode(Object o);

	/**
	 * Generates a hash code for a sequence of input values. The hash
	 * code is generated as if all the input values were placed into an
	 * array, and that array were hashed by calling {@link
	 * Arrays#hashCode(Object[])}.
	 * <p/>
	 * <p>This method is useful for implementing {@link
	 * Object#hashCode()} on objects containing multiple fields. For
	 * example, if an object that has three fields, {@code x}, {@code
	 * y}, and {@code z}, one could write:
	 * <p/>
	 * <blockquote><pre>
	 * &#064;Override public int hashCode() {
	 *     return Objects.hash(x, y, z);
	 * }
	 * </pre></blockquote>
	 * <p/>
	 * <b>Warning: When a single object reference is supplied, the returned
	 * value does not equal the hash code of that object reference.</b> This
	 * value can be computed by calling {@link #hashCode(Object)}.
	 *
	 * @param values the values to be hashed
	 * @return a hash value of the sequence of input values
	 * @see Arrays#hashCode(Object[])
	 * @see List#hashCode
	 */
	abstract public int hash(Object... values);

	/**
	 * Returns the result of calling {@code toString} for a non-{@code
	 * null} argument and {@code "null"} for a {@code null} argument.
	 *
	 * @param o an object
	 * @return the result of calling {@code toString} for a non-{@code
	 * null} argument and {@code "null"} for a {@code null} argument
	 * @see Object#toString
	 * @see String#valueOf(Object)
	 */
	abstract public String toString(Object o);

	/**
	 * Returns the result of calling {@code toString} on the first
	 * argument if the first argument is not {@code null} and returns
	 * the second argument otherwise.
	 *
	 * @param o          an object
	 * @param nullString string to return if the first argument is
	 *                   {@code null}
	 * @return the result of calling {@code toString} on the first
	 * argument if it is not {@code null} and the second argument
	 * otherwise.
	 * @see #toString(Object)
	 */
	abstract public String toString(Object o, String nullString);

	/**
	 * Returns 0 if the arguments are identical and {@code
	 * c.compare(a, b)} otherwise.
	 * Consequently, if both arguments are {@code null} 0
	 * is returned.
	 * <p/>
	 * <p>Note that if one of the arguments is {@code null}, a {@code
	 * NullPointerException} may or may not be thrown depending on
	 * what ordering policy, if any, the {@link Comparator Comparator}
	 * chooses to have for {@code null} values.
	 *
	 * @param <T> the type of the objects being compared
	 * @param a   an object
	 * @param b   an object to be compared with {@code a}
	 * @param c   the {@code Comparator} to compare the first two arguments
	 * @return 0 if the arguments are identical and {@code
	 * c.compare(a, b)} otherwise.
	 * @see Comparable
	 * @see Comparator
	 */
	abstract public <T> int compare(T a, T b, Comparator<? super T> c);

	/**
	 * Checks that the specified object reference is not {@code null}. This
	 * method is designed primarily for doing parameter validation in methods
	 * and constructors, as demonstrated below:
	 * <blockquote><pre>
	 * public Foo(Bar bar) {
	 *     this.bar = Objects.requireNonNull(bar);
	 * }
	 * </pre></blockquote>
	 *
	 * @param o   the object reference to check for nullity
	 * @param <T> the type of the reference
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException if {@code obj} is {@code null}
	 */
	abstract public <T> T requireNonNull(T o);

	/**
	 * Checks that the specified object reference is not {@code null} and
	 * throws a customized {@link NullPointerException} if it is. This method
	 * is designed primarily for doing parameter validation in methods and
	 * constructors with multiple parameters, as demonstrated below:
	 * <blockquote><pre>
	 * public Foo(Bar bar, Baz baz) {
	 *     this.bar = Objects.requireNonNull(bar, "bar must not be null");
	 *     this.baz = Objects.requireNonNull(baz, "baz must not be null");
	 * }
	 * </pre></blockquote>
	 *
	 * @param o       the object reference to check for nullity
	 * @param message detail message to be used in the event that a {@code
	 *                NullPointerException} is thrown
	 * @param <T>     the type of the reference
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException if {@code obj} is {@code null}
	 */
	abstract public <T> T requireNonNull(T o, String message);

	/**
	 * Returns {@code true} if the provided reference is {@code null} otherwise
	 * returns {@code false}.
	 *
	 * @param o a reference to be checked against {@code null}
	 * @return {@code true} if the provided reference is {@code null} otherwise
	 * {@code false}
	 * @since 1.8
	 */
	public boolean isNull(Object o) {
		return o == null;
	}

	/**
	 * Returns {@code true} if the provided reference is non-{@code null}
	 * otherwise returns {@code false}.
	 *
	 * @param o a reference to be checked against {@code null}
	 * @return {@code true} if the provided reference is non-{@code null}
	 * otherwise {@code false}
	 * @since 1.8
	 */
	public boolean nonNull(Object o) {
		return o != null;
	}
}
