package com.pmerienne.wikimobile.server.service;

import java.io.InputStream;
import java.net.URL;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmerienne.wikimobile.server.dao.WikiDAO;
import com.pmerienne.wikimobile.server.parser.WikitextParser;
import com.pmerienne.wikimobile.server.utils.Base64Utils;
import com.pmerienne.wikimobile.shared.model.Document;
import com.pmerienne.wikimobile.shared.model.WikiSource;
import com.pmerienne.wikimobile.shared.service.WikiService;

@Service("wikiService")
public class WikiServiceImpl implements WikiService {

	private final static Logger LOGGER = Logger.getLogger(WikiServiceImpl.class);

	@Autowired
	private WikiDAO wikiDAO;

	@Autowired
	private WikitextParser wikitextParser;

	@Override
	public List<String> search(WikiSource wikiSource, String search) {
		return this.wikiDAO.search(wikiSource, unAccent(search).toLowerCase());
	}

	@Override
	public Document getContent(WikiSource wikiSource, String pageName) {
		Document document = null;
		try {
			// Get raw content from mediawiki api
			String rawContent = this.wikiDAO.getContent(wikiSource, pageName);
			// Parse wikitext to generate html
			String html = this.wikitextParser.parseToHtml(rawContent);
			document = new Document(pageName, html);
		} catch (Exception ex) {
			LOGGER.error("Unable to get content for " + pageName, ex);
		}
		return document;
	}

	@Override
	public String getImageUrl(WikiSource wikiSource, String imageName) {
		String url = null;
		try {
			url = this.wikiDAO.getImageUrl(wikiSource, imageName);
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return url;
	}

	@Override
	public String getImageData(WikiSource wikiSource, String imageName) {
		String data = "data:unknown;base64,";
		InputStream input = null;

		try {
			String url = this.wikiDAO.getImageUrl(wikiSource, imageName);
			URL source = new URL(url);
			input = source.openStream();
			byte[] bytes = IOUtils.toByteArray(input);
			String base64 = Base64Utils.toBase64(bytes);
			data += base64;
		} catch (Exception ex) {
			LOGGER.error(ex);
		} finally {
			IOUtils.closeQuietly(input);
		}
		return data;
	}

	public static String unAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}
}
