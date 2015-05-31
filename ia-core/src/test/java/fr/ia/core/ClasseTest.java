package fr.ia.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClasseTest {

	@Test
	public void addElement() {
		Classe movie = new Classe("Movie");
		movie.add("A perfect    WORLD");
		movie.add("  MY PERFECT WoMAN");
		movie.add("PRETTY  WOMAN ");

		assertEquals(3, movie.getElementsSize());
		assertEquals(6, movie.getElementsUniqueSize());
		assertEquals(2, movie.getElementsUniqueSpecificSize("perfect"));
	}

}
