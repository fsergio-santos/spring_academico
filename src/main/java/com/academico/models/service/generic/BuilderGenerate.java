package com.academico.models.service.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


public class BuilderGenerate<T> {

	private final Supplier<T> instanceSupplier;
	private final Map<BiConsumer<T, Object>, Object> setters = new HashMap<>();

	private BuilderGenerate(Supplier<T> instanceSupplier) {
	        this.instanceSupplier = instanceSupplier;
	    }

	public static <T> BuilderGenerate<T> of(Supplier<T> instanceSupplier) {
		return new BuilderGenerate<>(instanceSupplier);
	}

	@SuppressWarnings("unchecked")
	public <V> BuilderGenerate<T> with(BiConsumer<T, V> setter, V value) {
		setters.put((BiConsumer<T, Object>) setter, value);
		return this;
	}

	public T build() {
		T instance = instanceSupplier.get();
		setters.forEach((setter, value) -> setter.accept(instance, value));
		return instance;
	}

}
