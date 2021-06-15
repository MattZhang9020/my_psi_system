package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CreateSellExcelFile {

	public CreateSellExcelFile(ArrayList<SellDataItems> data) {
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("�P�f����");
			
			String[] columnHeadings = {"�P�f�s��", "�U�Ƚs��", "�U�ȩm�W", "���~�զX�W��", "�P�f�ƶq", "���", "�P�f���B"};
			
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.BLACK.index);
			
			CellStyle headerStyle = workbook.createCellStyle();	
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			
			Row headerRow = sheet.createRow(0);
			for(int i = 0; i < columnHeadings.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeadings[i]);
				cell.setCellStyle(headerStyle);
			}
			
			int rowCount = 1;
			for (SellDataItems wItem : data) {
				Row row = sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(wItem.getSellId());
				row.createCell(1).setCellValue(wItem.getCustomerId());
				row.createCell(2).setCellValue(wItem.getCustomerName());
				row.createCell(3).setCellValue(wItem.getCombineName());
				row.createCell(4).setCellValue(wItem.getSellAmount());
				row.createCell(5).setCellValue(wItem.getSellPrice());
				row.createCell(6).setCellValue(wItem.getTotalSellPrice());
			}
			
			for (int i = 0; i < columnHeadings.length; i++) {
				sheet.autoSizeColumn(i);
			}
			
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("������X");
			File selectedDirectory = chooser.showDialog(new Stage());
			
			FileOutputStream fileOutputStream = new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\�P�f����.xlsx");
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}