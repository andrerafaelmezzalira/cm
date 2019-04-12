package br.com.samaia.cm.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.samaia.cm.domain.entity.Parametro;

/**
 * Classe para métodos utilitários para manipular json
 * 
 * @author andrerafaelmezzalira
 *
 */
public class JsonUtils {

	public static final <T> T fromJson(final String json, final Class<T> t) throws JsonParseException, JsonMappingException, IOException {
		return (T) getObjectMapper().readValue(json, t);
	}

	public static final <T> String toJson(final T t) throws JsonProcessingException {
		return getObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(t);
	}

	public static final <T extends Parametro> List<T> convertList(Collection<?> parametros,
			TypeReference<List<T>> typeReference) {
		return getObjectMapper().convertValue(parametros, typeReference);
	}

	private static final ObjectMapper getObjectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		return objectMapper;
	}
}
