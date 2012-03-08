package com.pmerienne.wikimobile.client.widget.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

public class MobileLink extends CellListWithHeader<String> implements HasTapHandlers {

	private String text;

	private String href;

	private List<TapHandler> handlers = new ArrayList<TapHandler>();

	public MobileLink() {
		super(new BasicCell<String>() {
			@Override
			public String getDisplayString(String string) {
				return string;
			}
		});
		this.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				History.newItem(href);
				for (TapHandler handler : handlers) {
					handler.onTap(new TapEvent(this));
				}
			}
		});
		this.getCellList().setRound(true);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.getCellList().render(Arrays.asList(text));
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public HandlerRegistration addTapHandler(TapHandler handler) {
		this.handlers.add(handler);
		return null;
	}

}
