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

/**
 *
 * @author ocean
 */
public class XMLStrategy implements FileType {

    @Override
    public void convert(BufferedReader reader) throws IOException {
	char[] cbuf = new char[15];
//	    int off = 0;
//	    int len = 11;
	StringBuilder sb = new StringBuilder();
	System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
	System.out.println("<text>");
	while (reader.read(cbuf) != -1) {
	    String s = String.valueOf(cbuf);//Sprawdzic czy char mozna odrazu na stringbuilder przerobic
	    boolean c = s.contains(".");//int indx = s.indexOf(".");
	    if (c) {
		int indx = s.indexOf(".");
		sb.append(s.substring(0, indx));
		String[] res = sb.toString().trim().split("(\\W)+");
		ArrayList<String> result = new ArrayList(Arrays.asList(res));
		Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
		System.out.println("    <sentence>");
		result.stream().forEach((r) -> {
		    System.out.format("	    <word>%s</word>\n", r);
		});
		System.out.println("    </sentence>");
//		    System.out.println(indx + ">>> " + sb.toString());
		sb = new StringBuilder();
		sb.append(s.substring(indx + 1));
	    } else {
		sb.append(cbuf);
//		    System.out.println("### " + sb.toString());
	    }
	}
	System.out.println("</text>");
    }

}
