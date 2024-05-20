package begh.compilationservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaldoByDate {
    private Date date;
    private BigDecimal income;
    private BigDecimal outcome;
}
