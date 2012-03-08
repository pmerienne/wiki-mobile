package com.pmerienne.wikimobile.client.utils;

import com.google.gwt.storage.client.Storage;
import com.pmerienne.wikimobile.shared.model.Options;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class OptionUtils {

	private final static String OPTIONS_KEY = "OPTIONS_KEY";

	private static Options OPTIONS;

	public static boolean isOffline() {
		return getOptions().isOffline();
	}

	public static void setOffline(Boolean offline) {
		getOptions().setOffline(offline);
		saveOptions();
	}

	public static WikiSource getWikiSource() {
		return getOptions().getWikiSource();
	}

	public static void setWikiSource(WikiSource wikiSource) {
		getOptions().setWikiSource(wikiSource);
		saveOptions();
	}

	private static Options getOptions() {
		if (OPTIONS == null) {
			OPTIONS = initOptions();
		}
		return OPTIONS;
	}

	private static Options initOptions() {
		if (Storage.isLocalStorageSupported()) {
			OPTIONS = LocalStorageUtils.find(OPTIONS_KEY, Options.class);
		}
		if (OPTIONS == null) {
			OPTIONS = new Options(false, WikiSource.WIKIPEDIA_FR);
		}
		return OPTIONS;
	}

	private static void saveOptions() {
		if (Storage.isLocalStorageSupported()) {
			LocalStorageUtils.save(OPTIONS_KEY, OPTIONS);
		}
	}
}
