package br.com.samaia.cm.client.robot.unity;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class AddUnityClient extends AbstractClient<HeaderVO> {

	public static void main(String[] args) throws Exception {
		AddUnityClient client = new AddUnityClient();
		String json = client.post(URL, client.getDataSource());
		HeaderVO headerVO = JsonUtils.fromJson(json, HeaderVO.class);
		System.err.println(headerVO);
	}

	@Override
	protected HeaderVO getDataSource() throws Exception {
		return UnityDataSource.getExcelDataSource(Constants.ADD_UNITY);
	}

}
