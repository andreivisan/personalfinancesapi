package io.programminglife.personalfinancesapi.util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public class CSVUtil {

    public static String CSV_FILE_TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile inputFile) {
        if (!CSV_FILE_TYPE.equals(inputFile.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CsvEntity> parseCsvToEntity(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                        .withIgnoreHeaderCase().withTrim().withQuote('"').withDelimiter(';'));) {

            List<CsvEntity> csvEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CsvEntity csvEntity = new CsvEntity();
                DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

                csvEntity.setTransactionDate(LocalDate.parse(csvRecord.get(0), formatter));
                csvEntity.setDescription(csvRecord.get(1));

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat format = new DecimalFormat("0.#");
                format.setDecimalFormatSymbols(symbols);
                csvEntity.setAmount(format.parse(csvRecord.get(6)).floatValue());

                csvEntity.setCategory("");
                csvEntity.setPaymentSystem(csvRecord.get(7));

                csvEntities.add(csvEntity);
            }

            return csvEntities;
        } catch (IOException | ParseException exception) {
            throw new MyFinancesException("Failed to parse CSV file: " + exception.getMessage());
        }
    }

    public static Expense csvEntityToExpense(CsvEntity csvEntity, Category category, PaymentSystem paymentSystem) {
        Expense expense = new Expense();

        expense.setExpenseDate(csvEntity.getTransactionDate());
        expense.setLabel(csvEntity.getDescription());
        expense.setAmount(csvEntity.getAmount());
        expense.setCategory(category);
        expense.setPaymentSystem(paymentSystem);
        expense.setClient(null);

        return expense;
    }

}
