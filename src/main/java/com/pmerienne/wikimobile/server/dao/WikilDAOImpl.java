package com.pmerienne.wikimobile.server.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pmerienne.wikimobile.shared.model.WikiSource;

@Service("wikiDAO")
public class WikilDAOImpl implements WikiDAO {

	private final static Logger LOGGER = Logger.getLogger(WikilDAOImpl.class);

	private final static String ACTION_PARAM = "action";
	private final static String ACTION_QUERY = "query";
	private final static String ACTION_OPENSEARCH = "opensearch";
	private final static String PROP_PARAM = "prop";
	private final static String PROP_REVISIONS = "revisions";
	private final static String PROP_IMAGEINFO = "imageinfo";
	private final static String IIPROP_PARAM = "iiprop";
	private final static String IIPROP_URL = "url";
	private final static String TITLES_PARAM = "titles";
	private final static String RVPROP_PARAM = "rvprop";
	private final static String RVPROP_CONTENT = "content";
	private final static String SEARCH_PARAM = "search";
	private final static String REDIRECTS_PARAM = "redirects";
	private final static String FORMAT_PARAM = "format";
	private final static String FORMAT_JSON = "json";

	@Override
	public List<String> search(WikiSource wikiSource, String search) {
		List<String> pages = new ArrayList<String>();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ACTION_PARAM, ACTION_OPENSEARCH);
		parameters.put(SEARCH_PARAM, search);
		try {
			String results = this.get(wikiSource.getApiUrl(), parameters);
			for (String page : results.split(",")) {
				String formatedPage = page.replaceAll("[^a-zA-Z0-9-_\\s]", "");
				if (!formatedPage.equals(search) && !formatedPage.isEmpty()) {
					pages.add(formatedPage);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Unable to search for " + search + ". URL : " + wikiSource.getApiUrl() + ", Parameters : " + parameters, e);
		}
		return pages;
	}

	@Override
	public String getContent(WikiSource wikiSource, String pageName) throws Exception {
		String content = null;
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ACTION_PARAM, ACTION_QUERY);
		parameters.put(PROP_PARAM, PROP_REVISIONS);
		parameters.put(TITLES_PARAM, pageName);
		parameters.put(RVPROP_PARAM, RVPROP_CONTENT);
		parameters.put(REDIRECTS_PARAM, Boolean.TRUE.toString());
		parameters.put(FORMAT_PARAM, FORMAT_JSON);
		String jsonTxt = this.get(wikiSource.getApiUrl(), parameters);
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
		JSONObject pages = json.getJSONObject("query").getJSONObject("pages");
		Object id = pages.names().get(0);
		JSONObject page = pages.getJSONObject(id.toString());
		JSONObject revision = (JSONObject) page.getJSONArray("revisions").get(0);
		content = revision.get("*").toString();
		return content;
	}

	@Override
	public String getImageUrl(WikiSource wikiSource, String imageName) throws Exception {
		String url = null;
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ACTION_PARAM, ACTION_QUERY);
		parameters.put(TITLES_PARAM, "Image:" + imageName);
		parameters.put(PROP_PARAM, PROP_IMAGEINFO);
		parameters.put(IIPROP_PARAM, IIPROP_URL);
		parameters.put(FORMAT_PARAM, FORMAT_JSON);
		String jsonResponse = this.get(wikiSource.getApiUrl(), parameters);
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonResponse);
		JSONObject pages = json.getJSONObject("query").getJSONObject("pages");
		Object id = pages.names().get(0);
		JSONObject page = pages.getJSONObject(id.toString());
		url = page.getJSONArray("imageinfo").getJSONObject(0).getString("url");
		if (!url.startsWith("http://") && !url.startsWith("www")) {
			url = wikiSource.getBaseUrl() + url;
		}
		return url;
	}

	private String get(String url, Map<String, String> parameters) throws ClientProtocolException, IOException {
		if (parameters != null && !parameters.isEmpty()) {
			url += "?";
			for (String key : parameters.keySet()) {
				url += key + "=" + parameters.get(key) + "&";
			}
			url = url.substring(0, url.length() - 1);
		}
		url = url.replaceAll(" ", "%20");
		HttpGet httpget = new HttpGet(url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpget);
		InputStream is = response.getEntity().getContent();
		return IOUtils.toString(is);
	}
}
