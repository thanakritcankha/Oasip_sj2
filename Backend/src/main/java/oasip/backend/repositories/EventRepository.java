package oasip.backend.repositories;

import oasip.backend.Enitities.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByEventCategory_Id(Integer categoryId, Sort sort);
}