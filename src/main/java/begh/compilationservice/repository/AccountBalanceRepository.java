package begh.compilationservice.repository;

import begh.compilationservice.model.AccountBalance;
import begh.compilationservice.model.dto.AmountByCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Integer> {
    @Query("SELECT new begh.compilationservice.model.dto.AmountByCategoryDto(at.type, SUM(ab.balance)) " +
            "FROM AccountBalance ab " +
            "JOIN ab.type at " +
            "WHERE at.id IN :typeIds " +
            "GROUP BY at.type")
    List<AmountByCategoryDto> findAmountByCategory(@Param("typeIds") List<Integer> typeIds);

    @Query("SELECT new begh.compilationservice.model.dto.AmountByCategoryDto(a.name, ab.balance) " +
            "FROM AccountBalance ab " +
            "JOIN ab.account a " +
            "JOIN ab.type at " +
            "WHERE at.type = :type AND ab.balance != 0 " +
            "GROUP BY a.name, ab.balance")
    List<AmountByCategoryDto> findBalancesByType(@Param("type") String type);
}
