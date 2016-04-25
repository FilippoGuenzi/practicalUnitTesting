package re.weplay._04._05.test_first_ex;

public class FootballTeam implements Comparable<FootballTeam>
{

    private int numOfVictories;

    public FootballTeam(int numberOfVictories)
    {
        if (numberOfVictories < 0)
        {
            throw new IllegalArgumentException(
                    "the number of victories passed is < 0 and that's not allowed");
        }
        this.numOfVictories = numberOfVictories;
    }

    public int getNumOfVictories()
    {
        return numOfVictories;
    }

    @Override
    public int compareTo(FootballTeam footballTeam)
    {
        return this.getNumOfVictories() - footballTeam.getNumOfVictories();
    }
}
