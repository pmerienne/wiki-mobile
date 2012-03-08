package com.pmerienne.wikimobile.client.place;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class AppAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			return Animation.FADE;
		}
		// Home / Option
		if (oldPlace instanceof HomePlace && newPlace instanceof OptionPlace) {
			return Animation.SLIDE;
		} else if (oldPlace instanceof OptionPlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Home / Search
		if (oldPlace instanceof HomePlace && newPlace instanceof SearchPlace) {
			return Animation.SLIDE;
		} else if (oldPlace instanceof SearchPlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Search / Content
		if (oldPlace instanceof SearchPlace && newPlace instanceof ContentPlace) {
			return Animation.SLIDE;
		} else if (oldPlace instanceof ContentPlace && newPlace instanceof SearchPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Search / Home
		if (oldPlace instanceof ContentPlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Content / Image
		if (oldPlace instanceof ContentPlace && newPlace instanceof ImagePlace) {
			return Animation.SLIDE;
		} else if (oldPlace instanceof ImagePlace && newPlace instanceof ContentPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Search / Image
		if (oldPlace instanceof SearchPlace && newPlace instanceof ImagePlace) {
			return Animation.SLIDE;
		} else if (oldPlace instanceof ImagePlace && newPlace instanceof SearchPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Content / Home
		if (oldPlace instanceof ImagePlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}

		// Default
		return Animation.FADE;
	}

}
