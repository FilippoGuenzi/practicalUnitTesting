package re.weplay._03._08.test_fixtures;

import java.util.ArrayList;
import java.util.List;

public class Client
{
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address)
    {
        addresses.add(address);
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }
}
