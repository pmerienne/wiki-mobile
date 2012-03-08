package com.pmerienne.wikimobile.client.theme;

import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPhone;
import com.googlecode.mgwt.ui.client.theme.base.MainCss;

public interface WikiThemeBundle extends MGWTClientBundleBaseThemeIPhone {

	@Override
	@Source({ "css/main.css" })
	public MainCss getMainCss();

	@Override
	@Source({ "css/header.css" })
	HeaderCss getHeaderCss();
}
