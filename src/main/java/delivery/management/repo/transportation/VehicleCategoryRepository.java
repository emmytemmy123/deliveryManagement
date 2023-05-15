package delivery.management.repo.transportation;


import delivery.management.model.entity.transportation.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, Long> {

    @Query("select st from VehicleCategory st where st.uuid=:recordId")
    Optional<VehicleCategory> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from VehicleCategory st where st.uuid=:recordId")
    Optional<VehicleCategory> deleteByUuid(@Param("recordId")UUID uuid);

    Optional<VehicleCategory> findByName(String name);

    @Query("SELECT p FROM VehicleCategory p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')")
    List<VehicleCategory> searchVehicleCategoryByName(String query);


}
