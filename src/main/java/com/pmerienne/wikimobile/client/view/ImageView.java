package com.pmerienne.wikimobile.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void save(String imageName, String url);

		void delete(String imageName);
	}

	void setPresenter(Presenter presenter);

	void clear();

	void setImageData(String imageName, String data);

	void setImageUrl(String imageName, String url);

	boolean isLoaded(String imageName);

	void setOffline(boolean offline);
}
