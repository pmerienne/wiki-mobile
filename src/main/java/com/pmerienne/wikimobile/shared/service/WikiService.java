package com.pmerienne.wikimobile.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pmerienne.wikimobile.shared.model.Document;
import com.pmerienne.wikimobile.shared.model.WikiSource;

@RemoteServiceRelativePath("wikiService.rpc")
public interface WikiService extends RemoteService {

	List<String> search(WikiSource wikiSource, String search);

	Document getContent(WikiSource wikiSource, String pageName);

	String getImageData(WikiSource wikiSource, String imageName);

	String getImageUrl(WikiSource wikiSource, String imageName);
}
