package delivery.management.repo.user;

import delivery.management.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  /*  @Query("select st from Client st where st.email=:email and st.password=:password")
    Optional<Client> findCustomerByEmailAndPassword(@Param("email"),  @Param("password"));
*/

    Customer findByUsername(String username);

    @Query("select st from Customer st where st.uuid=:recordId")
    Optional<Customer> findByUuid(@Param("recordId")UUID uuid);

    @Query("select c from Customer c where c.email=:email")
    Optional<Customer> findByEmailId(@Param("email")String email);

    Customer findByEmail(String email);





}
