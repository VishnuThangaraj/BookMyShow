package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
