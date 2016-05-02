package re.weplay._05._07._02.race_results_enhanced;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RaceResultsServiceTest
{

    private Client client1;
    private Client client2;

    private RaceToBeSubscribed f1Race;
    private RaceToBeSubscribed horseRace;
    private RaceToBeSubscribed boatRace;
    private List<RaceToBeSubscribed> racesToBeSubscribed;

    private RaceResultsService raceResultsService;

    private Message message1;
    private Message message2;
    private Message message3;
    private RaceMessageLogger logger = mock(RaceMessageLogger.class);

    @Before public void setUp()
    {
        client1 = mock(Client.class);
        client2 = mock(Client.class);

        f1Race = mock(RaceToBeSubscribed.class);
        when(f1Race.getTitle()).thenReturn("f1 races");
        horseRace = mock(RaceToBeSubscribed.class);
        when(horseRace.getTitle()).thenReturn("horse races");
        boatRace = mock(RaceToBeSubscribed.class);
        when(boatRace.getTitle()).thenReturn("boat races");
        racesToBeSubscribed = new ArrayList<>();

        message1 = mock(Message.class);
        message2 = mock(Message.class);
        message3 = mock(Message.class);
    }

    @Test public void a_client_who_unsubscribed_a_sport_feed_shouldn_t_receive_any_new_notification_from_it()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.removeSubscription(client1, f1Race);

        raceResultsService.feedMessageQueue(f1Race, message1);
        raceResultsService.feedMessageQueue(f1Race, message2);
        raceResultsService.feedMessageQueue(f1Race, message3);

        //Act
        raceResultsService.sendRaceMessages(f1Race);

        //Assert
        verify(client1, never()).receive(message1);
        verify(client1, never()).receive(message2);
        verify(client1, never()).receive(message3);
    }

    @Test public void a_client_who_subscribed_to_two_sport_and_unsubscribed_one_should_still_receive_the_messages_from_the_one_he_didn_t_unsubscribed()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        racesToBeSubscribed.add(horseRace);

        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);
        raceResultsService.addSubscribtion(client1, horseRace);
        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.removeSubscription(client1, horseRace);

        raceResultsService.feedMessageQueue(f1Race, message1);
        raceResultsService.feedMessageQueue(f1Race, message2);
        raceResultsService.feedMessageQueue(horseRace, message3);

        //Act
        raceResultsService.sendRaceMessages(f1Race);
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(client1).receive(message1);
        verify(client1).receive(message2);
        verify(client1, never()).receive(message3);
    }

    @Test public void all_subscribers_to_a_sport_should_receive_any_new_incoming_message()
    {
        //Arrange
        racesToBeSubscribed.add(horseRace);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

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

    @Test public void a_client_who_subscribed_to_many_races_should_receive_messages_from_all_those_races()
    {
        //Arrange
        racesToBeSubscribed.add(horseRace);
        racesToBeSubscribed.add(f1Race);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);
        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.addSubscribtion(client1, horseRace);

        raceResultsService.feedMessageQueue(f1Race, message1);
        raceResultsService.feedMessageQueue(horseRace, message2);
        raceResultsService.feedMessageQueue(horseRace, message3);

        //Act
        raceResultsService.sendRaceMessages(f1Race);
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(client1).receive(message1);
        verify(client1).receive(message2);
        verify(client1).receive(message3);
    }

    @Test public void a_client_who_never_subscribed_to_any_sport_shouldn_t_receive_any_notification()
    {
        //Arrange
        racesToBeSubscribed.add(horseRace);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        raceResultsService.feedMessageQueue(horseRace, message1);
        raceResultsService.feedMessageQueue(horseRace, message2);
        raceResultsService.feedMessageQueue(horseRace, message3);

        //Act
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(client1, never()).receive(message1);
        verify(client1, never()).receive(message2);
        verify(client1, never()).receive(message3);

    }

    @Test public void a_new_subscription_to_a_sport_feed_issued_by_a_client_who_already_subscribed_to_it_should_be_ignored()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        racesToBeSubscribed.add(horseRace);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.addSubscribtion(client2, horseRace);
        raceResultsService.addSubscribtion(client2, horseRace);

        raceResultsService.feedMessageQueue(f1Race, message1);
        raceResultsService.feedMessageQueue(f1Race, message2);
        raceResultsService.feedMessageQueue(horseRace, message3);

        //Act
        raceResultsService.sendRaceMessages(f1Race);
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(client1, times(1)).receive(message1);
        verify(client1, times(1)).receive(message2);
        verify(client2, times(1)).receive(message3);
    }

    @Test(expected = RaceNotAvailableException.class) public void a_subscription_to_a_race_not_listed_in_possible_subscription_should_throw_an_exception()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        //Act
        raceResultsService.addSubscribtion(client1, horseRace);

        //Assert

    }

    @Test public void every_message_sent_should_be_logged()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        racesToBeSubscribed.add(horseRace);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        raceResultsService.feedMessageQueue(f1Race, message1);
        raceResultsService.feedMessageQueue(f1Race, message2);
        raceResultsService.feedMessageQueue(horseRace, message1);
        raceResultsService.feedMessageQueue(horseRace, message3);

        when(message1.getDate()).thenReturn("10/12/1970");
        when(message2.getDate()).thenReturn("01/07/1985");
        when(message3.getDate()).thenReturn("08/05/2003");

        when(message1.getText()).thenReturn("message1");
        when(message2.getText()).thenReturn("message2");
        when(message3.getText()).thenReturn("message3");

        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.addSubscribtion(client1, horseRace);
        raceResultsService.addSubscribtion(client2, horseRace);

        //Act
        raceResultsService.sendRaceMessages(f1Race);
        raceResultsService.sendRaceMessages(horseRace);

        //Assert
        verify(logger, times(3)).log(message1.getDate() + " : " + message1.getText());
        verify(logger, times(1)).log(message2.getDate() + " : " + message2.getText());
        verify(logger, times(2)).log(message3.getDate() + " : " + message3.getText());
    }

    @Test
    // The problem is that when it's run with all the other tests it succeeds because the order of subscribed races between actual and expected is the same.
    // While when run alone it fails because the order gets inverted.
    // Whe should do some test which would be order indifferent
    public void when_a_client_who_already_unsubscribed_to_a_race_unsubscribes_again_he_should_receive_a_message_of_all_its_remaining_active_feeds()
    {
        //Arrange
        racesToBeSubscribed.add(f1Race);
        racesToBeSubscribed.add(horseRace);
        racesToBeSubscribed.add(boatRace);
        raceResultsService = new RaceResultsService(racesToBeSubscribed, logger);

        raceResultsService.addSubscribtion(client1, f1Race);
        raceResultsService.addSubscribtion(client1, horseRace);
        raceResultsService.addSubscribtion(client1, boatRace);

        raceResultsService.removeSubscription(client1, f1Race);

        String doubleUnsubscriptionMessage = "You already unsubscribed to f1 races notification. Didn't you mean to unsubscribe from one of those races you're still subscribed to : horse races, boat races ?";

        //Act
        raceResultsService.removeSubscription(client1, f1Race);

        //Assert
        String actualMessageTitle = raceResultsService.getDoubleUnsubscriptionMessage().getMessage();
        String expectedMessageTitle = doubleUnsubscriptionMessage;
        String assertionErrorMessage = "You should have " + expectedMessageTitle + " but had " + actualMessageTitle;
        Assert.assertEquals(assertionErrorMessage, expectedMessageTitle, actualMessageTitle);

        verify(client1).receive(any(Message.class));
        //comment vérifier que le message reçu par le client contient effectivement le même message. Je le verrai probablement avec les Mockito.matchers
    }
}
