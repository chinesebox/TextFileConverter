package org.textfileconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
	Path path = Paths.get("input.txt");
//	, StandardCharsets.UTF_8
	try (BufferedReader reader = Files.newBufferedReader(path)) {
	    FileConverter fc = new FileConverter(new XMLStrategy());
	    fc.convert(reader);
	} catch (IOException ex) {
	    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	}
	System.out.println("Hello World!");
    }
}
