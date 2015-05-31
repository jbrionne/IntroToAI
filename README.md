# IntroToAI
Examples of course : https://www.udacity.com/course/viewer#!/c-cs271/

Laplace smoothing

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


 output :
 2015-05-31 17:28:42,503 [main] INFO fr.ia.core.Main - Probability Movie 0.5 
 2015-05-31 17:28:42,507 [main] INFO fr.ia.core.Main - Probability Song 0.5  
 2015-05-31 17:28:42,507 [main] INFO fr.ia.core.Main - 0.1578947368
 2015-05-31 17:28:42,507 [main] INFO fr.ia.core.Main - 0.1052631579
 2015-05-31 17:28:42,507 [main] INFO fr.ia.core.Main - 0.05263157895
 2015-05-31 17:28:42,507 [main] INFO fr.ia.core.Main - 0.1052631579
 2015-05-31 17:28:42,508 [main] INFO fr.ia.core.Main - 0.4285714285
