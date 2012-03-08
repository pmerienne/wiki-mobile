package com.pmerienne.wikimobile.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContentPlace extends Place {

	private String pageName;

	public ContentPlace(String pageName) {
		this.pageName = pageName;
	}

	public String getPageName() {
		return pageName;
	}

	public static class Tokenizer implements PlaceTokenizer<ContentPlace> {

		@Override
		public ContentPlace getPlace(String token) {
			return new ContentPlace(token);
		}

		@Override
		public String getToken(ContentPlace place) {
			return place.getPageName();
		}

	}
}
