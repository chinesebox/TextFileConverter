/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.textfileconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ocean
 */
public class CSVStrategy implements FileType {

    private static final String DELIMITER = ".";

    private int sentencesCount = 0;

    private int wordsMax = 0;

    @Override
    public List<List<String>> convert(BufferedReader reader) throws IOException {
	List<List<String>> sentences = new ArrayList<>();
	countSentencesAndWords(reader);
	System.out.println("RESuLT: " + sentencesCount + ":" + wordsMax);
	displayCSV();
	return sentences;
    }

    private void countSentencesAndWords(BufferedReader reader) throws IOException {
	char[] cbuf = new char[15];
	StringBuilder sb = new StringBuilder();

	while (reader.read(cbuf) != -1) {
	    String s = String.valueOf(cbuf);//Sprawdzic czy char mozna odrazu na stringbuilder przerobic
	    boolean c = s.contains(DELIMITER);
	    if (c) {
		int indx = s.indexOf(DELIMITER);
		sb.append(s.substring(0, indx));
		String[] res = sb.toString().trim().split("(\\W)+");
		int wordsCount = res.length;
		wordsMax = wordsMax < wordsCount ? wordsCount : wordsMax;
		sentencesCount++;
		sb = new StringBuilder();
		sb.append(s.substring(indx + 1));
	    } else {
		sb.append(cbuf);
	    }
	}
    }

    private void displayCSV() throws IOException {
	Path path = Paths.get("input.txt");
	BufferedReader reader = Files.newBufferedReader(path);
	for (int i = 0; i < wordsMax; i++) {
	    System.out.print(", Word " + (i + 1));
	}
	System.out.println();
	List<List<String>> sentences = new ArrayList<>();
	List<String> words;
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

		System.out.print("Sentence " + sentences.size());
		for (String word : words) {
		    System.out.print(", " + word);
		}

		sb = new StringBuilder();
		sb.append(s.substring(indx + 1));
	    } else {
		sb.append(cbuf);
	    }
	}

//	return sentences;
	for (int i = 0; i < sentencesCount; i++) {
	    System.out.println("Sentence " + (i + 1));
	}
    }

}
