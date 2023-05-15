package delivery.management.repo.products;

import delivery.management.model.entity.products.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, Long> {

    @Query("select st from ProductItems st where st.uuid=:recordId")
    Optional<ProductItems> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from ProductItems st where st.uuid=:recordId")
    Optional<ProductItems> deleteByUuid(@Param("recordId")UUID uuid);

//    Optional<ProductItems> findByName(String name);

//    @Query("SELECT p FROM ProductItems p WHERE " +
//            "p.name LIKE CONCAT('%',:query, '%')" )
//    List<ProductItems> searchProductsByName(String query);

//    @Query("select st from ProductItems st where st.productCategory.uuid=:recordId")
//    List<ProductItems> findByProductCategoryUuid(@Param("recordId") UUID productCategoryUuid);


}
