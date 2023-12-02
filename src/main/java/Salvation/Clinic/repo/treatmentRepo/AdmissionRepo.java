package Salvation.Clinic.repo.treatmentRepo;

import Salvation.Clinic.model.entity.treatment.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long> {

    @Query("select st from Admission st where st.uuid=:uuid")
    Optional<Admission> findByUuid (@Param("uuid") UUID uuid);

    @Query("delete from Admission st where st.uuid=:uuid")
    Optional<Admission> deleteByUuid (@Param("uuid")UUID uuid);




}
