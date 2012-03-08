package com.pmerienne.wikimobile.client.widget.document;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class Copyright extends Composite {

	private static CopyrightUiBinder uiBinder = GWT.create(CopyrightUiBinder.class);

	interface CopyrightUiBinder extends UiBinder<Widget, Copyright> {
	}

	@UiField
	Anchor authors;

	@UiField
	Anchor wikiLink;

	public Copyright() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Copyright(String pageName, WikiSource wikiSource) {
		initWidget(uiBinder.createAndBindUi(this));
		this.setSource(pageName, wikiSource);
	}

	public void setSource(String pageName, WikiSource wikiSource) {
		this.authors.setHref(wikiSource.getSourceUrl() + "w/index.php?title=" + pageName + "&action=history");
		this.wikiLink.setHref(wikiSource.getSourceUrl() + "wiki/" + pageName);
		this.wikiLink.setText("article " + pageName + " sur " + wikiSource.getName());
	}
}
