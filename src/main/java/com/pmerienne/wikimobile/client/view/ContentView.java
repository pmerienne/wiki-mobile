package com.pmerienne.wikimobile.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.wikimobile.shared.model.Document;

public interface ContentView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void saveDocument(Document document);

		void deleteDocument(Document document);
	}

	void setDocument(String pageName, Document document);

	boolean isLoaded(String pageName);

	void setPresenter(Presenter presenter);

	void setOffline(boolean offline);
}
