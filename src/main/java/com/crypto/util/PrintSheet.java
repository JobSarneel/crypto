package com.crypto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.crypto.model.Transactie;

public class PrintSheet {

	public static void print(String inputPath) throws IOException {

		File file = new File(inputPath);
		System.out.println(file.exists());
		FileInputStream fi = new FileInputStream(file);
		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(fi);

		//Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() == 0)
				row = rowIterator.next();

			System.out.println(row.getRowNum());

			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t\t");
					break;
				}
			}
			System.out.println("");
		}
		fi.close();

	}

	public static ArrayList<Transactie> toArrayList(String inputPath) throws IOException, ParseException {

		File file = new File(inputPath);
		//		System.out.println(file.exists());
		FileInputStream fi = new FileInputStream(file);
		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(fi);

		//Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		ArrayList<Transactie> lijst = new ArrayList<Transactie>();
		

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Transactie transactie = new Transactie();
			if (row.getRowNum() == 0) {
				row = rowIterator.next();
			}		

			//For each row, iterate through each columns
			//In this version we visit columns one by one, to map every row to an object.

			//Instantiate cellIterator, arraylist and transactie 
			Iterator<Cell> cellIterator = row.cellIterator();


			//First column
			Cell cell = cellIterator.next();
			
		}

		return lijst;


	}

}


