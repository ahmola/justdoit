package com.example.jpa.repository;

import com.example.jpa.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // find

        // page
        Page<User> findAll(Pageable pageable);

        // name
        User findFirstByOrderByUsernameAsc();
        User findTopByOrderByRegistrationDateDesc();

        List<User> findByUsername(String username);
        List<User> findAllByOrderByUsernameAsc();
        List<User> findByUsernameContaining(String text);
        List<User> findByUsernameLike(String text);
        List<User> findByUsernameStartingWith(String start);
        List<User> findByUsernameEndingWith(String end);
        List<User> findByUsernameIgnoreCase(String username);

        // name and email
        List<User> findByUsernameAndEmail(String username, String email);
        List<User> findByUsernameOrEmail(String username, String email);

        // registrationdate
        List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
        List<User> findByRegistrationDateIn(Collection<LocalDate> dates);
        List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

        // level
        List<User> findByLevelOrderByUsernameDesc(int level);
        List<User> findByLevelGreaterThanEqual(int level);
        List<User> findByLevel(int level, Sort sort);
        List<User> findFirst2ByLevel(int level, Sort sort);

        // active
        List<User> findByActive(boolean active);
        List<User> findByActive(boolean active, Pageable pageable);

        // streamable
        Streamable<User> findByEmailContaining(String text);
        Streamable<User> findByLevel(int level);

    // find

    // custom query

        @Query("select count(u) from User u where u.active = ?1")
        int findNumberOfUsersByActivity(boolean active);

        @Query("select u from User u where u.level = :level and u.active = :active")
        List<User> findByLevelAndActive(@Param("level") int level
                , @Param("active") boolean active);

        @Query(value = "SELECT COUNT(*) FROM USER WHERE ACTIVE = ?1",
                nativeQuery = true)
        int findNumberOfUsersByActivityNative(boolean active);

        @Query("select u.username, LENGTH(u.email) as email_length from #{#entityName} u where u.username like %?1%")
        List<Object[]> findByAsArrayAndSort(String text, Sort sort);

    // custom query

    // projection

//        List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);
//
//        @Query("select u from User u where u.username = :username")
//        List<Projection.UsernameOnly> findByUsernameOnly(@Param("username") String username);
//        List<Projection.UsernameOnly> findByEmail(String email);
//
//        <T> List<T> findByUsername(String username, Class<T> type);

    // projection

    // modify

        @Modifying
        @Transactional
        @Query("update User u set u.level = ?2 where u.level = ?1")
        int updateLevel(int oldLevel, int newLevel);

        @Transactional
        int deleteByLevel(int level);

        @Transactional
        @Modifying
        @Query("delete from User u where u.level = ?1")
        int deleteBulkByLevel(int level);

    // modify
}
