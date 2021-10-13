package com.kapitalbank.task.service;

import com.kapitalbank.task.entity.Detail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DetailService {

    ResponseEntity<List<Detail>> getAllDetails();

    ResponseEntity<Detail> getDetailById(int id);

    ResponseEntity<Detail> createDetail(Detail detail);

    ResponseEntity<Detail> updateDetail(int id, Detail detail);

    ResponseEntity<HttpStatus> deleteDetail(int id);
}
