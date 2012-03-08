package com.pmerienne.wikimobile.client.utils;

import com.google.gwt.core.client.GWT;
import com.pmerienne.wikimobile.client.storage.LocalWikiService;
import com.pmerienne.wikimobile.client.storage.LocalWikiServiceImpl;
import com.pmerienne.wikimobile.shared.service.WikiService;
import com.pmerienne.wikimobile.shared.service.WikiServiceAsync;

public class Services {

	private static final WikiServiceAsync wikiService = GWT.create(WikiService.class);

	private static final LocalWikiService localWikiService = new LocalWikiServiceImpl();

	public static WikiServiceAsync getWikiService() {
		if (OptionUtils.isOffline()) {
			return localWikiService;
		} else {
			return wikiService;
		}
	}

	public static LocalWikiService getLocalWikiService() {
		return localWikiService;
	}

}
