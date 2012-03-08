package com.pmerienne.wikimobile.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.pmerienne.wikimobile.client.ClientFactory;
import com.pmerienne.wikimobile.client.utils.OptionUtils;
import com.pmerienne.wikimobile.client.utils.Services;
import com.pmerienne.wikimobile.client.view.SearchView;

public class SearchActivity extends MobileActivity implements SearchView.Presenter {

	public SearchActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getSearchView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final SearchView view = this.clientFactory.getSearchView();
		view.clear();
		view.setPresenter(this);
	}

	@Override
	public void goTo(Place place) {
		MGWT.hideKeyBoard();
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void search(String search) {
		Services.getWikiService().search(OptionUtils.getWikiSource(), search, new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> results) {
				clientFactory.getSearchView().setResults(results);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Une erreur à eu lieu lors de la recherche.");
			}
		});
	}

}
