package re.weplay._04._10._03;

import java.util.HashMap;
import java.util.Map;

public class BookingSystem
{
    Map<Integer, Integer> bookings = new HashMap<>();

    public void book(int startingTime, int endingTime)
    {
        boolean addable = true;
        if (startingTime < 0 || startingTime > 24 || endingTime < 0 || endingTime > 24 || startingTime >= endingTime)
        {
            throw new IllegalArgumentException("One of the provided argument is lower than 0 (hours) or greater than 24 (hours)");
        }
        for (Map.Entry<Integer, Integer> bookingEntry : bookings.entrySet())
        {
            if(endingTimeIsInsideAPreviousBooking(endingTime, bookingEntry) || startingTimeIsInsideAPreviousBooking(startingTime, bookingEntry) || newBookingIncludesAPreviousBooking(startingTime, endingTime, bookingEntry))
            {
                addable = false;
            }
        }
        if(addable == false)
        {
            throw new OverlapingBookingsException("Your new booking is ranging on a previous booking");
        }
        else
        {
            bookings.put(startingTime, endingTime);
        }
    }

    private boolean newBookingIncludesAPreviousBooking(int startingTime, int endingTime, Map.Entry<Integer, Integer> bookingEntry)
    {
        return startingTime <= bookingEntry.getKey() &&  endingTime >= bookingEntry.getValue();
    }

    private boolean startingTimeIsInsideAPreviousBooking(int startingTime, Map.Entry<Integer, Integer> bookingEntry)
    {
        return startingTime >= bookingEntry.getKey() && startingTime < bookingEntry.getValue();
    }

    private boolean endingTimeIsInsideAPreviousBooking(int endingTime, Map.Entry<Integer, Integer> bookingEntry)
    {
        return endingTime <= bookingEntry.getValue() && endingTime > bookingEntry.getKey();
    }

    public Map<Integer, Integer> getBookings()
    {
        return bookings;
    }
}
