package re.weplay._05._07._02.race_results_enhanced;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class RaceResultsServiceTest
{

    //a_client_who_never_subscribed_to_any_sport_shouldn_t_receive_any_notification()
//    @Test
//    public void a_client_who_never_subscribed_to_any_sport_shouldn_t_receive_any_notification()
//    {
//        //Arrange
//        Client client = mock(Client.class);
//        RaceToBeSubscribed horseRaces = Mockito.mock(RaceToBeSubscribed.class);
//        RaceToBeSubscribed formula1 = Mockito.mock(RaceToBeSubscribed.class);
//        RaceToBeSubscribed boatRaces = Mockito.mock(RaceToBeSubscribed.class);
//
//        RaceResultsService raceResultsService = new RaceResultsService();
//
//        //Act
//        raceResultsService.sendRaceMessages(horseRaces);
//        raceResultsService.sendRaceMessages(formula1);
//        raceResultsService.sendRaceMessages(boatRaces);
//
//        //Assert
//        Mockito.verify(client.receive())
//    }

    //all_subscribers_to_a_sport_should_receive_all_its_related_messages()
    @Test
    public void all_subscribers_to_a_sport_should_receive_any_new_incoming_message()
    {
        //Arrange
        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);
        RaceToBeSubscribed horseRace = mock(RaceToBeSubscribed.class);
        Message message1 = mock(Message.class);
        Message message2 = mock(Message.class);
        Message message3 = mock(Message.class);

        List<RaceToBeSubscribed> racesToBeSubscribed = new ArrayList<>();
        racesToBeSubscribed.add(horseRace);

        RaceResultsService raceResultsService = new RaceResultsService(racesToBeSubscribed);

        raceResultsService.feedMessageQueue(horseRace, message1);
        raceResultsService.feedMessageQueue(horseRace, message2);
        raceResultsService.feedMessageQueue(horseRace, message3);

        raceResultsService.addSubscribtion(client1, horseRace);
        raceResultsService.addSubscribtion(client2, horseRace);

        //Act
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(client1).receive(message1);
        verify(client1).receive(message2);
        verify(client1).receive(message3);

        verify(client2).receive(message1);
        verify(client2).receive(message2);
        verify(client2).receive(message3);
    }

    //a_client_who_unsubscribed_a_sport_feed_shouldn_t_receive_any_new_notification_from_it()
    @Test
    public void a_client_who_unsubscribed_a_sport_feed_shouldn_t_receive_any_new_notification_from_it()
    {
        //Arrange
        Client client = mock(Client.class);
        RaceToBeSubscribed raceToBeSubscribed = mock(RaceToBeSubscribed.class);
        List<RaceToBeSubscribed> racesToBeSubscribed = new ArrayList<>();

        //Act
        RaceResultsService raceResultsService = new RaceResultsService(racesToBeSubscribed);
        raceResultsService.addSubscribtion(client, raceToBeSubscribed);
        raceResultsService.removeSubscription(client, raceToBeSubscribed);
        raceResultsService.sendRaceMessages(raceToBeSubscribed);

        //Assert
        verify(client, never()).receive(&==&);
    }

    //a_new_subscription_to_a_sport_feed_issued_by_a_client_who_already_subscribed_to_it_should_be_ignored()
    //every_message_sent_by_the_RaceResultService_should_be_logged_with_its_date_and_text
    //an_unsubscription_from_a_client_who_already_unsubscribed_should_be_ignored

}
