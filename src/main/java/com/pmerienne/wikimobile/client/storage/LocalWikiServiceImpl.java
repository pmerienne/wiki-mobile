package com.pmerienne.wikimobile.client.storage;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.wikimobile.client.utils.LocalStorageUtils;
import com.pmerienne.wikimobile.shared.model.Document;
import com.pmerienne.wikimobile.shared.model.ImageData;
import com.pmerienne.wikimobile.shared.model.WikiSource;

public class LocalWikiServiceImpl implements LocalWikiService {

	private Storage storage;

	public LocalWikiServiceImpl() {
		this.storage = Storage.getLocalStorageIfSupported();
	}

	@Override
	public void search(WikiSource wikiSource, String search, AsyncCallback<List<String>> callback) {
		try {
			List<String> results = new ArrayList<String>();
			String keyPrefix = LocalStorageUtils.getPrefix(Document.class);
			String key;
			for (int i = 0; i < this.storage.getLength(); i++) {
				key = this.storage.key(i);
				if (key.startsWith(keyPrefix)) {
					String[] splitedKey = key.split(LocalStorageUtils.KEY_SEPARATOR);
					String documentName = splitedKey[splitedKey.length - 2];
					if (magicString(documentName).contains(magicString(search))) {
						results.add(documentName);
					}
				}
			}
			callback.onSuccess(results);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void getContent(WikiSource wikiSource, String pageName, AsyncCallback<Document> callback) {
		try {
			Document document = LocalStorageUtils.find(getId(wikiSource, pageName), Document.class);
			callback.onSuccess(document);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void getImageUrl(WikiSource wikiSource, String imageName, AsyncCallback<String> callback) {
		callback.onFailure(new Exception("Not available in local storage"));
	}
	
	@Override
	public void getImageData(WikiSource wikiSource, String imageName, AsyncCallback<String> callback) {
		try {
			ImageData imageData = LocalStorageUtils.find(getId(wikiSource, imageName), ImageData.class);
			String data = imageData == null ? null : imageData.getData();
			callback.onSuccess(data);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void save(WikiSource wikiSource, Document document, AsyncCallback<Void> callback) {
		try {
			LocalStorageUtils.save(getId(wikiSource, document.getName()), document);
			callback.onSuccess(null);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void save(WikiSource wikiSource, String imageName, String image, AsyncCallback<Void> callback) {
		ImageData imageData = new ImageData(imageName, image);
		try {
			LocalStorageUtils.save(getId(wikiSource, imageName), imageData);
			callback.onSuccess(null);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void delete(WikiSource wikiSource, Document document, AsyncCallback<Void> callback) {
		try {
			LocalStorageUtils.delete(getId(wikiSource, document.getName()), Document.class);
			callback.onSuccess(null);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void delete(WikiSource wikiSource, String imageName, AsyncCallback<Void> callback) {
		try {
			LocalStorageUtils.delete(getId(wikiSource, imageName), Document.class);
			callback.onSuccess(null);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	@Override
	public void clearAll(AsyncCallback<Void> callback) {
		try {
			this.storage.clear();
			callback.onSuccess(null);
		} catch (Throwable caught) {
			callback.onFailure(caught);
		}
	}

	private static String getId(WikiSource wikiSource, String name) {
		return name + ":" + wikiSource.getName();
	}

	private static String magicString(String s) {
		return s.toLowerCase();
	}
}
