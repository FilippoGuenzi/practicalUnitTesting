package re.weplay._05._07._02.race_results_enhanced;

public class RaceNotAvailableException extends RuntimeException
{
    public RaceNotAvailableException()
    {
    }

    public RaceNotAvailableException(Throwable cause)
    {
        super(cause);
    }

    public RaceNotAvailableException(String message)
    {
        super(message);
    }

    public RaceNotAvailableException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RaceNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
