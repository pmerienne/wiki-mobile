package com.pmerienne.wikimobile.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.wikimobile.client.ClientFactory;
import com.pmerienne.wikimobile.client.place.SearchPlace;
import com.pmerienne.wikimobile.client.utils.OptionUtils;
import com.pmerienne.wikimobile.client.utils.Services;
import com.pmerienne.wikimobile.client.view.ImageView;

public class ImageActivity extends MobileActivity implements ImageView.Presenter {

	private String imageName;

	public ImageActivity(ClientFactory clientFactory, String imageName) {
		super(clientFactory);
		this.imageName = imageName;
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getImageView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final ImageView view = this.clientFactory.getImageView();
		view.setPresenter(this);
		view.setOffline(OptionUtils.isOffline());
		if (!view.isLoaded(this.imageName)) {
			loadImage(this.imageName);
		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private void loadImage(final String imageName) {
		if (OptionUtils.isOffline()) {
			this.loadImageData(imageName);
		} else {
			this.loadImageUrl(imageName);
		}
	}

	private void loadImageData(final String imageName) {
		this.setPending(true);
		this.clientFactory.getImageView().clear();
		Services.getWikiService().getImageData(OptionUtils.getWikiSource(), imageName, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String data) {
				setPending(false);
				if (data != null) {
					clientFactory.getImageView().setImageData(imageName, data);
				} else if (OptionUtils.isOffline()) {
					Window.alert("Image " + imageName + " non disponible hors connexion");
				} else {
					Window.alert("Impossible de charger l'image " + imageName);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Window.alert("Impossible de charger l'image " + imageName);
			}
		});
	}

	private void loadImageUrl(final String imageName) {
		this.setPending(true);
		this.clientFactory.getImageView().clear();
		Services.getWikiService().getImageUrl(OptionUtils.getWikiSource(), imageName, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String url) {
				setPending(false);
				if (url != null && !url.isEmpty()) {
					clientFactory.getImageView().setImageUrl(imageName, url);
				} else if (OptionUtils.isOffline()) {
					Window.alert("Image " + imageName + " non disponible hors connexion");
				} else {
					Window.alert("Impossible de charger l'image " + imageName);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				if (OptionUtils.isOffline()) {
					Window.alert("Image " + imageName + " non disponible hors connexion");
				} else {
					Window.alert("Impossible de charger l'image " + imageName);
				}
			}
		});
	}

	@Override
	public void save(final String imageName, String url) {
		this.setPending(true);
		Services.getWikiService().getImageData(OptionUtils.getWikiSource(), imageName, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String data) {
				saveImageData(imageName, data);
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Window.alert("Impossible de sauvegarder " + imageName);
			}
		});
	}

	private void saveImageData(final String imageName, String data) {
		Services.getLocalWikiService().save(OptionUtils.getWikiSource(), imageName, data, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Window.alert("Impossible de sauvegarder " + imageName);
			}

			@Override
			public void onSuccess(Void result) {
				setPending(false);
				Window.alert(imageName + " sauvegardé");
			}
		});
	}

	@Override
	public void delete(final String imageName) {
		Services.getLocalWikiService().delete(OptionUtils.getWikiSource(), imageName, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible de supprimer " + imageName + " : " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert(imageName + " supprimé");
				clientFactory.getPlaceController().goTo(new SearchPlace());
			}
		});
	}

}
