package re.weplay.old;

public class Money
{
    private final int amount;
    private final String currency;

    public Money(int amount, String currency)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("illegal amount (< 0) : " + amount);
        }
        if (currency == null || currency.isEmpty())
        {
            throw new IllegalArgumentException("currency is whether empty or null");
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

    public boolean equals(Object anObject)
    {
        if (anObject instanceof Money)
        {
            Money money = (Money) anObject;
            return money.getCurrency().equals(getCurrency()) && getAmount() == money.getAmount();
        }
        return false;
    }
}
