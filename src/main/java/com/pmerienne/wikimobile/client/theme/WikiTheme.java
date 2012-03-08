package com.pmerienne.wikimobile.client.theme;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.MGWTTheme;

public class WikiTheme implements MGWTTheme {

	private final static WikiTheme INSTANCE = new WikiTheme();

	public static WikiTheme get() {
		return INSTANCE;
	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		return GWT.create(WikiThemeBundle.class);
	}

}
