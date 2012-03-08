package com.pmerienne.wikimobile.server.dao;

import java.util.List;

import org.junit.Test;

import com.pmerienne.wikimobile.server.dao.WikilDAOImpl;
import com.pmerienne.wikimobile.shared.model.WikiSource;

import static org.junit.Assert.*;

public class WikitravelDAOImplTest {

	private WikilDAOImpl wikitravelDAO = new WikilDAOImpl();

	@Test
	public void testSearch() {
		String search = "Te";
		List<String> results = this.wikitravelDAO.search(WikiSource.WIKITRAVEL, search);
		assertNotNull(results);
		assertTrue(results.contains("Teheran"));
		assertTrue(results.contains("Tegucigalpa"));
		assertTrue(results.contains("Tel-Aviv"));
		assertTrue(results.contains("Tenerife"));
		assertFalse(results.contains(search));
	}

	@Test
	public void testGetContent() throws Exception {
		String pageName = "Teheran";
		String content = this.wikitravelDAO.getContent(WikiSource.WIKITRAVEL, pageName);
		assertNotNull(content);
	}
}
