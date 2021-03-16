import dev.hottek.data.model.AccountPanelData;
import dev.hottek.data.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountPanelDataTest {

    private AccountPanelData panelDataWithoutInitialBalance;
    private AccountPanelData panelDataWithInitialBalance;

    @Before
    public void setup() {
        panelDataWithoutInitialBalance = new AccountPanelData("Account Without Transactions",
                                        0.0F, null);

        panelDataWithInitialBalance = new AccountPanelData("Account With Transactions",
                                        123.0F, null);
    }

    @Test
    public void checkBalance() {
        Assert.assertEquals(0.0F, panelDataWithoutInitialBalance.getBalance(), 0.1F);
        Assert.assertEquals(123.0F, panelDataWithInitialBalance.getBalance(), 0.1F);
    }

    @Test
    public void addTransaction() {
        Transaction t1 = new Transaction("t1", "s1", System.currentTimeMillis(), 123.0F);

        panelDataWithoutInitialBalance.addTransaction(t1);
        Assert.assertEquals(123.0F, panelDataWithoutInitialBalance.getBalance(), 0.1F);

        panelDataWithInitialBalance.addTransaction(t1);
        Assert.assertEquals(246.0F, panelDataWithInitialBalance.getBalance(), 0.1F);
    }
}
