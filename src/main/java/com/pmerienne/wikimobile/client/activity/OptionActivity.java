package com.pmerienne.wikimobile.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.wikimobile.client.ClientFactory;
import com.pmerienne.wikimobile.client.utils.LocalStorageUtils;
import com.pmerienne.wikimobile.client.utils.OptionUtils;
import com.pmerienne.wikimobile.client.view.OptionView;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class OptionActivity extends MobileActivity implements OptionView.Presenter {

	public OptionActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getOptionView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final OptionView view = this.clientFactory.getOptionView();
		view.setPresenter(this);
		view.setOffline(OptionUtils.isOffline());
		view.setWikiSource(OptionUtils.getWikiSource());
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void setOffline(boolean offline) {
		OptionUtils.setOffline(offline);
	}

	@Override
	public void clearLocalData() {
		if (Window.confirm("Êtes vous sur de vouloir supprimer les données offline")) {
			LocalStorageUtils.clearData();
		}
	}

	@Override
	public void setWikiSource(WikiSource wikiSource) {
		OptionUtils.setWikiSource(wikiSource);
	}

}
