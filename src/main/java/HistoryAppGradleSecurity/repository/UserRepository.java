package HistoryAppGradleSecurity.repository;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEnt,Long> {

    Optional<UserEnt> findUserEntByUsername(String username);

    Optional<UserEnt> findUserByUsernameAndPassword(String username, String password);

    Optional<UserEnt> findByEmail(String email);

UserEnt getUserEntByUsername(String username);

@Query("select u from  UserEnt u where  u.RegistrationEmailSend = false ")
    List<UserEnt> findUsersPendingEmails();
}
