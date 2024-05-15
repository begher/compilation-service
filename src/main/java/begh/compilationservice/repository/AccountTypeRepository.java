package begh.compilationservice.repository;

import begh.compilationservice.model.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    @Override
    Page<AccountType> findAll(Pageable pageable);
}
