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
    AssignmentRepository assignmentRepository;

    @Autowired
    LectureSuggestionRepository lectureSuggestionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Submitted'"
    )
    public void wheneverSubmitted_AiBasedGrade(@Payload Submitted submitted) {
        Submitted event = submitted;
        System.out.println(
            "\n\n##### listener AiBasedGrade : " + submitted + "\n\n"
        );

        // Sample Logic //
        Assignment.aiBasedGrade(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserRegistered'"
    )
    public void wheneverUserRegistered_AiBasedSuggestLecture(
        @Payload UserRegistered userRegistered
    ) {
        UserRegistered event = userRegistered;
        System.out.println(
            "\n\n##### listener AiBasedSuggestLecture : " +
            userRegistered +
            "\n\n"
        );

        // Sample Logic //
        LectureSuggestion.aiBasedSuggestLecture(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
