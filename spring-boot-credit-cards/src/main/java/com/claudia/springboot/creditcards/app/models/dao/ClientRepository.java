package com.claudia.springboot.creditcards.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudia.springboot.creditcards.app.models.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{
}
