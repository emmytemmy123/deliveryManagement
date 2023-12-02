package Salvation.Clinic.repo.roomRepo;


import Salvation.Clinic.model.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query("select st from Room st where st.uuid=:uuid")
    Optional<Room> findByUuid (@Param("uuid")UUID uuid);

    @Query("delete from Room st where st.uuid=:uuid")
    Optional<Room> deleteByUuid (@Param("uuid") UUID uuid);

//    @Query("select st from Room st where st.bedNo=:bedNo")
//    Optional<Room> findByBedNo (@Param("bedNo") String bedNo);

    @Query("select st from Room st where st.roomNo=:roomNo")
    Optional<Room> findByRoomNo (@Param("roomNo") String roomNo);




}
