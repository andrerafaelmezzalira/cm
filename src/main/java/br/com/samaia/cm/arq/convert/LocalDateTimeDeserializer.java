package br.com.samaia.cm.arq.convert;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Convert string para LocalDateTime
 * 
 * @author andrerafaelmezzalira
 *
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		return LocalDateTime.parse(jsonParser.getText());
	}
}