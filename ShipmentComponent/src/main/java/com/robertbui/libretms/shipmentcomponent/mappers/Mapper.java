package com.robertbui.libretms.shipmentcomponent.mappers;

public interface Mapper<A, B> {

	B mapTo(A a);

	A mapFrom(B b);
}
