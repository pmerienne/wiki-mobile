package com.pmerienne.wikimobile.shared.model;

import java.io.Serializable;

import com.kfuntak.gwt.json.serialization.client.JsonSerializable;

public class ImageData implements Serializable, JsonSerializable {

	private static final long serialVersionUID = 2768288644045571172L;

	private String name;

	private String data;

	public ImageData() {
		super();
	}

	public ImageData(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
