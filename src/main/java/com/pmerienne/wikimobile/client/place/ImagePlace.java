package com.pmerienne.wikimobile.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ImagePlace extends Place {

	private String imageName;

	public ImagePlace(String pageName) {
		this.imageName = pageName;
	}

	public String getImageName() {
		return imageName;
	}

	public static class Tokenizer implements PlaceTokenizer<ImagePlace> {

		@Override
		public ImagePlace getPlace(String token) {
			return new ImagePlace(token);
		}

		@Override
		public String getToken(ImagePlace place) {
			return place.getImageName();
		}

	}
}
