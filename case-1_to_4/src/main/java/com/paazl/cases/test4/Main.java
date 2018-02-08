package com.paazl.cases.test4;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class Main {

    private final static String ROOT_PATH = "src/main/resources/com/paazl/cases/test4";

    /*
     * Read and parse "testdata.csv" (located in
     * src/main/resources/com/paazl/cases/test4) into a list of POJOs. Increase
     * each POJO's "number" field by 1, and move the "date" field one day ahead.
     * Write the results to a file named "testdata.new.csv".
     */

    public static void main(String[] args)
            throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException
    {
        List<TestData> testDataList = csvRead(new CSVDaoFactory("/csv-config.xml"));

        //TODO: Parse "testdata.csv" and increase each POJO's number field by 1, and move the date field one day ahead
        testDataList.stream()
                .map(testData -> { testData.setNumber(increaseNumber(testData.getNumber()));
                    testData.setDate(increaseDay(testData.getDate()));
                    return testData; })
                .forEach(System.out::println);

        csvWriter(testDataList, Paths.get(ROOT_PATH + "/testdata.new.csv"));
    }

    static List<TestData> csvRead(CSVDaoFactory factory) {
        return new CSVDao(factory).find(TestData.class);
    }

    static void csvWriter(List<TestData> testDataList, Path path)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException
    {
        try (Writer writer = Files.newBufferedWriter(path)) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(';')
                    .build();
            beanToCsv.write(testDataList);
        }
    }

    private static Date increaseDay(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
    }

    private static int increaseNumber(int number) {
        return number + 1;
    }
}
