package com.crypto;

import java.io.IOException;
import java.util.ArrayList;

import com.crypto.model.Transactie;
import com.crypto.util.PrintSheet;

public class MainApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		// Set input file location 
		String inputPath = "/home/workspaceJava/crypto-app/excel/test.xls";
		PrintSheet.print(inputPath);

	}

}
