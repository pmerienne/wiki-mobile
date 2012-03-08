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
import com.pmerienne.wikimobile.client.view.ContentView;
import com.pmerienne.wikimobile.shared.model.Document;

public class ContentActivity extends MobileActivity implements ContentView.Presenter {

	private String pageName;

	public ContentActivity(ClientFactory clientFactory, String pageName) {
		super(clientFactory);
		this.pageName = pageName;
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getContentView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final ContentView view = this.clientFactory.getContentView();
		view.setPresenter(this);
		view.setOffline(OptionUtils.isOffline());
		if (!view.isLoaded(this.pageName)) {
			loadContent(pageName);
		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private void loadContent(final String pageName) {
		this.setPending(true);
		Services.getWikiService().getContent(OptionUtils.getWikiSource(), pageName, new AsyncCallback<Document>() {
			@Override
			public void onSuccess(Document document) {
				setPending(false);
				if (document != null) {
					clientFactory.getContentView().setDocument(pageName, document);
				} else if (OptionUtils.isOffline()) {
					Window.alert("Page " + pageName + " non disponible hors connexion");
				} else {
					Window.alert("Impossible de charger la page " + pageName);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Window.alert("Impossible de charger la page " + pageName + " : " + caught.getMessage());
				clientFactory.getPlaceController().goTo(new SearchPlace());
			}
		});
	}

	@Override
	public void saveDocument(final Document document) {
		this.setPending(true);
		Services.getLocalWikiService().save(OptionUtils.getWikiSource(), document, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Window.alert("Impossible de sauvegarder " + document.getName());
			}

			@Override
			public void onSuccess(Void result) {
				setPending(false);
				Window.alert(document.getName() + " sauvegardé");
			}
		});
	}

	@Override
	public void deleteDocument(final Document document) {
		Services.getLocalWikiService().delete(OptionUtils.getWikiSource(), document, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible de supprimer la page : " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert(document.getName() + " supprimé");
				clientFactory.getPlaceController().goTo(new SearchPlace());
			}
		});
	}
}
