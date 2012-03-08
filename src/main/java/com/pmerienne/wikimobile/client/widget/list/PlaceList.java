package com.pmerienne.wikimobile.client.widget.list;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import com.pmerienne.wikimobile.client.place.OptionPlace;
import com.pmerienne.wikimobile.client.place.SearchPlace;

public class PlaceList extends CellListWithHeader<String> implements HasCellSelectedHandler {

	private List<String> places = Arrays.asList("Recherche", "Options");

	public PlaceList() {
		super(new BasicCell<String>() {
			@Override
			public String getDisplayString(String string) {
				return string;
			}
		});
		this.getCellList().render(this.places);
	}

	public Place getPlace(int index) {
		if (index == 0) {
			return new SearchPlace();
		} else if (index == 1) {
			return new OptionPlace();
		}

		return null;
	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}

	public void setRound(boolean round) {
		this.getCellList().setRound(round);
	}

}
