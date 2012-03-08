package com.pmerienne.wikimobile.client.storage;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.wikimobile.shared.model.Document;
import com.pmerienne.wikimobile.shared.model.WikiSource;
import com.pmerienne.wikimobile.shared.service.WikiServiceAsync;

public interface LocalWikiService extends WikiServiceAsync {

	void save(WikiSource wikiSource, Document document, AsyncCallback<Void> callback);

	void save(WikiSource wikiSource, String imageName, String image, AsyncCallback<Void> callback);

	void delete(WikiSource wikiSource, Document document, AsyncCallback<Void> callback);

	void delete(WikiSource wikiSource, String imageName, AsyncCallback<Void> callback);

	void clearAll(AsyncCallback<Void> callback);
}
