package training.supportbank.dataconverters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.models.Transaction;
import training.supportbank.adaptors.TransactionAdaptor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final Logger logger = LogManager.getLogger("JsonParser");

    public List<Transaction> parseJsonToTransactions(String filename) throws FileNotFoundException {
        Type collectionType = new TypeToken<ArrayList<Transaction>>(){}
                .getType();

        Gson gson = new GsonBuilder().registerTypeAdapter(Transaction.class, new TransactionAdaptor()).create();
        JsonReader reader = new JsonReader(new FileReader(filename));
        List<Transaction> transactions = gson.fromJson(reader, collectionType);
        logger.info(transactions);
        return transactions;
    }
}
