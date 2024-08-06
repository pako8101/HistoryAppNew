package HistoryAppGradleSecurity.repository;

import HistoryAppGradleSecurity.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Repository
public interface LogRepository extends JpaRepository<LogEntity,Long> {

    @Transactional
    @Modifying
    @Query("DELETE from LogEntity as l where l.appearanceTime   < :olderThan")
    void deleteOldLogs(Instant olderThan);


}
