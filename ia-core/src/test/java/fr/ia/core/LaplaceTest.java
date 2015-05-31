package fr.ia.core;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;

public class LaplaceTest {

	@Test
	public void addElement() {

		String keyMovie = "Movie";
		String keySong = "Song";

		Classe movie = new Classe(keyMovie);
		movie.add("A PERFECT WORLD");
		movie.add("MY PERFECT WOMAN");
		movie.add("PRETTY WOMAN");

		Classe song = new Classe(keySong);
		song.add("A PERFECT DAY");
		song.add("ELECTRIC STORM");
		song.add("ANOTHER RAINY DAY");

		Laplace laplace = new Laplace(1, movie, song);
		Map<String, BigDecimal> classesProbability = laplace
				.classesProbability();

		assertTrue(MathUtils.equalsApproxBigDecimal(new BigDecimal(0.5, Laplace.MC),
				classesProbability.get(keyMovie)));

		assertTrue(MathUtils.equalsApproxBigDecimal(new BigDecimal(0.5, Laplace.MC),
				classesProbability.get(keySong)));

		assertTrue(MathUtils.equalsApproxBigDecimal(
				new BigDecimal(3.0 / 19.0, Laplace.MC),
				laplace.conditionalProbability("PERFECT", movie)));

		assertTrue(MathUtils.equalsApproxBigDecimal(
				new BigDecimal(2.0 / 19.0, Laplace.MC),
				laplace.conditionalProbability("PERFECT", song)));

		assertTrue(MathUtils.equalsApproxBigDecimal(
				new BigDecimal(1.0 / 19.0, Laplace.MC),
				laplace.conditionalProbability("STORM", movie)));

		assertTrue(MathUtils.equalsApproxBigDecimal(
				new BigDecimal(2.0 / 19.0, Laplace.MC),
				laplace.conditionalProbability("STORM", song)));

		assertTrue(MathUtils.equalsApproxBigDecimal(
				new BigDecimal(3.0 / 7.0, Laplace.MC),
				laplace.conditionalProbabilityInv(movie, song, "PERFECT",
						"STORM")));

	}

	
}
