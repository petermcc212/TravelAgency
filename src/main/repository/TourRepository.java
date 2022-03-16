package main.repository;

import main.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository <Tour, Integer> {

    @Query("from Tour t left join fetch t.comments where t.id = :id")
    public Tour getByIdWithComments(@Param("id") int id);

}
