package com.pmerienne.wikimobile.server.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.pmerienne.wikimobile.server.parser.WikitextParserImpl;

public class WikitextParserImplTest {

	private WikitextParserImpl wikitextParser = new WikitextParserImpl();

	@Test
	public void testParse() {
		StringBuilder rawContent = new StringBuilder();
		rawContent.append("[[Image:Localisation Maine-et-Loire.png|frame|right|Localisation du département de Maine-et-Loire]]").append("\n");
		rawContent.append("Le '''Maine-et-Loire''' est un département de l'Ouest de la [[France]] dans la région [[Pays-de-la-Loire]].").append("\n");
		rawContent.append("Le Maine-et-Loire correspond également à l'Anjou.").append("\n");
		rawContent.append("== Boire ==").append("\n");
		rawContent.append(" Liqueur :").append("\n");
		rawContent.append(" * Cointreau").append("\n");
		rawContent.append("== Se loger ==").append("\n");
		rawContent
				.append(" * Un séjour ou des [http://www.malocationvacances.fr/location/maine_et_loire-185-0.htm vacances en Maine-et-Loire] prévus ? Découvrez notre sélection de [http://www.malocationvacances.fr/location/maison-maine_et_loire-185-0.htm maisons Maine-et-Loire], [http://www.malocationvacances.fr/location/gite-maine_et_loire-185-0.htm gîtes Maine-et-Loire], [http://www.malocationvacances.fr/location/chambre_dhotes-maine_et_loire-185-0.htm chambres d'hôtes Maine-et-Loire] et [http://www.malocationvacances.fr/location/appartement-maine_et_loire-185-0.htm appartements Maine-et-Loire] !")
				.append("\n");
		rawContent.append(" * [http://www.monimmobilier.com/immobilier/maine_et_loire-185-0.htm Immobilier Maine-et-Loire]").append("\n");
		rawContent.append("{{Dans|Pays-de-la-Loire}}").append("\n");
		rawContent.append("[http://www.anjou-tourisme.com Tourisme en Anjou]").append("\n");
		rawContent.append(" [[en:Maine-et-Loire]]").append("\n");
		rawContent.append("[[Wikipedia:Maine-et-Loire]]").append("\n");

		String html = this.wikitextParser.parseToHtml(rawContent.toString());
		assertNotNull(html);
	}
}
