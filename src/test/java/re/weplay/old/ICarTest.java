package re.weplay.old;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import re.weplay.old.ICar;

public class ICarTest
{
    private ICar myFerrari = Mockito.mock(ICar.class);

    @Test
    public void testIfCarIsACar()
    {
        Assert.assertTrue("the passed car is not an instance of the passed type", myFerrari instanceof ICar);
    }

    @Test
    public void testTestDoubleDefaultBehavior()
    {
        Assert.assertFalse("a new 'test double' should return false a defaultboolean", myFerrari.needsFuel());

        Mockito.when(myFerrari.needsFuel()).thenReturn(true);
        Assert.assertTrue("a 'test double' whose boolean has been set to true should return true", myFerrari.needsFuel());

        Assert.assertEquals("a new 'test double' should return 0 as default double", 0, myFerrari.getEngineTemperature(), 0);

        Mockito.when(myFerrari.getEngineTemperature()).thenReturn(2.3);
        Assert.assertEquals("a test double supose to return 2.3 should return 2.3", 2.3, myFerrari.getEngineTemperature(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwRequiredException()
    {
        Mockito.when(myFerrari.getEngineTemperature()).thenThrow(IllegalArgumentException.class);
        myFerrari.getEngineTemperature();
    }
}
