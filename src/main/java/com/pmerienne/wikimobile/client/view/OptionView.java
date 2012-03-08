package com.pmerienne.wikimobile.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public interface OptionView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void setOffline(boolean offline);

		void clearLocalData();

		void setWikiSource(WikiSource wikiSource);
	}

	void setPresenter(Presenter presenter);

	void setOffline(boolean offline);

	void setWikiSource(WikiSource wikiSource);
}
