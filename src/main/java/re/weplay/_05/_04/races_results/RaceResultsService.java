package re.weplay._05._04.races_results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService
{
    private Collection<Client> clients = new HashSet<>();

    public void addSubscriber(Client client)
    {
        clients.add(client);
    }

    public void sendMessage(Message message)
    {
        for (Client client : clients)
        {
            client.receive(message);
        }
    }

    public void removeSubscriber(Client client)
    {
        clients.remove(client);
    }
}
