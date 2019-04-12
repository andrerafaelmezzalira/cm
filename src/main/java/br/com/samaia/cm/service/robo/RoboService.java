package br.com.samaia.cm.service.robo;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Define o nome do servico e o nome do site a serem acessados
 * 
 * @author andrerafaelmezzalira
 *
 */
public interface RoboService {

	String getNomeSite();

	String getNomeServico();

	void validations(final Header header, final HeaderVO headerVO) throws CampoObrigatorioException;
}
