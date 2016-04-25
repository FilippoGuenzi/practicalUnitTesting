package re.weplay.old;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import re.weplay.old.Money;

@RunWith(JUnitParamsRunner.class)
public class MoneyParameterizedTest
{

    private static final Object[] getMoney()
    {
        return new Object[]
        {
                new Object[]
                {
                        10,
                        "USD"
                }, new Object[]
                {
                        20,
                        "EUR"
                }
        };
    }

    @Test
    @Parameters(method = "getMoney")
    public void constructorShouldSetAmountAndCurrencyProperly(int amount, String currency)
    {
        Money money = new Money(amount, currency);

        Assert.assertNotNull(money);
        Assert.assertEquals(amount, money.getAmount());
        Assert.assertEquals(currency, money.getCurrency());

    }

}
