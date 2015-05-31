package fr.ia.core;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		Classe movie = new Classe("Movie");
		movie.add("A PERFECT WORLD");
		movie.add("MY PERFECT WOMAN");
		movie.add("PRETTY WOMAN");

		Classe song = new Classe("Song");
		song.add("A PERFECT DAY");
		song.add("ELECTRIC STORM");
		song.add("ANOTHER RAINY DAY");

		Laplace laplace = new Laplace(1, movie, song);
		Map<String, BigDecimal> classesProbability = laplace
				.classesProbability();

		for (Entry<String, BigDecimal> e : classesProbability.entrySet()) {
			LOG.info("Probability {} {} ", e.getKey(), e.getValue());
		}

		LOG.info("{}", laplace.conditionalProbability("PERFECT", movie));
		LOG.info("{}", laplace.conditionalProbability("PERFECT", song));
		LOG.info("{}", laplace.conditionalProbability("STORM", movie));
		LOG.info("{}", laplace.conditionalProbability("STORM", song));

		LOG.info("{}", laplace.conditionalProbabilityInv(movie, song,
				"PERFECT", "STORM"));

	}

}
