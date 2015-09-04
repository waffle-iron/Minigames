package au.com.mineauz.minigames.properties;

import com.google.common.base.Converter;


class TransformingProperty<T,S> extends AbstractProperty<T> {
	private final Converter<S, T> converter;
	private final Property<S> source;
	
	public TransformingProperty(Property<S> source, Converter<S,T> converter) {
		super();
		this.source = source;
		this.converter = converter;
	}
	
	@Override
	public T getValue() {
		S value = source.getValue();
		return converter.convert(value);
	}
	
	@Override
	public void setValue(T value) {
		S converted = converter.reverse().convert(value);
		source.setValue(converted);
		
		// Apply to parent so listeners will be fired
		super.setValue(value);
	}
}