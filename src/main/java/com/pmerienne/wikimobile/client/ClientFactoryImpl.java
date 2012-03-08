/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.pmerienne.wikimobile.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pmerienne.wikimobile.client.view.ContentView;
import com.pmerienne.wikimobile.client.view.ContentViewImpl;
import com.pmerienne.wikimobile.client.view.HomeView;
import com.pmerienne.wikimobile.client.view.HomeViewImpl;
import com.pmerienne.wikimobile.client.view.ImageView;
import com.pmerienne.wikimobile.client.view.ImageViewImpl;
import com.pmerienne.wikimobile.client.view.OptionView;
import com.pmerienne.wikimobile.client.view.OptionViewImpl;
import com.pmerienne.wikimobile.client.view.PendingView;
import com.pmerienne.wikimobile.client.view.PendingViewImpl;
import com.pmerienne.wikimobile.client.view.SearchView;
import com.pmerienne.wikimobile.client.view.SearchViewImpl;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();

	@SuppressWarnings("deprecation")
	private final PlaceController placeController = new PlaceController(eventBus);

	private final HomeView homeView = new HomeViewImpl();
	private final PendingView pendingView = new PendingViewImpl();
	private final SearchView searchView = new SearchViewImpl();
	private final ContentView contentView = new ContentViewImpl();
	private final ImageView imageView = new ImageViewImpl();
	private final OptionView optionView = new OptionViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public HomeView getHomeView() {
		return homeView;
	}

	@Override
	public PendingView getPendingView() {
		return pendingView;
	}

	@Override
	public SearchView getSearchView() {
		return searchView;
	}

	@Override
	public ContentView getContentView() {
		return contentView;
	}

	@Override
	public ImageView getImageView() {
		return imageView;
	}

	@Override
	public OptionView getOptionView() {
		return optionView;
	}
}
