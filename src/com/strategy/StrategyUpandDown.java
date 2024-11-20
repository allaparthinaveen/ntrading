package com.strategy;

import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;

public class StrategyUpandDown {
	
	/**
	 * I/O entryValue, buyPercent, sellPercent
	 * currentValue is dynamic input form csv or excel file or from DB
	 * 
	 * BL:
	 * Read currentValue from excel and iterate over for each value
	 * if currentValue > entryValue && percentage of currentValue and entryValue > sellPercent then calculate sellValue
	 * else if percentage of entryValue and currentValue > buyPercent then calculate buyValue
	 */
	public static void upandDownStrategy(BigDecimal entryValue, BigDecimal buyPercent, BigDecimal sellPercent){
		
		String excelFilePath = "test.xlsx";
		
		try (FileInputStream fis = new FileInputStream(excelFilePath)){
			Workbook workbook = new XSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			for(Row row : sheet){
				if(row.getRownum() == 0) continue; //skip header row
				
				Cell currentCellValue = row.getCell(0); //assumees current value is in the first column
				
				if (currentCellValue != null && currentCellValue.getCellType() == CellType.NUMERIC){
					BigDecimal currentValue = BigDecimal.valueOf(currentCellValue.getNumericCellValue());
					BigDecimal percentageDiff = currentValue.subtract(entryValue).divide(entryValue, 4, RoundingMode.HALF_UP);
					
					if(currentValue.compareTo(entryValue) > 0 && percentageDiff.compareTo(sellPercent) >0){
						BigDecimal sellValue = currentValue.subtract(entryValue);
						System.out.println("Current Value: "+ currentValue +", Sell Value: " + sellValue);
						
					} else if (percentageDiff.negate().compareTo(buyPercent) > 0) {
						BigDecimal buyValue = entryValue.subtract(currentValue);
						System.out.println("Current Value: "+ currentValue +", Buy Value: " + buyValue);
					}
				}
			}
		} catch (IOException e){
			System.err.print("Error reading Excel file: " + e.getMessage());
		} catch (Exception e){
			System.err.println("An unexpected error occurred: "+ e.getMessage());
		}
		
		
	}

}
