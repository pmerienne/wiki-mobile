package com.pmerienne.wikimobile.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.ButtonType;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.OptionsDialogOption;
import com.googlecode.mgwt.ui.client.widget.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.pmerienne.wikimobile.client.place.ContentPlace;
import com.pmerienne.wikimobile.client.place.HomePlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;
import com.pmerienne.wikimobile.client.widget.list.ResultList;

public class SearchViewImpl extends Composite implements SearchView {

	private static SearchViewImplUiBinder uiBinder = GWT.create(SearchViewImplUiBinder.class);

	interface SearchViewImplUiBinder extends UiBinder<Widget, SearchViewImpl> {
	}

	@UiField
	MSearchBox searchBox;

	@UiField
	ResultList resultList;

	private Presenter presenter;

	private String lastSearch;

	public SearchViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("nav")
	protected void onNavTaped(TapEvent event) {
		List<OptionsDialogOption> options = new ArrayList<OptionsDialogOption>();
		options.add(new OptionsDialogOption("Accueil", ButtonType.NORMAL));
		options.add(new OptionsDialogOption("Recherche", ButtonType.NORMAL));
		options.add(new OptionsDialogOption("Revenir", ButtonType.NORMAL));
		options.add(new OptionsDialogOption("Cancel", ButtonType.NORMAL));
		Dialogs.options(options, new Dialogs.OptionCallback() {
			@Override
			public void onOptionSelected(int index) {
				if (index == 1) {
					presenter.goTo(new HomePlace());
				} else if (index == 2) {
					presenter.goTo(new SearchPlace());
				} else if (index == 3) {
					History.back();
				}
			}
		});
	}

	@UiHandler("searchBox")
	protected void onSearchChange(KeyUpEvent event) {
		if (this.searchBox.getValue() != null && !this.searchBox.getValue().isEmpty()) {
			if (!this.searchBox.getValue().equals(this.lastSearch)) {
				this.lastSearch = this.searchBox.getValue();
				this.presenter.search(this.searchBox.getValue());
			}
		} else {
			this.setResults(new ArrayList<String>());
		}
	}

	@UiHandler("resultList")
	protected void onResultSelected(CellSelectedEvent event) {
		String pageName = this.resultList.getResult(event.getIndex());
		this.presenter.goTo(new ContentPlace(pageName));
	}

	@Override
	public void setResults(List<String> results) {
		this.resultList.setResults(results);
	}

	@Override
	public void clear() {
		this.searchBox.setValue("");
		this.setResults(new ArrayList<String>());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
