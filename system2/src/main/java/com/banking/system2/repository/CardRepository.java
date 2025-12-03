
package com.banking.system2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.system2.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {}
