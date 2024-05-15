package begh.compilationservice.service;

import begh.compilationservice.model.dto.AmountByCategoryDto;
import begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto;
import begh.compilationservice.model.dto.WeeklyDebitCreditByDateAndTypeDto;
import begh.compilationservice.repository.AccountBalanceRepository;
import begh.compilationservice.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final VoucherRepository repo;
    private final AccountBalanceRepository balanceRepo;
    public Page<DebitCreditByDateAndTypeDto> getCreditDebitByDateAndType(Date startDate, Date endDate, String accountType, Pageable pageable) {
        return repo.findDebitCreditAmountByAccountTypeAndDate(startDate, endDate, accountType, pageable);
    }

    public List<AmountByCategoryDto> getAmountByCategory() {
        return balanceRepo.findAmountByCategory(List.of(16, 17, 18, 24, 25, 26));
    }

    public List<AmountByCategoryDto> getAccountBalances(String type) {
        return balanceRepo.findBalancesByType(type);
    }

//    public Page<WeeklyDebitCreditByDateAndTypeDto> getWeeklyCreditDebitByDateAndType(Date startDate, Date endDate, String accountType, Pageable pageable) {
//        return repo.findWeeklyDebitCreditAmountByAccountTypeAndDate(startDate, endDate, accountType, pageable);
//    }
}
