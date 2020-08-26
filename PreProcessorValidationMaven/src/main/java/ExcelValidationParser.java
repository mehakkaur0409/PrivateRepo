import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ExcelValidationParser {

    private ArrayList<String> headersList;
    private File inputFilePath;
    private File outputFilePath;
    private List<String> missingHeadersList;
    private static Object InvalidCellOutputFileHeaders[];
    private Map<String, Object[]> outputDataMap = new LinkedHashMap<>();
    private int outputFileRowNum = 1;
    //To count total no of records tested.
    private int ProcessedRowNum = 0;
    private String outputFileName ;



    public ExcelValidationParser() {
    }

    public ExcelValidationParser(String inputFilepath, String outputFilePath) {
        this.headersList = new ArrayList<>();
        this.inputFilePath = new File(inputFilepath);
        this.outputFilePath = new File(outputFilePath);
    }


    private void headersValidation(Row HeadersRow) {
        Iterator<Cell> headerIterator = HeadersRow.cellIterator();
        while (headerIterator.hasNext()) {
            Cell cell = headerIterator.next();
            headersList.add(cell.getStringCellValue());
        }
        Set<String> predefinedHeadersSet = ValidatingConditionsDataStorage.headersMap.keySet();
        /* resultSet contains all headers that are not present in input file. */
        missingHeadersList = new ArrayList<String>();
        /* .add() returns false if element already exists */
        for (String element : predefinedHeadersSet)
            if (!headersList.contains(element)) missingHeadersList.add(element);

        //outputDataMap will be written in excel sheet
        if (!missingHeadersList.isEmpty()) {
            Object headerObject[] = new Object[missingHeadersList.size()];
            headerObject[0] = "MissingHeaders";
            for (int i = 1; i < headerObject.length; i++) {
                headerObject[i] = missingHeadersList.get(i);
            }
            outputDataMap.put(Integer.toString(outputFileRowNum), headerObject);
            outputFileRowNum++;
        }


    }

    private void cellValidation(Row recordRow, Sheet sheet) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        int colNum = 0;
        Iterator<Cell> cellItr = recordRow.iterator();
        DataFormatter formatter = new DataFormatter();
        while (cellItr.hasNext()) {
            Cell cell = cellItr.next();
           
                String cellValue = formatter.formatCellValue(sheet.getRow(ProcessedRowNum).getCell(colNum));
                if(cell.getColumnIndex()<headersList.size()) {
               
                	/* get parent header of this cell from "headerList" and mapping validation function from "headersMap" */
                int cellColumnIndex = cell.getColumnIndex();
                String parentCellHeader = headersList.get(cellColumnIndex);
              
                if (ValidatingConditionsDataStorage.headersMap.containsKey(parentCellHeader)) {
                	 boolean isCellValid=true;
                	if(!cellValue.contains(" ")) {
                    String validatingFunction = ValidatingConditionsDataStorage.headersMap.get(parentCellHeader);
                    Class<?> c = Class.forName("ValidatingConditions");
                    Method method = c.getDeclaredMethod(validatingFunction, String.class);
                    isCellValid  = (boolean) method.invoke(c.newInstance(), cellValue);
                	}
                    //outputDataMap will be written in excel sheet
                  
                    if (!isCellValid) {
                        if (InvalidCellOutputFileHeaders == null) {
                            InvalidCellOutputFileHeaders = new Object[3];
                            InvalidCellOutputFileHeaders[0] = "Row Number";
                            InvalidCellOutputFileHeaders[1] = "Column Name";
                            InvalidCellOutputFileHeaders[2] = "Column Value";
                            outputDataMap.put(Integer.toString(outputFileRowNum), InvalidCellOutputFileHeaders);
                        } 
                            // rowno, columnname, columnvalue
                            Object invalidCellObject[] = new Object[3];
                            int outputRowNumber=ProcessedRowNum+1;
                            invalidCellObject[0] = outputRowNumber;
                            invalidCellObject[1] = parentCellHeader;
                            invalidCellObject[2] = cellValue;
                            outputDataMap.put(Integer.toString(outputFileRowNum), invalidCellObject);
                        
                        outputFileRowNum++;
                    }
                }
               colNum++;
                }
            }


    }
    


    private void createOutputExcelFile() {
        //make this singelton

        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet(outputFileName);

        //Iterate over data and write to sheet
        if (!outputDataMap.isEmpty()) {
            Set<String> keyset = outputDataMap.keySet();
            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = outputDataMap.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(outputFilePath);
            workbook.write(out);
            out.close();
            System.out.println(outputFilePath + " file path written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validatingParser() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //todo: file exist check
        if (!inputFilePath.exists()) {
            throw new FileNotFoundException("Not found or not a file: " + inputFilePath.getPath());
        }
        FileInputStream excelInputStream = new FileInputStream(inputFilePath);
        Workbook workbook = new XSSFWorkbook(excelInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        outputFileName= sheet.getSheetName();
        Iterator<Row> rowItr = sheet.iterator();

        while (rowItr.hasNext()) {
            if (ProcessedRowNum == 0)
                headersValidation(rowItr.next());
            else {
                cellValidation(rowItr.next(), sheet);
            }
            ProcessedRowNum++;

        }
        workbook.close();
        excelInputStream.close();
        createOutputExcelFile();
        //ALGO
        //
        /*1. AFTER FIRST WHILE, WE WILL GET ALL COLUMN NAMES. sTORE IT IN AN ARRAYLIST(DYNAMIC AS ARRAY SIZE IS NOT FIXED)
         *   > PREDEFINED: FINAL CAN HAVE A HASHMAP<INDEX, COLUMNANME >
         * 3. (FieldCheck)ARRAYSLIST INDEX AND ITS VALUE SHOULD MATCH HASHMAP KEY/VALUE.
         *  > PREDEFINED: map having columnname and corresponding condition/functionname. call through reflection
         * 4. FOR EACH CELL OF ROW,, maintain a counter of cell as well as row
         *    - IF counter matches index in hashmap, THEN it  NEEDs TO CHECK.
         *     -ELSE IT doesn't NEED TO BE CHECKED.
         * 5. for that index, get columnname from hashmap1, and conditon for tht columnanme from hashmap2.
         *
         * validations: USE PATTERN CLASS OF STRING.
         *  -space validation function on ALL columns: logic WILL BE CALLED ON ALL VALIDITABLE  CELLS
         * -header function: normal2 format logic WILL BE CALLED UPON FIRST ROW OF SHEET ONLY
         * -number format function : round 4 spaces logic
         * -pure number function: no decimals
         * -short date format function: MM/DD/YYYY
         * -Accounting Format function: $ and Round 2 places
         * YYYY-MM format function
         * -number and text check function: maybe from cell_TYPE
         *
         * Output file: EXCEL, provider file name, total row count, date/time
         *  - line number, columnname1,  columnname2
         *    1(row count),  2
         * big functions: having above validation as well as cell specific validation* */


    }


}
