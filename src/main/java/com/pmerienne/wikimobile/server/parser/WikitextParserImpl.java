package com.pmerienne.wikimobile.server.parser;

import info.bliki.wiki.model.WikiModel;

import org.springframework.stereotype.Service;

@Service("wikitextParser")
public class WikitextParserImpl implements WikitextParser {

	@Override
	public String parseToHtml(String rawContent) {
		CustomHtmlConverter htmlConverter = new CustomHtmlConverter();
		WikiModel wikiModel = new CustomWikiModel("#ImagePlace:${image}", "#ContentPlace:${title}");
		String html = wikiModel.render(htmlConverter, rawContent);
		html = magicString(html);
		return html;
	}

	/**
	 * Remove text contained in {{ }}
	 * 
	 * @param string
	 * @return
	 */
	private final static String magicString(String string) {
		String formatted = string.replaceAll("\\{\\{(.*)\\}\\}", "");
		return formatted;
	}
}
