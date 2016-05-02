package re.weplay._05._07._02.race_results_enhanced;

public class MessageImplementation implements Message
{
    String message;

    public MessageImplementation(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String getDate()
    {
        return null;
    }

    @Override
    public String getText()
    {
        return null;
    }
}
