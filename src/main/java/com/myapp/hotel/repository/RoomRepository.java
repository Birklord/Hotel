package com.myapp.hotel.repository;

import com.myapp.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    public Optional<Room> findById(Long id);

    @Query("select r from Room r where r.maximum <=:value order by r.roomTypeAmount")
    public List<Room> occupantCheck(int value);


}

