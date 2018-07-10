import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataConverter;
import training.supportbank.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDataConverter {
    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException, ParseException {
        DataConverter dataConverter = new DataConverter();
        Transaction transaction;
        List<Transaction> testTransactionsList = new ArrayList<>();

        LocalDate date = LocalDate.parse("01/01/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        transaction = new Transaction(date, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        testTransactionsList.add(transaction);
        transaction = new Transaction(date, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        testTransactionsList.add(transaction);

        BufferedReader testBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(testBufferedReader.readLine()).thenReturn("01/01/2014,John D,Jane D,Doughnuts,5.00", "01/01/2014,Jane D,John D,Coffee,2.00", null);
        List<Transaction> actualDataConverterOutput = dataConverter.extractTransactions(testBufferedReader);
        assertThat(actualDataConverterOutput)
                .hasSameClassAs(testTransactionsList)
                .hasSameSizeAs(testTransactionsList);
    }
}
