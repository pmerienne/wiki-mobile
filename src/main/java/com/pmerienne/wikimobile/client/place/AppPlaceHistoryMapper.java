package com.pmerienne.wikimobile.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ HomePlace.Tokenizer.class, SearchPlace.Tokenizer.class, ContentPlace.Tokenizer.class, ImagePlace.Tokenizer.class, OptionPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}