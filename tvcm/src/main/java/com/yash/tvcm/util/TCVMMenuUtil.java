package com.yash.tvcm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class TCVMMenuUtil {
	
	private static final String TCVM_MENU_FILE_PATH = "src/main/resources/files/TCVMMenu.txt";
	private static Logger logger=Logger.getLogger(TCVMMenuUtil.class.getName());
	
	public static void getMenu() throws FileNotFoundException {
		BufferedReader menuReader = new BufferedReader(new FileReader(new File(TCVM_MENU_FILE_PATH)));
		try {
			readAndPrintMenuFile(menuReader);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}
	}

	private static void readAndPrintMenuFile(BufferedReader menuReader) throws IOException {
		String line = menuReader.readLine();
		while (line != null) {
			System.out.println(line);
			line = menuReader.readLine();
		}
	}
}
