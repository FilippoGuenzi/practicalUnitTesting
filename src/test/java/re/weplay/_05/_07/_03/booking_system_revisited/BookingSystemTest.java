package re.weplay._05._07._03.booking_system_revisited;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import re.weplay._04._10._03.BookingSystem;
import re.weplay._04._10._03.OverlapingBookingsException;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest
{

    re.weplay._04._10._03.BookingSystem bookingSystem;

    @Before
    public void setUp()
    {
        bookingSystem = new BookingSystem();
    }

    private static final Object[] provideOutOf24HRangeBookingBoundaries()
    {
        return new Object[]
                {
                        new Object[] {-3, -8},
                        new Object[] {-1, 7},
                        new Object[] {7, -1},
                        new Object[] {-10, 25},
                        new Object[] {25, -10},
                        new Object[] {5, 27},
                        new Object[] {27, 5},
                        new Object[] {26, 32}
                };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "provideOutOf24HRangeBookingBoundaries")
    public void should_not_allow_starting_time_and_ending_times_out_of_24_hours_range(int startingTime, int endingTime)
    {
        //Arrange
        //Act
        bookingSystem.book(startingTime, endingTime);
        //Assert

    }

    private static final Object[] provideInvertedBookingRangeBoundaries()
    {
        return new Object[]
                {
                new Object[] {0,0},
                new Object[] {5,5},
                new Object[] {15,15},
                new Object[] {24,24},
                new Object[] {2,1} ,
                new Object[] {4,2},
                new Object[] {7,3},
                new Object[] {9,1},
                new Object[] {12,11},
                new Object[] {14,13},
                new Object[] {16,10},
                new Object[] {18,8},
                new Object[] {20,15},
                new Object[] {22,6},
                new Object[] {24,11}
                };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "provideInvertedBookingRangeBoundaries")
    public void should_not_allow_a_starting_time_gte_ending_time(int startingTime, int endingTime)
    {
        //Arrange
        //Act
        bookingSystem.book(startingTime, endingTime);
        //Assert
    }

    private static final Object[] provideValidBookingRanges()
    {
        return new Object[]
                {
                        new Object[] { 2 , 4 },
                        new Object[] { 5 , 7 },
                        new Object[] { 8 , 16 },
                        new Object[] { 10 , 11 },
                        new Object[] { 17 , 19 }
                };
    }

    @Test
    @Parameters(method = "provideValidBookingRanges")
    public void should_remember_first_booked_range(int startingTime, int endingTime)
    {
        //Arrange
        Map.Entry<Integer, Integer> bookingEntry = new AbstractMap.SimpleEntry<>(startingTime, endingTime);
        //Act
        bookingSystem.book(startingTime, endingTime);
        //Assert
        Assert.assertTrue("The bookingSystem should contain the booked entry " + "(" + startingTime + "," + endingTime + ")" + " but it doesn't", bookingSystem.getBookings().entrySet().contains(bookingEntry));
    }

    private static final Object[] provideSeriesOfValidBookings()
    {
        Map<Integer, Integer> rangesToBook1 = new HashMap<>();
        rangesToBook1.put(1, 5);
        rangesToBook1.put(7, 8);
        rangesToBook1.put(12, 15);
        rangesToBook1.put(21, 24);

        Map<Integer, Integer> rangesToBook2 = new HashMap<>();
        rangesToBook2.put(3,4);
        rangesToBook2.put(7,13);
        rangesToBook2.put(13,14);
        rangesToBook2.put(16,18);
        rangesToBook2.put(23,24);

        Map<Integer, Integer> rangesToBook3 = new HashMap<>();
        rangesToBook3.put(0, 7);
        rangesToBook3.put(7,8);
        rangesToBook3.put(13, 16);

        Map<Integer, Integer> rangesToBook4 = new HashMap<>();
        rangesToBook4.put(3 , 9);
        rangesToBook4.put(20, 22);
        rangesToBook4.put(13, 15);

        return new Object[]
                {
                        new Object[] {rangesToBook1},
                        new Object[] {rangesToBook2},
                        new Object[] {rangesToBook3},
                        new Object[] {rangesToBook4}
                };
    }

    @Test
    @Parameters(method = "provideSeriesOfValidBookings")
    public void should_remember_all_booked_ranges(Map<Integer, Integer> bookedRanges)
    {
        //Arrange
        //Act
        for(Map.Entry<Integer, Integer> bookedRangeEntry : bookedRanges.entrySet())
        {
            bookingSystem.book(bookedRangeEntry.getKey(), bookedRangeEntry.getValue());
        }
        //Assert
        for(Map.Entry<Integer, Integer> bookedRangeEntry : bookedRanges.entrySet())
        {
            Assert.assertTrue("The booked range " + "( " + bookedRangeEntry.getKey() + " , "+ bookedRangeEntry.getValue()  + " )" + " hasn't been booked", bookingSystem.getBookings().entrySet().contains(bookedRangeEntry));
        }
    }

    private static final Object[] provideOverlappingRanges()
    {
        return new Object[]
                {
                        new Object[] {1, 5, 3, 6},
                        new Object[] {1, 6, 2, 6},
                        new Object[] {1, 6, 2, 4},
                        new Object[] {1, 2, 1, 3},
                        new Object[] {1, 4, 1, 4},
                        new Object[] {1, 4, 1, 3},
                        new Object[] {2, 3, 1, 4},
                        new Object[] {2, 4, 1, 4},
                        new Object[] {2, 4, 1, 3}
                };
    }

    @Test(expected = OverlapingBookingsException.class)
    @Parameters(method = "provideOverlappingRanges")
    public void should_not_allow_overlapping_ranges(int startingTime1, int endingTime1, int startingTime2, int endingTime2)
    {
        //Arrange
        bookingSystem.book(startingTime1, endingTime1);
        //Act
        bookingSystem.book(startingTime2, endingTime2);
        //Assert
    }
}
