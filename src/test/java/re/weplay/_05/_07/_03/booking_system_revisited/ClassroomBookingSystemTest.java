package re.weplay._05._07._03.booking_system_revisited;

import org.junit.Test;

public class ClassroomBookingSystemTest
{
    //should_remember_first_booked_range
    @Test public void should()
    {
        //Given
        ClassroomBookingSystem classroomBookingSystem = new ClassroomBookingSystemImpl();
        //When
        classroomBookingSystem.bookClassroom("A1", 8);
        //Then

    }
    //should_remember_all_booked_ranges
    //should_prevent_overlapping_bookings
    //should_return_all_existing_classrooms
    //should_return_all_available_classrooms_at_a_given_day_and_hourly_time_range
    //should_respect_given_constraints
    //should_not_allow_starting_time_and_ending_times_out_of_24_hours_range


}
