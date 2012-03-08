package com.pmerienne.wikimobile.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.ButtonType;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.OptionsDialogOption;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import com.pmerienne.wikimobile.client.place.HomePlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;
import com.pmerienne.wikimobile.client.widget.list.WikiSourceListBox;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class OptionViewImpl extends Composite implements OptionView {

	private static OptionViewImplUiBinder uiBinder = GWT.create(OptionViewImplUiBinder.class);

	interface OptionViewImplUiBinder extends UiBinder<Widget, OptionViewImpl> {
	}

	@UiField
	MCheckBox offline;

	@UiField
	Button clearData;

	@UiField
	WikiSourceListBox wikiSourceListBox;

	@UiField
	Label storageNotAvailable;

	private Presenter presenter;

	public OptionViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		boolean storageSupported = Storage.isLocalStorageSupported();
		this.storageNotAvailable.setVisible(!storageSupported);
		this.offline.setVisible(storageSupported);
		this.clearData.setVisible(storageSupported);
	}

	@UiHandler("offline")
	protected void onOfflineChanged(ValueChangeEvent<Boolean> event) {
		this.presenter.setOffline(event.getValue());
	}

	@UiHandler("clearData")
	protected void onClearTaped(TapEvent event) {
		this.presenter.clearLocalData();
	}

	@UiHandler("wikiSourceListBox")
	protected void onWikiSourceChanged(ChangeEvent event) {
		WikiSource wikiSource = this.wikiSourceListBox.getSelectedWikiSource();
		this.presenter.setWikiSource(wikiSource);
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

	@Override
	public void setOffline(boolean offline) {
		this.offline.setValue(offline);
	}

	@Override
	public void setWikiSource(WikiSource wikiSource) {
		this.wikiSourceListBox.setSelectedWikiSource(wikiSource);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
