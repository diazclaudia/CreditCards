package com.claudia.springboot.creditcards.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.claudia.springboot.creditcards.app.models.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
}