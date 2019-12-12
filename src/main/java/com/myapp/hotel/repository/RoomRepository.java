package com.myapp.hotel.repository;

import com.myapp.hotel.model.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    public Optional<Room> findById(Long id);

    @Query("select r from Room r where r.maximum in (2) order by r.roomTypeAmount")
    public List<Room> occupantCheck(Pageable pageable);

    @Query("select r from Room r where r.maximum = 2 or (r.maximum <=:value) ")
    public List<Room> occupantAnalyzer(int value, Pageable pageable);

}

