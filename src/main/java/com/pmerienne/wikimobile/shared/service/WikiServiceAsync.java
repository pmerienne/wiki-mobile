package com.pmerienne.wikimobile.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.wikimobile.shared.model.Document;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public interface WikiServiceAsync {

	void search(WikiSource wikiSource, String search, AsyncCallback<List<String>> callback);

	void getContent(WikiSource wikiSource, String pageName, AsyncCallback<Document> callback);

	void getImageData(WikiSource wikiSource, String imageName, AsyncCallback<String> callback);

	void getImageUrl(WikiSource wikiSource, String imageName, AsyncCallback<String> callback);

}
