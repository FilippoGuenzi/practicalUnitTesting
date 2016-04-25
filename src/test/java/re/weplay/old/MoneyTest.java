package re.weplay.old;

import org.junit.Assert;
import org.junit.Test;
import re.weplay.old.Money;

public class MoneyTest
{

    @Test
    public void constructorShouldSetAmountAndCurrencyProperly()
    {
        int inputAmount = 10;
        String inputCurrency = "EUR";
        Money money = new Money(inputAmount, inputCurrency);

        Assert.assertNotNull(money);
        Assert.assertEquals(inputAmount, money.getAmount());
        Assert.assertEquals(inputCurrency, money.getCurrency());

    }

}
