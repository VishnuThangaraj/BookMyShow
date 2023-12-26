package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
}
