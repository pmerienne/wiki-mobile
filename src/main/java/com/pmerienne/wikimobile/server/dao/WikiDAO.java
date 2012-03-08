package com.pmerienne.wikimobile.server.dao;

import java.util.List;

import com.pmerienne.wikimobile.shared.model.WikiSource;

public interface WikiDAO {

	List<String> search(WikiSource wikiSource, String search);

	String getContent(WikiSource wikiSource, String pageName) throws Exception;

	String getImageUrl(WikiSource wikiSource, String imageName) throws Exception;
}
