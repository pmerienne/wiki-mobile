package com.pmerienne.wikimobile.client.widget.document;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.pmerienne.wikimobile.client.utils.OptionUtils;
import com.pmerienne.wikimobile.shared.model.Document;

public class DocumentPanel extends FlowPanel {

	private Document document;

	public DocumentPanel() {
		super();
	}

	public DocumentPanel(Document document) {
		super();
		this.setDocument(document);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
		this.clear();
		if (document.getHtml() != null && !document.getHtml().isEmpty()) {
			HTML html = new HTML(document.getHtml());
			html.getElement().getStyle().setPadding(10, Unit.PX);
			this.add(html);
		}
		this.add(new Copyright(document.getName(), OptionUtils.getWikiSource()));
	}

}
