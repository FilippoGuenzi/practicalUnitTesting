package re.weplay._04._10._03;

public class OverlapingBookingsException extends RuntimeException
{
    public OverlapingBookingsException()
    {
        super();
    }

    public OverlapingBookingsException(String message)
    {
        super(message);
    }

    public OverlapingBookingsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public OverlapingBookingsException(Throwable cause)
    {
        super(cause);
    }

    protected OverlapingBookingsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
