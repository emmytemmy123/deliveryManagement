package delivery.management.model.entity.document;

import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "document")
public class Document extends BaseEntity {

    private String type;
    private String name;
    private String size;
    private String description;
    private String filePath;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id", insertable = false, updatable = false)
    private ProductItems productItems;

    public Document(){}



}
