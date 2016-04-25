package re.weplay._03._08.test_fixtures;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest01
{
    //in JUnit instance variables are created before each test method execution
    Address address1 = new Address("3 rue des capucines, ABER42 Ropin");
    Address address2 = new Address("54 faubourg des prés, ZSXR65 Furboas");

    @Test
    public void it_should_have_no_address_before_getting_one()
    {
        //Arrange
        Client client = new Client();
        //Act
        //Assert
        assertEquals(0, client.getAddresses().size());
    }

    @Test
    public void it_should_save_one_address_and_keep_it_properly()
    {
        //Arrange
        Client client = new Client();
        //Act
        client.addAddress(address1);
        //Assert
        assertEquals(1, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(address1));
    }

    @Test
    public void it_should_save_several_addresses_and_keep_them_properly()
    {
        //Arrange
        Client client = new Client();
        //Act
        client.addAddress(address1);
        client.addAddress(address2);
        //Assert
        assertEquals(2, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(address1));
        assertTrue(client.getAddresses().contains(address2));
    }
}
