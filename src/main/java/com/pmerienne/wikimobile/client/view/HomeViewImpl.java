package com.pmerienne.wikimobile.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.pmerienne.wikimobile.client.widget.list.PlaceList;

public class HomeViewImpl extends Composite implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}

	@UiField
	PlaceList placeList;

	private Presenter presenter;

	public HomeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("placeList")
	protected void onPlaceSelected(CellSelectedEvent event) {
		Place place = this.placeList.getPlace(event.getIndex());
		this.presenter.goTo(place);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
