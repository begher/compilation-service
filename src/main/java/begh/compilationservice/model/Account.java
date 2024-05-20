package begh.compilationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Integer number;
    private String name;

    @ManyToOne
    @JoinColumn(name = "vat_code_id")
    private VatCode vatCode;

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id")
    private FiscalYear fiscalYear;

    private String referenceCode;
    private boolean isActive;
    private boolean isProjectAllowed;
    private boolean isCostCenterAllowed;
    private boolean isBlockedForManualBooking;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccountType type;

    @OneToMany(mappedBy = "account")
    private List<VoucherRow> voucherRows;
}
