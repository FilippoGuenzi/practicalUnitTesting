package re.weplay.old;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import re.weplay.old.Address;
import re.weplay.old.OldClient;

public class OldClientTest
{
    Address addressA = new Address("A");
    Address addressB = new Address("B");
    OldClient client;
    
    

    @Before
    public void beforeEveryTest()
    {
        client = new OldClient();
    }

    @Test
    public void newClientShouldntHaveAnyAddressTest()
    {
        Assert.assertNotNull(client.getAddresses());
        Assert.assertEquals(0, client.getAddresses().size());
    }

    @Test
    public void clientsShouldBeAbleToStoreAddressesTest()
    {
        client.addAddress(addressA);

        Assert.assertNotNull(client.getAddresses());
        Assert.assertEquals(1, client.getAddresses().size());
        Assert.assertTrue(client.getAddresses().contains(addressA));
    }

    @Test
    public void clientShouldBeAbleToStoreManyAddressesTest()
    {
        client.addAddress(addressA);
        client.addAddress(addressB);

        Assert.assertNotNull(client.getAddresses());
        Assert.assertEquals(2, client.getAddresses().size());
        Assert.assertTrue(client.getAddresses().contains(addressA));
        Assert.assertTrue(client.getAddresses().contains(addressB));
    }
}
