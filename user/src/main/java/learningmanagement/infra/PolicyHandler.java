package learningmanagement.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import learningmanagement.config.kafka.KafkaProcessor;
import learningmanagement.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='LectureSuggested'"
    )
    public void wheneverLectureSuggested_SuggestLecture(
        @Payload LectureSuggested lectureSuggested
    ) {
        LectureSuggested event = lectureSuggested;
        System.out.println(
            "\n\n##### listener SuggestLecture : " + lectureSuggested + "\n\n"
        );

        // Sample Logic //
        User.suggestLecture(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
