package com.pmerienne.wikimobile.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.pmerienne.wikimobile.client.ClientFactory;
import com.pmerienne.wikimobile.client.place.ContentPlace;
import com.pmerienne.wikimobile.client.place.HomePlace;
import com.pmerienne.wikimobile.client.place.ImagePlace;
import com.pmerienne.wikimobile.client.place.OptionPlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;

public class AppActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity(clientFactory);
		} else if (place instanceof SearchPlace) {
			return new SearchActivity(clientFactory);
		} else if (place instanceof ContentPlace) {
			return new ContentActivity(clientFactory, ((ContentPlace) place).getPageName());
		} else if (place instanceof ImagePlace) {
			return new ImageActivity(clientFactory, ((ImagePlace) place).getImageName());
		} else if (place instanceof OptionPlace) {
			return new OptionActivity(clientFactory);
		}

		// Default
		return new HomeActivity(clientFactory);
	}

}
