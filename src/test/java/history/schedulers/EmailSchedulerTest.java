package history.schedulers;


import HistoryAppGradleSecurity.schedulers.EmailScheduler;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailSchedulerTest.class)
public class EmailSchedulerTest {
    @Mock
    private EmailScheduler emailScheduler;

    @Test
    public void  testScheduledSubscriptionEmail(){
//        Mockito.verify(emailScheduler,Mockito.timeout(60000)
//                .atLeastOnce()).sendSubscriptionEmails();

        emailScheduler.sendSubscriptionEmails();

        verify(emailScheduler).sendSubscriptionEmails();

    }    @Test
    public void  testScheduledEmail(){
//        Mockito.verify(emailScheduler,Mockito.timeout(60000)
//                .atLeastOnce()).sendSubscriptionEmails();

        emailScheduler.sendScheduledEmails();

        verify(emailScheduler).sendScheduledEmails();

    }
}
