package com.pmerienne.wikimobile.client.view;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface SearchView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void search(String search);
	}

	void setPresenter(Presenter presenter);

	void setResults(List<String> results);
	
	void clear();
}
