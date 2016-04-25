package re.weplay._03._03.first;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MoneyTest
{

    private static final Object[] passParamsAndExpectedValues()
    {
        return new Object[] {
                new Object[] { 10, "USD", 10, "USD" },
                new Object[] { 15, "EUR", 15, "EUR" }
        };
    }

    @Test
    @Parameters(method = "passParamsAndExpectedValues")
    public void it_should_set_the_amount_and_the_currency_properly(
            int expectedAmount,
            String expectedCurrency,
            int amount,
            String currency
                                                                  )
    {
        //Arrange - Given

        //Act - When
        Money money = new Money(amount, currency);

        //Assert - Then
        assertEquals(expectedAmount, money.getAmount());
        assertEquals(expectedCurrency, money.getCurrency());
    }

    private static final Object[] passIllegalParams()
    {
        return new Object[] {
                new Object[] { -1, "EUR" },
                new Object[] { 1, "" },
                new Object[] { 1, null },
                new Object[] { -31, "" },
                new Object[] { -5, null }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "passIllegalParams")
    public void it_should_throw_an_exception_when_passed_an_illegal_argument(
            int amount, String currency
                                                                            )
    {
        //Arrange
        //Act
        Money money = new Money(amount, currency);
        //Assert
    }
}
