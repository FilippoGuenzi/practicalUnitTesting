package re.weplay.old;

import org.junit.Test;
import org.mockito.Mockito;
import re.weplay.old.Client;
import re.weplay.old.MailServer;
import re.weplay.old.Messenger;
import re.weplay.old.Template;
import re.weplay.old.TemplateEngine;

public class MessengerTest
{
    private final String MSG = "Hello coco !";
    private final String EMAIL = "a@b.com";

    @Test
    public void sendMessageTest()
    {
        Client client = Mockito.mock(Client.class);
        Template template = Mockito.mock(Template.class);
        TemplateEngine templateEngine = Mockito.mock(TemplateEngine.class);
        MailServer mailServer = Mockito.mock(MailServer.class);

        Messenger sut = new Messenger(mailServer, templateEngine);

        Mockito.when(templateEngine.prepareMessage(template, client)).thenReturn(MSG);
        Mockito.when(client.getEmail()).thenReturn(EMAIL);

        sut.sendMessage(client, template);
        
        Mockito.verify(mailServer).send(EMAIL, MSG);
        
        
    }
}
