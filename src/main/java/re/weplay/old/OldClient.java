package re.weplay.old;

import java.util.ArrayList;
import java.util.List;

public class OldClient
{
    private List<Address> addresses = new ArrayList<Address>();

    public void addAddress(Address address)
    {
        this.addresses.add(address);
    }

    public OldClient(List<Address> pAddresses)
    {
        super();
        addresses = pAddresses;
    }

    public OldClient()
    {
        super();
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> pAddresses)
    {
        addresses = pAddresses;
    }

}
