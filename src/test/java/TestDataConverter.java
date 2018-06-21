import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataConverter;
import training.supportbank.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestDataConverter {
    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException, ParseException {
        DataConverter dataConverter = new DataConverter();
        Transaction testTransaction;
        List<Transaction> testTransactionsList = new ArrayList<>();

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        testTransaction = new Transaction(date, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        testTransactionsList.add(testTransaction);
        testTransaction = new Transaction(date, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        testTransactionsList.add(testTransaction);

        BufferedReader testBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(testBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,5.00", "01/01/14,Jane D,John D,Coffee,2.00", null);
        List<Transaction> actualDataConverterOutput = dataConverter.extractTransactions(testBufferedReader);
        assertThat(actualDataConverterOutput)
                .hasSameClassAs(testTransactionsList)
                .hasSameSizeAs(testTransactionsList);
    }
}
