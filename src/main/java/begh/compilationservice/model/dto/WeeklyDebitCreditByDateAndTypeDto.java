package begh.compilationservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyDebitCreditByDateAndTypeDto {
    private Timestamp voucherDate;
    private Integer week;
    private String accountType;
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
}
