package com.pmerienne.wikimobile.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.wikimobile.client.ClientFactory;
import com.pmerienne.wikimobile.client.view.HomeView;

public class HomeActivity extends MobileActivity implements HomeView.Presenter {

	public HomeActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getHomeView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final HomeView view = this.clientFactory.getHomeView();
		view.setPresenter(this);
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
