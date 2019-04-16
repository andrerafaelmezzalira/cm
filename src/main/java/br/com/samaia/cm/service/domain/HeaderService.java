package br.com.samaia.cm.service.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parameter;
import br.com.samaia.cm.domain.entity.Reprocessing;
import br.com.samaia.cm.domain.repository.HeaderRepository;
import br.com.samaia.cm.vo.FindHeaderVO;

/**
 * Operações na tabela Header
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class HeaderService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private HeaderRepository repository;

	public List<Header> consultar(FindHeaderVO consultaHeader) {
		return repository.consultar(consultaHeader);
	}

	public void saveHeader(Header header) throws JsonProcessingException {
		log.info("método saveHeader insert " + String.valueOf(header.getId() == null));
		if (header.getId() == null) {
			repository.insert(header);
		} else {
			repository.update(header);
		}
	}

	public List<Header> get() {
		List<Reprocessing> invalidHeaders = repository.get();
		Map<Integer, List<Reprocessing>> maps = getMapByHeader(invalidHeaders);
		List<Header> headers = new ArrayList<>();
		for (Entry<Integer, List<Reprocessing>> entry : maps.entrySet()) {
			Header header = repository.findById(entry.getKey());
			Collection<? extends Parameter> parameter = repository.findByParameter(header.getId(), entry.getValue());
			Field field;
			try {
				field = header.getClass().getDeclaredField(entry.getValue().get(0).getEntity());
				field.setAccessible(true);
				field.set(header, parameter);
				headers.add(header);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				log.severe("problem header service get");
			}
		}
		return headers;
	}

	private Map<Integer, List<Reprocessing>> getMapByHeader(List<Reprocessing> invalidHeaders) {
		Map<Integer, List<Reprocessing>> map = new HashMap<>();
		for (Reprocessing reprocessing : invalidHeaders) {
			List<Reprocessing> list = map.get(reprocessing.getHeaders());
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(reprocessing);
			map.put(reprocessing.getHeaders(), list);
		}
		return map;
	}
}