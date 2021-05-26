import dev.hottek.data.JsonReader;
import dev.hottek.data.JsonWriter;
import dev.hottek.data.model.Account;
import dev.hottek.data.model.HistoryEntry;
import dev.hottek.data.model.SafeFormat;
import dev.hottek.data.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JsonReaderAndWriterTest {

    private String data;
    private SafeFormat dataAsSafeFormat;

    @Before
    public void setup() {
        data = "{\"accountList\":[{\"name\":\"safd\",\"balance\":123.0,\"transactions\":[]},{\"name\":\"asfd\",\"balance\":123.0,\"transactions\":[{\"participant\":\"asfd\",\"subject\":\"asf\",\"timestamp\":1621980000000,\"value\":123.0}]}],\"historyEntryList\":[{\"message\":\"Finance Manager instance created\",\"timestamp\":1622036066308},{\"message\":\"New Account Panel safd Created\",\"timestamp\":1622036066310},{\"message\":\"New Account Panel asfd Created\",\"timestamp\":1622036070445},{\"message\":\"New Account Panel asfd Created\",\"timestamp\":1622036070445},{\"message\":\"Account asfd added Transaction with subject: asf and value: 123.0\",\"timestamp\":1622036075485},{\"message\":\"Account asfd added Transaction with subject: asf and value: 123.0\",\"timestamp\":1622036075485}]}";
    }

    @Test
    public void jsonReaderTest() {
        JsonReader jsonReader = new JsonReader();
        dataAsSafeFormat = jsonReader.readJsonFromString(data);
        List<Account> accounts = dataAsSafeFormat.getAccountList();
        List<HistoryEntry> historyEntries = dataAsSafeFormat.getHistoryEntryList();
        Assert.assertEquals(2, accounts.size());
        Assert.assertEquals(6, historyEntries.size());
        List<Transaction> transactions = accounts.get(1).getTransactions();
        Assert.assertEquals(1, transactions.size());
    }

    @Test
    public void jsonWriterTest() {
        JsonReader jsonReader = new JsonReader();
        dataAsSafeFormat = jsonReader.readJsonFromString(data);
        JsonWriter jsonWriter = new JsonWriter();
        String dataFromWriter = jsonWriter.writeToString(dataAsSafeFormat);
        Assert.assertEquals(data, dataFromWriter);
    }
}
