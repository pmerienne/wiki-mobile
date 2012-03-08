package com.pmerienne.wikimobile.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface HomeView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);
	}

	void setPresenter(Presenter presenter);
}
