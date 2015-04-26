package org.textfileconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException, Exception {

	Path path = Paths.get("input.txt");
//	, StandardCharsets.UTF_8

	try (BufferedReader reader = Files.newBufferedReader(path)) {

	    FileConverter fc = new FileConverter(new XMLStrategy());
	    List<List<String>> sentences = fc.convert(reader);
	    printXmlOnConsole(sentences);
	} catch (IOException ex) {
	    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	}

	try (BufferedReader reader = Files.newBufferedReader(path)) {
//	    FileConverter fc = new FileConverter(new XMLStrategy());
//	    List<List<String>> sentences = fc.convert(reader);
//	    printXmlOnConsole(sentences);
//	    FileConverter fc = new FileConverter(new CSVStrategy());
//	    List<List<String>> sentences = fc.convert(reader);
	} catch (IOException ex) {
	    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	}
	System.out.println("Hello World!");

    }

    public static void printXmlOnConsole(List<List<String>> sentences) {
	System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
	System.out.println("<text>");
	sentences.stream().map((sentence) -> {
	    System.out.println("    <sentence>");
	    return sentence;
	}).map((sentence) -> {
	    sentence.stream().forEach((r) -> {
		System.out.format("	    <word>%s</word>\n", r);
	    });
	    return sentence;
	}).forEach((sentence) -> {
	    System.out.println("    </sentence>");
	});
	System.out.println("</text>");
    }

    public static void printCsvOnConsole(List<List<String>> sentences) {
	int sentenceCount = 0;
	sentences.stream().map((sentence) -> {
	    System.out.println("Sentence " + sentenceCount);
	    return sentence;
	}).map((sentence) -> {
	    sentence.stream().forEach((r) -> {
		System.out.format("	    <word>%s</word>\n", r);
	    });
	    return sentence;
	}).forEach((sentence) -> {
	    System.out.println("    </sentence>");
	});
	System.out.println("</text>");
    }
}
