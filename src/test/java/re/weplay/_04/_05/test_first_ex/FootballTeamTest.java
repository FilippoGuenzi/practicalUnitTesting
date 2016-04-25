package re.weplay._04._05.test_first_ex;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest
{

    public static final int ANY_NUMBER = 5;

    private static final Object[] numberOfVictories()
    {
        return new Object[] {
                new Object[] { 3, 3 },
                new Object[] { 5, 5 },
                new Object[] { 9, 9 },
                new Object[] { 20, 20 },
                new Object[] { 2, 2 }
        };
    }

    @Parameters(method = "numberOfVictories")
    @Test
    public void constructor_should_set_the_right_number_of_games_won(
            int expectedNumOfVictories, int passedNumOfVictories
                                                                    )
    {
        //Arrange
        //Act
        FootballTeam footballTeam = new FootballTeam(passedNumOfVictories);
        //Assert
        assertEquals("\n"
                     + "On s'attendait à "
                     + expectedNumOfVictories
                     + " victoires et au lieu on en a "
                     + footballTeam.getNumOfVictories(),
                     expectedNumOfVictories,
                     footballTeam.getNumOfVictories()
                    );
    }

    private static final Object[] illegalNumberOfVictories()
    {
        return new Object[] {
                new Object[] { -3, -3 },
                new Object[] { -5, -5 },
                new Object[] { -9, -9 },
                new Object[] { -20, -20 },
                new Object[] { -2, -2 }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "illegalNumberOfVictories")
    public void constructor_should_throw_an_exception_if_illegal_argument(
            int expectedNumOfVictories, int passedNumOfVictories
                                                                         )
    {
        //Arrange
        //Act
        FootballTeam footballTeam = new FootballTeam(passedNumOfVictories);
        //Assert
    }

    @Test
    public void footballTeam_should_be_comparable()
    {
        //Arrange
        //Act
        FootballTeam footballTeam = new FootballTeam(ANY_NUMBER);
        //Assert
        Assert.assertTrue(
                "FootballTeam is supposed to be comparable but it's not",
                footballTeam instanceof Comparable
                         );

    }

    private static final Object[] paramsToCompareBiggerWinner()
    {
        return new Object[] {
                new Object[] { 1, 7, 5 },
                new Object[] { 1, 13, 9 },
                new Object[] { 1, 23, 4 },
                new Object[] { 1, 12, 11 },
                new Object[] { -1, 5, 8 },
                new Object[] { -1, 3, 6 },
                new Object[] { -1, 30, 31 },
                new Object[] { -1, 0, 1 },
                new Object[] { 0, 6, 6 },
                new Object[] { 0, 3, 3 },
                new Object[] { 0, 7, 7 },
                new Object[] { 0, 14, 14 }
        };
    }

    @Test
    @Parameters(method = "paramsToCompareBiggerWinner")
    public void compareTo_method_of_footballTeam_should_work_appropriately(
            int expectedComparisonSignum,
            int passedNumberOfVictories1,
            int passedNumberOfVictories2
                                                                          )
    {
        //Arrange
        //Act
        FootballTeam footballTeam1 = new FootballTeam(passedNumberOfVictories1);
        FootballTeam footballTeam2 = new FootballTeam(passedNumberOfVictories2);
        //Assert
        assertEquals("Expected comparison signum was "
                     + expectedComparisonSignum
                     + " but is "
                     + Math.signum(footballTeam1.compareTo(footballTeam2)),
                     expectedComparisonSignum,
                     Math.signum(footballTeam1.compareTo(footballTeam2)),
                     0.000000000000000000000001
                    );
    }
}
