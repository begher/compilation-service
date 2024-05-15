package begh.compilationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher_row")
public class VoucherRow {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;
    @Column(name = "account_description")
    private String accountDescription;
    @Column(name = "debit_amount")
    private BigDecimal debitAmount;
    @Column(name = "credit_amount")
    private BigDecimal creditAmount;
    @Column(name = "transaction_text")
    private String transactionText;
}
