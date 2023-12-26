package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, UUID> {
}
