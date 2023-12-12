package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceImplTest {

    @InjectMocks
    EventServicesImpl eventService;

    @Mock
    EventRepository eventRepository;

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    LogisticsRepository logisticsRepository;

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();

        when(participantRepository.save(participant)).thenReturn(participant);

        Participant savedParticipant = eventService.addParticipant(participant);

        assertEquals(participant, savedParticipant);
    }

    @Test
    void testAddAffectEvenParticipantById() {
        Event event = new Event();
        int idParticipant = 1;

        Participant participant = new Participant();
        participant.setIdPart(idParticipant);

        when(participantRepository.findById(idParticipant)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        Event savedEvent = eventService.addAffectEvenParticipant(event, idParticipant);

        assertEquals(event, savedEvent);
        assertEquals(1, participant.getEvents().size()); // Assuming the participant is updated with the event
    }

    @Test
    void testAddAffectEvenParticipantByEvent() {
        Event event = new Event();

        Participant participant1 = new Participant();
        participant1.setIdPart(1);

        Participant participant2 = new Participant();
        participant2.setIdPart(2);

        Set<Participant> participants = new HashSet<>(Arrays.asList(participant1, participant2));
        event.setParticipants(participants);

        when(participantRepository.findById(1)).thenReturn(Optional.of(participant1));
        when(participantRepository.findById(2)).thenReturn(Optional.of(participant2));
        when(eventRepository.save(event)).thenReturn(event);

        Event savedEvent = eventService.addAffectEvenParticipant(event);

        assertEquals(event, savedEvent);
        assertEquals(1, participant1.getEvents().size()); // Assuming the participant is updated with the event
        assertEquals(1, participant2.getEvents().size()); // Assuming the participant is updated with the event
    }

}
