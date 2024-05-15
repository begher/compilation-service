package begh.compilationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    private UUID id;
    @Column(name = "voucher_date")
    private Date voucherDate;
    @Column(name = "voucher_text")
    private String voucherText;
    @OneToMany(mappedBy = "voucher")
    private List<VoucherRow> voucherRows;
}
