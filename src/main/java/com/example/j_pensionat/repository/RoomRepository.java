package com.example.j_pensionat.repository;

import com.example.j_pensionat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select o.room.id from Order o where :startDate <= o.endDate and :endDate >= o.startDate")
    List<Long> findUnavailableRoomsWithinRange(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query("select r from Room r where r.maxGuests >= :guests and r.id not in (select o.room.id from Order o where :startDate <= o.endDate and :endDate >= o.startDate)")
    List<Room> filteredByDateAndGuests(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("guests") int guests );
}
