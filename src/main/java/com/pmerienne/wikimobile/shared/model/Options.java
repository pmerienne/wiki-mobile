package com.pmerienne.wikimobile.shared.model;

import java.io.Serializable;

import com.kfuntak.gwt.json.serialization.client.JsonSerializable;

public class Options implements Serializable, JsonSerializable {

	private static final long serialVersionUID = 9779312554365903L;

	private boolean offline;

	private WikiSource wikiSource;

	public Options() {
		super();
	}

	public Options(boolean offline, WikiSource wikiSource) {
		super();
		this.offline = offline;
		this.wikiSource = wikiSource;
	}

	public boolean getOffline() {
		return offline;
	}

	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	public WikiSource getWikiSource() {
		return wikiSource;
	}

	public void setWikiSource(WikiSource wikiSource) {
		this.wikiSource = wikiSource;
	}

}
