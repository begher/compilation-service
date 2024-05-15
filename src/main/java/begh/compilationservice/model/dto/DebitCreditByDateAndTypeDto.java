package begh.compilationservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebitCreditByDateAndTypeDto {
    private Date voucherDate;
    private String accountType;
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
}
