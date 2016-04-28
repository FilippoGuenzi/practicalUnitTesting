package re.weplay._05._07._02.race_results_enhanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RaceResultsService
{
    Map<RaceToBeSubscribed, List<Client>> subscriptions = new HashMap<>();
    Map<RaceToBeSubscribed, Queue<Message>> messagesToBeSent = new HashMap<>();

    public RaceResultsService(List<RaceToBeSubscribed> racesToBeSubscribed)
    {
        for (RaceToBeSubscribed raceToBeSubscribed : racesToBeSubscribed)
        {
            subscriptions.put(raceToBeSubscribed, new ArrayList<>());
            messagesToBeSent.put(raceToBeSubscribed, new LinkedList<>());
        }
    }

    public void sendRaceMessages(RaceToBeSubscribed raceToBeSubscribed)
    {
        while(!messagesToBeSent.get(raceToBeSubscribed).isEmpty())
        {
            Message firstMessageInQueue = messagesToBeSent.get(raceToBeSubscribed).poll();
            for (Client client : subscriptions.get(raceToBeSubscribed))
            {
                client.receive(firstMessageInQueue);
            }
        }
    }

    public void addSubscribtion(Client client, RaceToBeSubscribed raceToBeSubscribed)
    {
        subscriptions.get(raceToBeSubscribed).add(client);
    }

    public void feedMessageQueue(RaceToBeSubscribed raceToBeSubscribed, Message message)
    {
        messagesToBeSent.get(raceToBeSubscribed).add(message);
    }
}
