package com.frauddetectionapp.repositories.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlaggedTransactionRepository extends JpaRepository<FlaggedTransaction, Long>, FlaggedTransactionRepositoryCustom {
}
