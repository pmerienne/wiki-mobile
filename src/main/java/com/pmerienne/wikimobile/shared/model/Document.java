package com.pmerienne.wikimobile.shared.model;

import java.io.Serializable;

import com.kfuntak.gwt.json.serialization.client.JsonSerializable;

public class Document implements Serializable, JsonSerializable {

	private static final long serialVersionUID = -4023621621345098485L;

	private String name;

	private String html;

	public Document() {
		super();
	}

	public Document(String name) {
		super();
		this.name = name;
	}

	public Document(String name, String html) {
		super();
		this.name = name;
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "Document [name=" + name + ", html=" + html + "]";
	}

}
