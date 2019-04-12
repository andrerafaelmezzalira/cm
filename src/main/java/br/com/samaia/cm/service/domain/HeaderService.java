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
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.domain.entity.Reprocessamento;
import br.com.samaia.cm.domain.repository.HeaderRepository;
import br.com.samaia.cm.vo.ConsultaHeaderVO;

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

	public List<Header> consultar(ConsultaHeaderVO consultaHeader) {
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

	public List<Header> getAllParametersInvalids() {
		List<Reprocessamento> headersInvalidos = repository.getAllParametersInvalids();
		Map<Integer, List<Reprocessamento>> maps = getMapByHeader(headersInvalidos);
		List<Header> headers = new ArrayList<>();
		for (Entry<Integer, List<Reprocessamento>> entry : maps.entrySet()) {
			Header header = repository.findById(entry.getKey());
			Collection<? extends Parametro> parametros = repository.findByParametros(header.getId(), entry.getValue());
			Field field;
			try {
				field = header.getClass().getDeclaredField(entry.getValue().get(0).getEntity());
				field.setAccessible(true);
				field.set(header, parametros);
				headers.add(header);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				log.severe("problema header service getallparametersinvalids");
			}
		}
		return headers;
	}

	private Map<Integer, List<Reprocessamento>> getMapByHeader(List<Reprocessamento> headersInvalidos) {
		Map<Integer, List<Reprocessamento>> map = new HashMap<>();
		for (Reprocessamento reprocessamento : headersInvalidos) {
			List<Reprocessamento> list = map.get(reprocessamento.getHeaders());
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(reprocessamento);
			map.put(reprocessamento.getHeaders(), list);
		}
		return map;
	}

}