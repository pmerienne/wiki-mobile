package com.pmerienne.wikimobile.client.widget.list;

import com.googlecode.mgwt.ui.client.widget.MListBox;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class WikiSourceListBox extends MListBox {

	public WikiSourceListBox() {
		super();
		for (WikiSource wikiSource : WikiSource.getAll()) {
			this.addItem(wikiSource.getName());
		}
	}

	public WikiSource getSelectedWikiSource() {
		int index = this.getSelectedIndex();
		return WikiSource.getAll().get(index);
	}

	public void setSelectedWikiSource(WikiSource wikiSource) {
		int index = WikiSource.getAll().indexOf(wikiSource);
		this.setSelectedIndex(index);
	}
}
