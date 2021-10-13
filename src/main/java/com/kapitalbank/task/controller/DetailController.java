package com.kapitalbank.task.controller;

import com.kapitalbank.task.entity.Customer;
import com.kapitalbank.task.entity.Detail;
import com.kapitalbank.task.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Detail>> getAllDetails()
    {
        return detailService.getAllDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> getDetailById(@PathVariable int id)
    {
        return detailService.getDetailById(id);
    }

    @PostMapping("")
    public ResponseEntity<Detail> createDetail(@RequestBody Detail detail)
    {
        return detailService.createDetail(detail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable int id, @RequestBody Detail detail)
    {
        return detailService.updateDetail(id, detail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDetail(@PathVariable int id)
    {
        return detailService.deleteDetail(id);
    }
}
