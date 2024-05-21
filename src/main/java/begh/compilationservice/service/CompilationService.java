package begh.compilationservice.service;

import begh.compilationservice.model.dto.AmountByCategoryDto;
import begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto;
import begh.compilationservice.model.dto.SaldoByDate;
import begh.compilationservice.repository.AccountBalanceRepository;
import begh.compilationservice.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final VoucherRepository repo;
    //private final AccountBalanceRepository balanceRepo;
    public Page<DebitCreditByDateAndTypeDto> getCreditDebitByDateAndType(Date startDate, Date endDate, String accountType, Pageable pageable) {
        return repo.findDebitCreditAmountByAccountTypeAndDate(startDate, endDate, accountType, pageable);
    }

    public List<AmountByCategoryDto> getAmountByCategory() {
        return setBalanceToPositiv(repo.findSaldoByCategoryIds(List.of(18, 20, 26, 25, 29)));
    }

    public List<AmountByCategoryDto> getAccountBalances(String type) {
        return setBalanceToPositiv(repo.findSaldoByType(type));
    }

    public List<AmountByCategoryDto> setBalanceToPositiv(List<AmountByCategoryDto> balances) {
        return balances.stream()
                .peek(e -> {
                    if (e.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                        e.setBalance(e.getBalance().negate());
                    }
                })
                .toList();
    }

    public List<SaldoByDate> weekly(Date startDate, Date endDate) {
        return repo.findSaldoWeekMapping(startDate, endDate, 1930);
    }

    public List<SaldoByDate> monthly(Date startDate, Date endDate) {
        return repo.findSaldoMonthMapping(startDate, endDate, 1930);
    }
}
