package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.DailyVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyVisitRepository extends JpaRepository<DailyVisit,Long> {
    Optional<DailyVisit> findByVisitDate(LocalDate today);

    List<DailyVisit> findByVisitDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(v.visitorCount) FROM DailyVisit v WHERE YEAR(v.visitDate) = :year AND MONTH(v.visitDate) = :month")
    Integer getTotalVisitsByMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(v.visitorCount) FROM DailyVisit v WHERE YEAR(v.visitDate) = :year")
    Integer getTotalVisitsByYear(@Param("year") int year);
}
