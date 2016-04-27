package re.weplay.old;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class MoneyIllegalArgumentExceptionTest
{

    private final int VALID_AMOUNT = 3;
    private final String VALID_CURRENCY = "EUR";

    private static Object[] getInvalidAmount()
    {
        return new Integer[][]
        {
                {
                        -5
                },
                {
                        -7
                }
        };
    }

    private static Object[] getInvalidCurrency()
    {
        return new String[][]
        {
                {
                        ""
                },
                {
                        null
                }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidAmount")
    public void constructorShouldThrowAnIAExceptionWithInvalidAmount(int amount)
    {
        String currency = VALID_CURRENCY;
        Money money = new Money(amount, currency);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidCurrency")
    public void constructorShouldThrowAnIAExceptionWithInvalidCurrency(String currency)
    {
        int amount = VALID_AMOUNT;
        Money money = new Money(amount, currency);
    }
}
