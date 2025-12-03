
package com.example.system2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.system2.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {}
