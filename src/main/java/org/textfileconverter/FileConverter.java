/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.textfileconverter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author ocean
 */
public class FileConverter {

    private final FileType fileTypeStrategy;

    public FileConverter(FileType file) {
	fileTypeStrategy = file;
    }

    public void convert(BufferedReader reader) throws IOException {
	fileTypeStrategy.convert(reader);
    }
}
