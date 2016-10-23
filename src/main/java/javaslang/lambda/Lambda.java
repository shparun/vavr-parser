/**    / \____  _    ______   _____ / \____   ____  _____
 *    /  \__  \/ \  / \__  \ /  __//  \__  \ /    \/ __  \   Javaslang
 *  _/  // _\  \  \/  / _\  \\_  \/  // _\  \  /\  \__/  /   Copyright 2014 Daniel Dietrich
 * /___/ \_____/\____/\_____/____/\___\_____/_/  \_/____/    Licensed under the Apache License, Version 2.0
 */
package javaslang.lambda;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import javaslang.collection.Tuple;

/**
 * The class of reflective lambda functions with a specific return type.
 */
public interface Lambda<R> extends Reflective {

	/**
	 * @return the numper of arguments of this lambda.
	 * @see http://en.wikipedia.org/wiki/Arity
	 */
	int arity();

	λ1<?, ?> curried();

	λ1<? extends Tuple, R> tupled();

	<V> Lambda<V> andThen(Function<? super R, ? extends V> after);

	@FunctionalInterface
	static interface λ0<R> extends Lambda<R> {

		R apply();

		@Override
		default int arity() {
			return 0;
		}

		// http://stackoverflow.com/questions/643906/uses-for-the-java-void-reference-type
		@Override
		default λ1<Void, R> curried() {
			return v -> apply();
		}

		@Override
		default λ1<Tuple.Tuple0, R> tupled() {
			return t -> apply();
		}

