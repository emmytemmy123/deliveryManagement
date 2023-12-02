package Salvation.Clinic.repo.roomRepo;


import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.entity.room.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomCategoryRepo extends JpaRepository<RoomCategory, Long> {

    @Query("select st from RoomCategory st where st.uuid=:uuid")
    Optional<RoomCategory> findByUuid (@Param("uuid") UUID uuid);

    @Query("delete from RoomCategory st where st.uuid=:uuid")
    Optional<RoomCategory> deleteByUuid (@Param("uuid") UUID uuid);

    @Query("select st from RoomCategory st where st.name=:name")
    Optional<RoomCategory> findByRoomName (@Param("name") String name);


}
