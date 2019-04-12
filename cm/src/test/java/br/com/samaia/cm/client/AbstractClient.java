package br.com.samaia.cm.client;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.com.samaia.cm.utils.JsonUtils;

/**
 *
 * Client abstrato
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class AbstractClient<T> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	protected static final String URL_ROBO_HOMOLOG = "http://homologacao.projetabrasil.net.br:8080/cm/api/processar";
	protected static final String URL_ROBO = "http://localhost:8080/cm/api/processar";

	protected String post(String url, T t) {
		final HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(JsonUtils.toJson(t)));
			return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
		} catch (ParseException | IOException e) {
			log.severe("problema abstractclient");
			e.printStackTrace();
		}
		return url;
	}

	protected String get(String url, String params) {
		try {
			final HttpGet httpGet = new HttpGet(url + params);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			return EntityUtils.toString(new DefaultHttpClient().execute(httpGet).getEntity());
		} catch (ParseException | IOException e) {
			log.severe("problema abstractclient");
			e.printStackTrace();
		}
		return url;
	}

	protected void log(String msg) {
		log.info(msg);
	}

	protected abstract T getFonteDeDados();
}
