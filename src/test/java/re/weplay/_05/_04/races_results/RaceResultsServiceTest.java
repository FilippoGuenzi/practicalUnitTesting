package re.weplay._05._04.races_results;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class RaceResultsServiceTest
{
    RaceResultsService raceResultsService;
    Message message;
    Client client1;
    Client client2;

    @Before
    public void setUp()
    {
        raceResultsService = new RaceResultsService();
        message = mock(Message.class);
        client1 = mock(Client.class, "client 1");
        client2 = mock(Client.class, "client 2");
    }

    @Test
    public void a_client_who_never_subscribed_shouldn_t_receive_any_notification()
    {
        //Arrange

        //Act
        raceResultsService.sendMessage(message);

        //Assert
        verify(client1, never()).receive(message);
        verify(client1, never()).receive(message);

    }

    @Test
    public void a_client_who_subscribed_should_receive_each_new_notification_once()
    {
        //Arrange

        //Act
        raceResultsService.addSubscriber(client1);
        raceResultsService.sendMessage(message);

        //Assert
        verify(client1).receive(message);
    }

    @Test
    public void all_subscribers_should_receive_any_new_incoming_message()
    {
        //Arrange

        //Act
        raceResultsService.addSubscriber(client1);
        raceResultsService.addSubscriber(client2);
        raceResultsService.sendMessage(message);

        //Assert
        verify(client1).receive(message);
        verify(client2).receive(message);
    }

    @Test
    public void a_client_who_unsubscribed_shouldn_t_receive_any_new_notification()
    {
        //Arrange

        //Act
        raceResultsService.addSubscriber(client1);
        raceResultsService.removeSubscriber(client1);
        raceResultsService.sendMessage(message);

        //Assert
        verify(client1, never()).receive(message);
    }

    @Test
    public void a_new_subscription_issued_by_a_client_who_already_subscribed_should_be_ignored()
    {
        //Arrange

        //Act
        raceResultsService.addSubscriber(client1);
        raceResultsService.addSubscriber(client1);
        raceResultsService.sendMessage(message);

        //Assert
        verify(client1).receive(message);
    }
}
