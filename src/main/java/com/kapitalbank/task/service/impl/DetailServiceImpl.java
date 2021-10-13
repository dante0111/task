package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.entity.Detail;
import com.kapitalbank.task.repository.DetailRepository;
import com.kapitalbank.task.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {

    private DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public ResponseEntity<List<Detail>> getAllDetails() {
        try {
            List<Detail> details = detailRepository.findAll();

            return new ResponseEntity<>(details, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Detail> getDetailById(int id) {
        Optional<Detail> detail = detailRepository.findById(id);
        if(detail.isPresent())
        {
            return new ResponseEntity<>(detail.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Detail> createDetail(Detail detail) {
        try {
            Detail detail1 = detailRepository
                    .save(new Detail(detail.getQuantity(), detail.getProduct()));
            return new ResponseEntity<>(detail1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Detail> updateDetail(int id, Detail detail) {
        Optional<Detail> detail1 = detailRepository.findById(id);

        if (detail1.isPresent()) {
            Detail detail2 = detail1.get();
            detail2.setQuantity(detail.getQuantity());
            detail2.setProduct(detail.getProduct());
            return new ResponseEntity<>(detailRepository.save(detail2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteDetail(int id) {
        try {
            detailRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
