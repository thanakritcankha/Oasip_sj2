package oasip.backend.repositories;

import oasip.backend.Enitities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    
}