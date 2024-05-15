package begh.compilationservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountByCategoryDto {
    private String type;
    private BigDecimal balance;
}
