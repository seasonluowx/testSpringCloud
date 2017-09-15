import com.season.streamrabbit.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class testSend {

    @Qualifier("input")
    @Autowired
    private MessageChannel input;

    @Test
    public void send(){
        input.send(MessageBuilder.withPayload("from two input channel").build());
    }
}
