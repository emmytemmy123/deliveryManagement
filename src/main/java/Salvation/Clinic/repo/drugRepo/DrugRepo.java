package Salvation.Clinic.repo.drugRepo;


import Salvation.Clinic.model.entity.drug.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrugRepo extends JpaRepository<Drugs, Long> {

    @Query("select st from Drugs st where st.uuid=:uuid")
    Optional<Drugs> findByUuid (@Param("uuid")UUID uuid);

    @Query("delete from Drugs st where st.uuid=:uuid")
    Optional<Drugs> deleteByUuid (@Param("uuid") UUID uuid);

    @Query("select st from Drugs st where st.name=:name ")
    Optional<Drugs> findByName (@Param("name") String name);




}
