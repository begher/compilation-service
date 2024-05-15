package begh.compilationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_balances")
public class AccountBalance {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_number", referencedColumnName = "number")
    private Account account;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccountType type;
}
