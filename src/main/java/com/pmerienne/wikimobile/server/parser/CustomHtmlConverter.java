package com.pmerienne.wikimobile.server.parser;

import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.filter.HTMLConverter;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.model.ImageFormat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CustomHtmlConverter extends HTMLConverter {

	@Override
	public void imageNodeToText(TagNode node, ImageFormat imageFormat, Appendable resultBuffer, IWikiModel model) throws IOException {
		resultBuffer.append("<a ");
		Map<String, String> tagAtttributes = node.getAttributes();

		for (Map.Entry<String, String> currEntry : tagAtttributes.entrySet()) {
			String attName = currEntry.getKey();
			if (attName.length() >= 1 && Character.isLetter(attName.charAt(0))) {
				String attValue = currEntry.getValue();
				if ("href".equals(attName)) {
					continue;
				}
				if ("src".equals(attName)) {
					attName = "href";
				}

				resultBuffer.append(" ");
				resultBuffer.append(attName);
				resultBuffer.append("=\"");
				resultBuffer.append(attValue);
				resultBuffer.append("\"");
			}
		}

		List<Object> children = node.getChildren();
		resultBuffer.append('>');
		if (children.size() != 0) {
			nodesToText(children, resultBuffer, model);
		}
		resultBuffer.append("</a>");
	}

}
