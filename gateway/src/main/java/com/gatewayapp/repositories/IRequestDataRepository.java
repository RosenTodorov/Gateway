package com.gatewayapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gatewayapp.entities.RequestData;

@Repository
public interface IRequestDataRepository extends JpaRepository<RequestData, Long> {

}
