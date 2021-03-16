import dev.hottek.data.JsonReader;
import dev.hottek.data.JsonWriter;
import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JsonReaderAndWriterTest {

    private String data;
    private List<Account> accounts;

    @Before
    public void setup() {
        data = "[{\"name\":\"asdf\",\"balance\":1243449.0,\"transactions\":[{\"participant\":\"du\",\"subject\":\"asdf\",\"timestamp\":1615762800000,\"value\":1.0},{\"participant\":\"ich\",\"subject\":\"asdfg\",\"timestamp\":1615762800000,\"value\":1243324.0}]}]";
    }

    @Test
    public void jsonReaderTest() {
        JsonReader jsonReader = new JsonReader();
        accounts = jsonReader.readJsonFromString(data);
        Assert.assertEquals(1, accounts.size());
        List<Transaction> transactions = accounts.get(0).getTransactions();
        Assert.assertEquals(2, transactions.size());
    }

    @Test
    public void jsonWriterTest() {
        JsonReader jsonReader = new JsonReader();
        accounts = jsonReader.readJsonFromString(data);
        JsonWriter jsonWriter = new JsonWriter();
        String dataFromWriter = jsonWriter.writeToString(accounts);
        Assert.assertEquals(data, dataFromWriter);
    }
}
