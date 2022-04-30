package oasip.backend.repositories;

import oasip.backend.Enitities.Eventcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventcategoryRepository extends JpaRepository<Eventcategory ,Integer> {
}