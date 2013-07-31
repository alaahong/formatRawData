package com.successfactors.perflab;

import java.util.Comparator;

public class ComparatorDate implements Comparator<String> {
	@Override
	public int compare(String str0, String str1) {
		return str0.substring(0, 23).compareTo(str1.substring(0, 23));
	}
}