		@Override
		default <V> λ0<V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return () -> after.apply(apply());
		}
	}

	/**
	 * A function with one argument which implements Serializable in order to obtain runtime type information about the
	 * lambda via {@link javaslang.lambda.Lambdas#getLambdaSignature(Serializable)}.
	 *
	 * @param <T1> The parameter type of the function.
	 * @param <R> The return type of the function.
	 */
	@FunctionalInterface
	static interface λ1<T1, R> extends Lambda<R>, Function<T1, R> {

		@Override
		R apply(T1 t1);

		@Override
		default int arity() {
			return 1;
		}

		@Override
		default λ1<T1, R> curried() {
			return t1 -> apply(t1);
		}

		@Override
		default λ1<Tuple.Tuple1<T1>, R> tupled() {
			return t -> apply(t._1);
		}

		@Override
		default <V> λ1<T1, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return t1 -> after.apply(apply(t1));
		}

		@Override
		default <V> λ1<V, R> compose(Function<? super V, ? extends T1> before) {
			Objects.requireNonNull(before);
			return (V v) -> apply(before.apply(v));
		}

		static <T> λ1<T, T> identity() {
			return t -> t;
		}
	}

	@FunctionalInterface
	static interface λ2<T1, T2, R> extends Lambda<R>, BiFunction<T1, T2, R> {

		@Override
		default int arity() {
			return 2;
		}

		@Override
		R apply(T1 t1, T2 t2);

		@Override
		default λ1<T1, λ1<T2, R>> curried() {
			return t1 -> t2 -> apply(t1, t2);
		}

		@Override
		default λ1<Tuple.Tuple2<T1, T2>, R> tupled() {
			return t -> apply(t._1, t._2);
		}

		@Override
		default <V> λ2<T1, T2, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2) -> after.apply(apply(t1, t2));
		}
	}

	@FunctionalInterface
	static interface λ3<T1, T2, T3, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3);

		@Override
		default int arity() {
			return 3;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, R>>> curried() {
			return t1 -> t2 -> t3 -> apply(t1, t2, t3);
		}

		@Override
		default λ1<Tuple.Tuple3<T1, T2, T3>, R> tupled() {
			return t -> apply(t._1, t._2, t._3);
		}

		@Override
		default <V> λ3<T1, T2, T3, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3) -> after.apply(apply(t1, t2, t3));
		}
	}

	@FunctionalInterface
	static interface λ4<T1, T2, T3, T4, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4);

		@Override
		default int arity() {
			return 4;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, R>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> apply(t1, t2, t3, t4);
		}

		@Override
		default λ1<Tuple.Tuple4<T1, T2, T3, T4>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4);
		}

		@Override
		default <V> λ4<T1, T2, T3, T4, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4) -> after.apply(apply(t1, t2, t3, t4));
		}
	}

	@FunctionalInterface
	static interface λ5<T1, T2, T3, T4, T5, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);

		@Override
		default int arity() {
			return 5;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, R>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> apply(t1, t2, t3, t4, t5);
		}

		@Override
		default λ1<Tuple.Tuple5<T1, T2, T3, T4, T5>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5);
		}

		@Override
		default <V> λ5<T1, T2, T3, T4, T5, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5) -> after.apply(apply(t1, t2, t3, t4, t5));
		}
	}

	@FunctionalInterface
	static interface λ6<T1, T2, T3, T4, T5, T6, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);

		@Override
		default int arity() {
			return 6;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, R>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> apply(t1, t2, t3, t4, t5, t6);
		}

		@Override
		default λ1<Tuple.Tuple6<T1, T2, T3, T4, T5, T6>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6);
		}

		@Override
		default <V> λ6<T1, T2, T3, T4, T5, T6, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6) -> after.apply(apply(t1, t2, t3, t4, t5, t6));
		}
	}

	@FunctionalInterface
	static interface λ7<T1, T2, T3, T4, T5, T6, T7, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);

		@Override
		default int arity() {
			return 7;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, R>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> apply(t1, t2, t3, t4, t5, t6, t7);
		}

		@Override
		default λ1<Tuple.Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7);
		}

		@Override
		default <V> λ7<T1, T2, T3, T4, T5, T6, T7, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7));
		}
	}

	@FunctionalInterface
	static interface λ8<T1, T2, T3, T4, T5, T6, T7, T8, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8);

		@Override
		default int arity() {
			return 8;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, R>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> apply(t1, t2, t3, t4, t5, t6, t7, t8);
		}

		@Override
		default λ1<Tuple.Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
		}

		@Override
		default <V> λ8<T1, T2, T3, T4, T5, T6, T7, T8, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7, t8));
		}
	}

	@FunctionalInterface
	static interface λ9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9);

		@Override
		default int arity() {
			return 9;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, λ1<T9, R>>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> t9 -> apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
		}

		@Override
		default λ1<Tuple.Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
		}

		@Override
		default <V> λ9<T1, T2, T3, T4, T5, T6, T7, T8, T9, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8, t9) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7, t8, t9));
		}
	}

	@FunctionalInterface
	static interface λ10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10);

		@Override
		default int arity() {
			return 10;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, λ1<T9, λ1<T10, R>>>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> t9 -> t10 -> apply(t1, t2, t3, t4, t5, t6, t7, t8,
					t9, t10);
		}

		@Override
		default λ1<Tuple.Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
		}

		@Override
		default <V> λ10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7, t8, t9,
					t10));
		}
	}

	@FunctionalInterface
	static interface λ11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

		@Override
		default int arity() {
			return 11;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, λ1<T9, λ1<T10, λ1<T11, R>>>>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> t9 -> t10 -> t11 -> apply(t1, t2, t3, t4, t5, t6,
					t7, t8, t9, t10, t11);
		}

		@Override
		default λ1<Tuple.Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
		}

		@Override
		default <V> λ11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7, t8,
					t9, t10, t11));
		}
	}

	@FunctionalInterface
	static interface λ12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12);

		@Override
		default int arity() {
			return 12;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, λ1<T9, λ1<T10, λ1<T11, λ1<T12, R>>>>>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> t9 -> t10 -> t11 -> t12 -> apply(t1, t2, t3, t4, t5,
					t6, t7, t8, t9, t10, t11, t12);
		}

		@Override
		default λ1<Tuple.Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
		}

		@Override
		default <V> λ12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, V> andThen(
				Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) -> after.apply(apply(t1, t2, t3, t4, t5, t6, t7,
					t8, t9, t10, t11, t12));
		}
	}

	@FunctionalInterface
	static interface λ13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> extends Lambda<R> {

		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13);

		@Override
		default int arity() {
			return 13;
		}

		@Override
		default λ1<T1, λ1<T2, λ1<T3, λ1<T4, λ1<T5, λ1<T6, λ1<T7, λ1<T8, λ1<T9, λ1<T10, λ1<T11, λ1<T12, λ1<T13, R>>>>>>>>>>>>> curried() {
			return t1 -> t2 -> t3 -> t4 -> t5 -> t6 -> t7 -> t8 -> t9 -> t10 -> t11 -> t12 -> t13 -> apply(t1, t2, t3,
					t4, t5, t6, t7, t8, t9, t10, t11, t12, t13);
		}

		@Override
		default λ1<Tuple.Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>, R> tupled() {
			return t -> apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
		}

		@Override
		default <V> λ13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, V> andThen(
				Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) -> after.apply(apply(t1, t2, t3, t4, t5,
					t6, t7, t8, t9, t10, t11, t12, t13));
		}
	}
}
