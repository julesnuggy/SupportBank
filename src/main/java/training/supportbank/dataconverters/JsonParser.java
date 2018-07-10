package training.supportbank.dataconverters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import training.supportbank.adaptors.TransactionAdaptor;
import training.supportbank.models.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<Transaction> parseJsonToTransactions(String filename) throws FileNotFoundException {
            Type collectionType = new TypeToken<ArrayList<Transaction>>() {
            }
                    .getType();

            Gson gson = new GsonBuilder().registerTypeAdapter(Transaction.class, new TransactionAdaptor()).create();
            JsonReader reader = new JsonReader(new FileReader(filename));
            List<Transaction> transactions = gson.fromJson(reader, collectionType);
            return transactions;
    }
}
