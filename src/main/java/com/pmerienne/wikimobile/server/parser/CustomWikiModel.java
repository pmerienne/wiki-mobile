package com.pmerienne.wikimobile.server.parser;

import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.model.WikiModel;

public class CustomWikiModel extends WikiModel {

	public CustomWikiModel(String imageBaseURL, String linkBaseURL) {
		super(imageBaseURL, linkBaseURL);
	}

	public void parseInternalImageLink(String imageNamespace, String rawImageLink) {
		if (fExternalImageBaseURL != null) {
			String imageHref = fExternalWikiBaseURL;
			String imageSrc = fExternalImageBaseURL;
			ImageFormat imageFormat = ImageFormat.getImageFormat(rawImageLink, imageNamespace);

			String imageName = imageFormat.getFilename();
			if (imageName.endsWith(".svg")) {
				imageName += ".png";
			}
			imageName = Encoder.encodeUrl(imageName);
			if (replaceColon()) {
				imageName = imageName.replace(':', '/');
			}
			String link = imageFormat.getLink();
			if (link != null) {
				if (link.length() == 0) {
					imageHref = "";
				} else {
					String encodedTitle = encodeTitleToUrl(link, true);
					imageHref = imageHref.replace("${title}", encodedTitle);
				}

			} else {
				if (replaceColon()) {
					imageHref = imageHref.replace("${title}", imageNamespace + '/' + imageName);
				} else {
					imageHref = imageHref.replace("${title}", imageNamespace + ':' + imageName);
				}
			}
			imageSrc = imageSrc.replace("${image}", imageName);

			appendInternalImageLink(imageHref, imageSrc, imageFormat);
		}
	}

	@Override
	public void appendRawWikipediaLink(String rawLinkText, String suffix) {
		if (!rawLinkText.contains(":") || rawLinkText.startsWith("Image:")) {
			super.appendRawWikipediaLink(rawLinkText, suffix);
		}
	}
}
