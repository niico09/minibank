package minibank.bbva.impl.inter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import minibank.bbva.impl.ResultDeserializer;

@JsonDeserialize(using = ResultDeserializer.class)
public interface ResultChange {

	public Double getTasa();

	public Double getResultado();
	
}
