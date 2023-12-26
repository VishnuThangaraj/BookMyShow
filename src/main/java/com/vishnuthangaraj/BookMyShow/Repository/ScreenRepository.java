package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, UUID> {

    // Book Screen for the show
    @Query(value = "update screen set status = true where id =: screenId", nativeQuery = true)
    public void bookScreen(UUID screenId);
}
