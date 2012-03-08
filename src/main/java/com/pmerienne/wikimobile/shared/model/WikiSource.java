package com.pmerienne.wikimobile.shared.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.kfuntak.gwt.json.serialization.client.JsonSerializable;

public class WikiSource implements Serializable, JsonSerializable {

	private static final long serialVersionUID = 8180865389038730145L;

	public static final WikiSource WIKITRAVEL = new WikiSource("Wikitravel", "http://wikitravel.org", "http://wikitravel.org/wiki/fr/api.php",
			"http://wikitravel.org/fr/");
	public static final WikiSource WIKIPEDIA_FR = new WikiSource("Wikipedia (fr)", "http://fr.wikipedia.org/", "http://fr.wikipedia.org/w/api.php",
			"http://fr.wikipedia.org/");
	public static final WikiSource WIKTIONARY_FR = new WikiSource("Wiktionary (fr)", "http://fr.wiktionary.org/", "http://fr.wiktionary.org/w/api.php",
			"http://fr.wiktionary.org/");
	public static final WikiSource WIKIPEDIA_EN = new WikiSource("Wikipedia (en)", "http://en.wikipedia.org/", "http://en.wikipedia.org/w/api.php",
			"http://en.wikipedia.org/");
	public static final WikiSource WIKTIONARY_EN = new WikiSource("Wiktionary (en)", "http://en.wiktionary.org/", "http://en.wiktionary.org/w/api.php",
			"http://en.wiktionary.org/");

	private String name;

	private String baseUrl;

	private String apiUrl;

	private String sourceUrl;

	public WikiSource() {
	}

	public WikiSource(String name, String baseUrl, String apiUrl, String sourceUrl) {
		this.name = name;
		this.baseUrl = baseUrl;
		this.apiUrl = apiUrl;
		this.sourceUrl = sourceUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public static List<WikiSource> getAll() {
		return Arrays.asList(WIKIPEDIA_FR, WIKTIONARY_FR, WIKIPEDIA_EN, WIKTIONARY_EN, WIKITRAVEL);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiUrl == null) ? 0 : apiUrl.hashCode());
		result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sourceUrl == null) ? 0 : sourceUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WikiSource other = (WikiSource) obj;
		if (apiUrl == null) {
			if (other.apiUrl != null)
				return false;
		} else if (!apiUrl.equals(other.apiUrl))
			return false;
		if (baseUrl == null) {
			if (other.baseUrl != null)
				return false;
		} else if (!baseUrl.equals(other.baseUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sourceUrl == null) {
			if (other.sourceUrl != null)
				return false;
		} else if (!sourceUrl.equals(other.sourceUrl))
			return false;
		return true;
	}

}
