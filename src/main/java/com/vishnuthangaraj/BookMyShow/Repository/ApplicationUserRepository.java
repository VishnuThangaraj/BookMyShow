package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {

    // Get the User by Email
    @Query(value = "select * from -- where email =:email", nativeQuery = true)
    public ApplicationUser getUserByEmail(String email);
}
