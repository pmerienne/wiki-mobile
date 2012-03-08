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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.ButtonType;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.OptionsDialogOption;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.pmerienne.wikimobile.client.place.HomePlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;

public class ImageViewImpl extends Composite implements ImageView {

	private static ImageViewImplUiBinder uiBinder = GWT.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	Image image;

	@UiField
	ButtonBase save;

	@UiField
	ButtonBase delete;

	private String imageName;
	private String imageUrl;

	private Presenter presenter;

	public ImageViewImpl() {
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
		this.presenter.save(this.imageName, this.imageUrl);
	}

	@UiHandler("delete")
	protected void onDeleteTaped(TapEvent event) {
		this.presenter.delete(this.imageName);
	}

	@Override
	public boolean isLoaded(String imageName) {
		return this.imageName != null && this.imageName.equals(imageName);
	}

	@Override
	public void clear() {
		this.imageName = null;
		this.imageUrl = null;
		this.image.setVisible(false);
	}

	@Override
	public void setImageData(String imageName, String imageData) {
		this.imageName = imageName;
		this.imageUrl = null;
		this.image.setUrl(imageData);
		this.image.setVisible(true);
	}

	@Override
	public void setImageUrl(String imageName, String url) {
		this.imageName = imageName;
		this.imageUrl = url;
		this.image.setUrl(url);
		this.image.setVisible(true);
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
