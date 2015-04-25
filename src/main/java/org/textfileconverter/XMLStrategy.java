/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.textfileconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ocean
 */
public class XMLStrategy implements FileType {

    private final static String DELIMITER = ".";

    @Override
    public List<List<String>> convert(BufferedReader reader) throws IOException {
	List<List<String>> sentences = new ArrayList<>();
	List<String> words = new ArrayList<>();
	char[] cbuf = new char[15];
	StringBuilder sb = new StringBuilder();
	while (reader.read(cbuf) != -1) {
	    words = new ArrayList<>();
	    String s = String.valueOf(cbuf);//Sprawdzic czy char mozna odrazu na stringbuilder przerobic
	    boolean c = s.contains(DELIMITER);//int indx = s.indexOf(".");
	    if (c) {
		int indx = s.indexOf(DELIMITER);
		sb.append(s.substring(0, indx));
		String[] res = sb.toString().trim().split("(\\W)+");
		ArrayList<String> result = new ArrayList(Arrays.asList(res));
		Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
		result.stream().forEach(words::add);
		sentences.add(words);
		sb = new StringBuilder();
		sb.append(s.substring(indx + 1));
	    } else {
		sb.append(cbuf);
	    }
	}

	return sentences;
    }

}
