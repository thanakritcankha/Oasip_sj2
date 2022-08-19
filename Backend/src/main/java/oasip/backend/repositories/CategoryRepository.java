package oasip.backend.repositories;

import oasip.backend.Enitities.Eventcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Eventcategory,Integer> {
    Eventcategory findByEventCategoryName(String name);
}