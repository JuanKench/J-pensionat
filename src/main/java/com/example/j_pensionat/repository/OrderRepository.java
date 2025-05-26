package com.example.j_pensionat.repository;

import com.example.j_pensionat.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCancelledFalse();

    List<Order> findByCustomerId(Long customerId);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Order o " +
            "WHERE o.room.id = :roomId " +
            "AND o.startDate < :endDate " +
            "AND o.endDate > :startDate")
    boolean existsOverlappingBooking(@Param("roomId") Long roomId,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}
