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
    RaceMessageLogger raceMessageLogger;
    Message doubleUnsubscriptionMessage ;

    public RaceResultsService(List<RaceToBeSubscribed> racesToBeSubscribed, RaceMessageLogger raceMessageLogger)
    {
        for (RaceToBeSubscribed raceToBeSubscribed : racesToBeSubscribed)
        {
            subscriptions.put(raceToBeSubscribed, new ArrayList<>());
            messagesToBeSent.put(raceToBeSubscribed, new LinkedList<>());
        }

        this.raceMessageLogger = raceMessageLogger;
    }

    public void sendRaceMessages(RaceToBeSubscribed raceToBeSubscribed)
    {
        while (!messagesToBeSent.get(raceToBeSubscribed).isEmpty())
        {
            Message firstMessageInQueue = messagesToBeSent.get(raceToBeSubscribed).poll();
            for (Client client : subscriptions.get(raceToBeSubscribed))
            {
                client.receive(firstMessageInQueue);
                raceMessageLogger.log(firstMessageInQueue.getDate() + " : " + firstMessageInQueue.getText());
            }
        }
    }

    public void addSubscribtion(Client client, RaceToBeSubscribed raceToBeSubscribed)
    {
        if (!subscriptions.containsKey(raceToBeSubscribed))
        {
            throw new RaceNotAvailableException(raceToBeSubscribed + "is not available in raceResultService");
        }
        if (!subscriptions.get(raceToBeSubscribed).contains(client))
        {
            subscriptions.get(raceToBeSubscribed).add(client);
        }
    }

    public void feedMessageQueue(RaceToBeSubscribed raceToBeSubscribed, Message message)
    {
        messagesToBeSent.get(raceToBeSubscribed).add(message);
    }

    public void removeSubscription(Client client, RaceToBeSubscribed raceToBeSubscribed)
    {
        if (!subscriptions.get(raceToBeSubscribed).contains(client))
        {
            int subscribedRacesCount = 0;
            String subscribedRacesList = "";
            for (Map.Entry<RaceToBeSubscribed, List<Client>> raceEntry : subscriptions.entrySet())
            {
                if (raceEntry.getValue().contains(client))
                {
                    subscribedRacesList += (subscribedRacesCount == 0 ? " " : ", ") + raceEntry.getKey().getTitle();
                    subscribedRacesCount++;
                }
            }
            String doubleUnsubscriptionMessageValue = "You already unsubscribed to " + raceToBeSubscribed.getTitle() + " notification. Didn't you mean to unsubscribe from one of those races you're still subscribed to : " + subscribedRacesList +" ?";
            doubleUnsubscriptionMessage = new MessageImplementation(doubleUnsubscriptionMessageValue);
            client.receive(doubleUnsubscriptionMessage);
        }
        subscriptions.get(raceToBeSubscribed).remove(client);
    }

    public Message getDoubleUnsubscriptionMessage()
    {
        return doubleUnsubscriptionMessage;
    }
}
