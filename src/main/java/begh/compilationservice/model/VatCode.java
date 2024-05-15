package begh.compilationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vat_code")
public class VatCode {
    @Id
    private UUID id;
    private String code;
    private String description;
    @Column(name = "vat_rate")
    private BigDecimal vatRate;
}
