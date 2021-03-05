package dhbw.demo.text_extractor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelTextExtractor extends TextExtractor{
    //TODO to array, no null cells

    @Override
    public String extractText(String path) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++){
                Sheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()){
                    Row nextRow = rowIterator.next();

                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    while (cellIterator.hasNext()){
                        Cell nextCell = cellIterator.next();
                        String cellValue;

                        switch (nextCell.getCellType()) {
                            case STRING:
                                cellValue = nextCell.getStringCellValue();
                                stringBuilder.append(cellValue);
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(nextCell.getBooleanCellValue());
                                stringBuilder.append(cellValue);
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(nextCell.getNumericCellValue());
                                stringBuilder.append(cellValue);
                                break;
                        }
                    }
                }

                fileInputStream.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(errorMessage);
        }

        return stringBuilder.toString();
    }
}
