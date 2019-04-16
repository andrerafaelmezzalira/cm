package br.com.samaia.cm.client.robot.find;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.FindHeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class FindRobotClient extends AbstractClient<FindHeaderVO> {

	static final String URL = "http://localhost:8080/cm/api/find";

	public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {
		FindRobotClient client = new FindRobotClient();
		client.log(client.get(URL, "?header=" + URLEncoder.encode(JsonUtils.toJson(client.getDataSource()), "UTF-8")));
	}

	@Override
	protected FindHeaderVO getDataSource() {
		FindHeaderVO findHeader = new FindHeaderVO();
		findHeader.setCpf("66666666666669");
		findHeader.setService(Constants.ADD_UNITY);
		findHeader.setDateTime(LocalDateTime.of(2018, 11, 28, 22, 0));
		return findHeader;
	}
}
