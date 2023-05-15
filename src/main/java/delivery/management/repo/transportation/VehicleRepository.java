package delivery.management.repo.transportation;


import delivery.management.model.entity.transportation.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select st from Vehicle st where st.uuid=:recordId")
    Optional<Vehicle> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Vehicle st where st.uuid=:recordId")
    Optional<Vehicle> deleteByUuid(@Param("recordId")UUID uuid);

    Optional<Vehicle> findVehicleByPlateNo(String plateNo);

//    @Query("SELECT p FROM Vehicle p WHERE " +
//            "p.name LIKE CONCAT('%',:query, '%')" )
//    List<Vehicle> searchVehicleByName(String query);

//    @Query("select st from Vehicle st where st.vehicleCategory.uuid=:recordId")
//    List<Vehicle> findByVehicleCategoryUuid(@Param("recordId") UUID vehicleCategoryUuid);



}
