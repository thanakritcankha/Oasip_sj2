package oasip.backend.repositories;

import oasip.backend.Enitities.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByEventCategory_Id(Integer categoryId, Sort sort);
    List<Event> findByEventStartTimeBefore(Date startTime , Sort sort);
    List<Event> findByEventStartTimeAfter(Date startTime , Sort sort);
    List<Event> findByEventStartTimeBetween(Date startTime , Date endTime , Sort sort);
//    List<Event> findByEventStartTime_Day(Sort sort);
}