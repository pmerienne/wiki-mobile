package com.pmerienne.wikimobile.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class OptionPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<OptionPlace> {

		@Override
		public OptionPlace getPlace(String token) {
			return new OptionPlace();
		}

		@Override
		public String getToken(OptionPlace place) {
			return "";
		}

	}
}
