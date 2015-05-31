package fr.ia.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Classe {

	private static final Logger LOG = LoggerFactory.getLogger(Classe.class);

	private String name;
	private List<String> elements = new ArrayList<>();
	private Map<String, Integer> elementsUnique = new HashMap<>();

	public Classe(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void add(String e) {
		if (e == null || e.isEmpty()) {
			throw new IllegalArgumentException(
					"Element musn't be null or empty");
		}
		String upperEl = e.toUpperCase();
		String trimEl = upperEl.trim().replaceAll(" +", " ");

		String[] words = trimEl.split(" ");
		for (String word : words) {
			LOG.debug("add " + word);
			Integer size = elementsUnique.get(word);
			if (size == null) {
				size = 1;
			} else {
				size = size + 1;
			}
			elementsUnique.put(word, size);
		}

		elements.add(trimEl);
	}

	public int getElementsSize() {
		return elements.size();
	}

	public int getElementsWordSize() {
		int n = 0;
		for (Entry<String, Integer> c : elementsUnique.entrySet()) {
			n += c.getValue();
		}
		return n;
	}

	public int getElementsUniqueSize() {
		return elementsUnique.size();
	}

	public int getElementsUniqueSpecificSize(String word) {
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException("Word musn't be null or empty");
		}
		String upperW = word.toUpperCase();
		String trimW = upperW.trim();

		for (char c : word.toCharArray()) {
			if (c == ' ') {
				throw new IllegalArgumentException("Word contains a space !");
			}
		}

		int nb = 0;
		Integer res = elementsUnique.get(trimW);
		if (res != null) {
			nb = res.intValue();
		}
		return nb;
	}

	public Set<Entry<String, Integer>> getEntrySetElementUnique() {
		return elementsUnique.entrySet();
	}
}
