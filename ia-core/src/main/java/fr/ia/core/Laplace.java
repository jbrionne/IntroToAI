package fr.ia.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LAPLACE SMOOTHING LS(k) p(x) (count(x) + k) /(N + k|x|)
 *
 * |x| is the number of values that the variable x can take on. k is a smoothing
 * parameter. And N is the total number of occurrences of x (the variable, not
 * the value) in the sample size.
 *
 */
public class Laplace {
	
	private static final Logger LOG = LoggerFactory.getLogger(Laplace.class);
	
	public static final MathContext MC = new MathContext(10, RoundingMode.HALF_DOWN);	
	
	private Set<Classe> cl = new HashSet<>();

	private int k;

	private Set<String> elementsUnique = new HashSet<>();

	public Laplace(int k, Classe... classes) {
		this.k = k;
		cl.addAll(Arrays.asList(classes));

		for (Classe c : classes) {
			Set<Entry<String, Integer>> es = c.getEntrySetElementUnique();
			for (Entry<String, Integer> e : es) {
				elementsUnique.add(e.getKey());
			}
		}
	}

	public int getClassesSize() {
		return cl.size();
	}

	public Map<String, BigDecimal> classesProbability() {
		Map<String, BigDecimal> classesProbability = new HashMap<>();
		int n = getN();
		for (Classe c : cl) {
			classesProbability.put(c.getName(), laplace(n, c));
		}
		return classesProbability;
	}

	public BigDecimal conditionalProbability(String word, Classe c) {
		BigDecimal up = new BigDecimal(c.getElementsUniqueSpecificSize(word)
				+ k);
		BigDecimal down = new BigDecimal(c.getElementsWordSize() + k
				* elementsUnique.size());
		if(LOG.isDebugEnabled()) {
			LOG.debug("up {} {} {}", c.getElementsUniqueSpecificSize(word), k, up);
			LOG.debug("down {} {} {}", c.getElementsWordSize(), k
					* elementsUnique.size(), down);
		}
		return up.divide(down, MC);
	}
	
	public BigDecimal conditionalProbabilityInv(Classe c, Classe cinv, String word1, String word2) {
		// Bayes rule P(A|B) = P(B|A)*P(A) / P(B)
		BigDecimal up = conditionalProbability(word1, c).multiply(conditionalProbability(word2, c)).multiply(laplace(getN(), c));
		BigDecimal down = up.add(conditionalProbability(word1, cinv).multiply(conditionalProbability(word2, cinv)).multiply(laplace(getN(), cinv)));
		return up.divide(down, MC);
	}
	

	private BigDecimal laplace(int n, Classe c) {
		BigDecimal up = new BigDecimal(c.getElementsSize() + k);
		BigDecimal down = new BigDecimal(n + k * cl.size());
		return up.divide(down, MC);
	}

	private int getN() {
		int N = 0;
		for (Classe c : cl) {
			N += c.getElementsSize();
		}
		return N;
	}

}
