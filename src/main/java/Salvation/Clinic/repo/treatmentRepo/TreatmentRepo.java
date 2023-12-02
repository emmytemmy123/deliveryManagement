package Salvation.Clinic.repo.treatmentRepo;

import Salvation.Clinic.model.entity.treatment.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TreatmentRepo extends JpaRepository<Treatment, Long> {

    @Query("select st from Treatment st where st.uuid=:uuid")
    Optional<Treatment> findByUuid (@Param("uuid") UUID uuid);

    @Query("delete from Treatment st where st.uuid=:uuid")
    Optional<Treatment> deleteByUuid (@Param("uuid")UUID uuid);


}
