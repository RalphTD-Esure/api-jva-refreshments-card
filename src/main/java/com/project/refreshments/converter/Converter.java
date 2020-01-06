package com.project.refreshments.converter;

@FunctionalInterface
public interface Converter<T1, T2> {
    T1 convert(T2 from);
}
