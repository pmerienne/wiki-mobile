package com.pmerienne.wikimobile.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
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
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.pmerienne.wikimobile.client.place.HomePlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;
import com.pmerienne.wikimobile.client.widget.document.DocumentPanel;
import com.pmerienne.wikimobile.shared.model.Document;

public class ContentViewImpl extends Composite implements ContentView {

	private static ContentViewImplUiBinder uiBinder = GWT.create(ContentViewImplUiBinder.class);

	interface ContentViewImplUiBinder extends UiBinder<Widget, ContentViewImpl> {
	}

	@UiField
	ScrollPanel scrollPanel;

	@UiField
	ButtonBase save;

	@UiField
	ButtonBase delete;

	private String pageName;

	private Presenter presenter;

	private Document document;

	public ContentViewImpl() {
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

	@UiHandler("save")
	protected void onSaveTaped(TapEvent event) {
		this.presenter.saveDocument(this.document);
	}

	@UiHandler("delete")
	protected void onDeleteTaped(TapEvent event) {
		this.presenter.deleteDocument(this.document);
	}

	@Override
	public void setDocument(String pageName, Document document) {
		this.document = document;
		this.pageName = pageName;
		DocumentPanel contentPanel = new DocumentPanel(document);
		this.scrollPanel.setWidget(contentPanel);
		this.scrollPanel.refresh();
	}

	@Override
	public boolean isLoaded(String pageName) {
		return this.pageName != null && this.pageName.equals(pageName);
	}

	@Override
	public void setOffline(boolean offline) {
		boolean storageSupported = Storage.isLocalStorageSupported();
		this.save.setVisible(!offline && storageSupported);
		this.delete.setVisible(offline && storageSupported);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
