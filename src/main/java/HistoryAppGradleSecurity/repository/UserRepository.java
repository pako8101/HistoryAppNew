package HistoryAppGradleSecurity.repository;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEnt,Long> {

    Optional<UserEnt> findUserEntByUsername(String username);

    Optional<UserEnt> findUserByUsernameAndPassword(String username, String password);

    Optional<UserEnt> findByEmail(String email);



}
