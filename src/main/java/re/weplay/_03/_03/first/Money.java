package re.weplay._03._03.first;

public class Money
{
    private final int amount;
    private final String currency;

    public Money(int amount, String currency)
    {
        if (amount < 0 || currency == null || currency.isEmpty())
        {
            throw new IllegalArgumentException(
                    "amount < 0 || currency == null || currency.isEmpty(). Those arguments are illegal.");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getCurrency()
    {
        return currency;
    }
}
