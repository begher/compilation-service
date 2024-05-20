package begh.compilationservice.model;

import begh.compilationservice.model.dto.SaldoByDate;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SaldoWeekMapping",
                classes = @ConstructorResult(
                        targetClass = SaldoByDate.class,
                        columns = {
                                @ColumnResult(name = "date", type = Date.class),
                                @ColumnResult(name = "income", type = BigDecimal.class),
                                @ColumnResult(name = "outcome", type = BigDecimal.class)
                        }
                )
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "SaldoByDate.findSaldoWeekMapping",
                query = "SELECT " +
                        "DATE_TRUNC('week', v.voucher_date) as date, " +
                        "SUM(CASE WHEN vr.debit_amount IS NOT NULL THEN vr.debit_amount ELSE 0 END) as income, " +
                        "SUM(CASE WHEN vr.credit_amount IS NOT NULL THEN vr.credit_amount ELSE 0 END) as outcome " +
                        "FROM voucher v " +
                        "JOIN voucher_row vr ON v.id = vr.voucher_id " +
                        "WHERE v.voucher_date BETWEEN :startDate AND :endDate " +
                        "AND vr.account_number = :accountNumber " +
                        "GROUP BY DATE_TRUNC('week', v.voucher_date) " +
                        "ORDER BY date",
                resultSetMapping = "SaldoWeekMapping"
        ),
        @NamedNativeQuery(
                name = "SaldoByDate.findSaldoMonthMapping",
                query = "SELECT " +
                        "DATE_TRUNC('month', v.voucher_date) as date, " +
                        "SUM(CASE WHEN vr.debit_amount IS NOT NULL THEN vr.debit_amount ELSE 0 END) as income, " +
                        "SUM(CASE WHEN vr.credit_amount IS NOT NULL THEN vr.credit_amount ELSE 0 END) as outcome " +
                        "FROM voucher v " +
                        "JOIN voucher_row vr ON v.id = vr.voucher_id " +
                        "WHERE v.voucher_date BETWEEN :startDate AND :endDate " +
                        "AND vr.account_number = :accountNumber " +
                        "GROUP BY DATE_TRUNC('month', v.voucher_date) " +
                        "ORDER BY date",
                resultSetMapping = "SaldoWeekMapping"
        )
})
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
