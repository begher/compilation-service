package begh.compilationservice.repository;

import begh.compilationservice.model.Voucher;
import begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto;
import begh.compilationservice.model.dto.WeeklyDebitCreditByDateAndTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    @Query("SELECT new begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto(" +
            "v.voucherDate, " +
            "at.type, " +
            "SUM(vr.debitAmount), " +
            "SUM(vr.creditAmount)) " +
            "FROM Voucher v " +
            "JOIN v.voucherRows vr " +
            "JOIN vr.account a " +
            "JOIN a.type at " +
            "WHERE v.voucherDate BETWEEN :startDate AND :endDate " +
            "AND at.type = :accountType " +
            "GROUP BY v.voucherDate, at.type " +
            "ORDER BY v.voucherDate, at.type")
    Page<DebitCreditByDateAndTypeDto> findDebitCreditAmountByAccountTypeAndDate(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("accountType") String accountType,
            Pageable pageable);

//    @Query("SELECT new begh.compilationservice.model.dto.DebitCreditByDateAndTypeDto(" +
//            "week, at.type, SUM(vr.debitAmount), SUM(vr.creditAmount)) " +
//            "FROM Voucher v " +
//            "JOIN v.voucherRows vr " +
//            "JOIN vr.account a " +
//            "JOIN a.type at " +
//            "WHERE v.voucherDate BETWEEN :startDate AND :endDate " +
//            "AND at.type = :accountType " +
//            "GROUP BY EXTRACT(WEEK FROM v.voucherDate) AS week, at.type " +
//            "ORDER BY week, at.type")
//    Page<DebitCreditByDateAndTypeDto> findDebitCreditAmountByAccountTypeAndDateGroupedByWeek(
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate,
//            @Param("accountType") String accountType,
//            Pageable pageable);
}
