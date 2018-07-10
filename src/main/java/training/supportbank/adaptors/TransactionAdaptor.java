package training.supportbank.adaptors;

import com.google.gson.*;
import training.supportbank.Transaction;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionAdaptor implements JsonDeserializer<Transaction> {

    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            String jsonDate = jsonObject.get("date").getAsString();
            LocalDate date = LocalDate.parse(jsonDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final String from = jsonObject.get("fromAccount").getAsString();
            final String to = jsonObject.get("toAccount").getAsString();
            final String narrative = jsonObject.get("narrative").getAsString();
            final BigDecimal amount = jsonObject.get("amount").getAsBigDecimal();

            final Transaction transaction = new Transaction(date, from, to, narrative, amount);
            return transaction;
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
