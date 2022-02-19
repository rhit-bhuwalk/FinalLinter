package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DataSource.*;
import Linter.*;
public class CLI {

	private DataParser dp;
	private Linter linter;

	public CLI() {
		dp = new DataParser();
		linter = new Linter();
		run();
	}
	
	public CLI(String filePath) {
		List<File> filesList = new ArrayList<>();
		listFiles(filePath, filesList);
		dp = new DataParser();
		linter = new Linter();
		try {
			dp.parse(filesList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : linter.lintAll(dp.getData())) {
				System.out.println(s);
	}
	}
	

	private void run() {
		Scanner scanner = new Scanner(System.in);
		boolean quit = true;
		while (quit) {
			System.out.println("Enter file path, or type \"quit\".");
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("quit")) {
				quit = false;
			} else {
				List<File> filesList = new ArrayList<>();
				listFiles(input, filesList);
				try {
					dp.parse(filesList);
					for (String s : linter.lintAll(dp.getData())) {
						System.out.println(s);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		scanner.close();
	}

	private static List<File> listFiles(String path, List<File> filesList) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".class")) {
				filesList.add(file);
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath(), filesList);
			}
		}
		return filesList;
	}

}
