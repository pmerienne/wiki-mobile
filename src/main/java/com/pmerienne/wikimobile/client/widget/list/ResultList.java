package com.pmerienne.wikimobile.client.widget.list;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;

public class ResultList extends CellListWithHeader<String> implements HasCellSelectedHandler {

	private List<String> results;

	public ResultList() {
		super(new BasicCell<String>() {
			@Override
			public String getDisplayString(String string) {
				return string;
			}
		});
	}

	public void setResults(List<String> results) {
		this.results = results;
		this.getCellList().render(results);
	}

	public String getResult(int index) {
		return this.results.get(index);
	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}

	public void setRound(boolean round) {
		this.getCellList().setRound(round);
	}

}
