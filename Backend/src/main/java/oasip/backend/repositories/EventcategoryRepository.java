package oasip.backend.repositories;

import oasip.backend.Enitities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventcategoryRepository extends JpaRepository<EventCategory, Integer> {
}