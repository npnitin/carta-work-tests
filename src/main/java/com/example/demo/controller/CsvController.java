package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.util.CsvDataPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
public class CsvController {

    @Autowired
    CsvDataPopulator csvDataPopulator;


    @PostMapping(value = "")
    public ResponseEntity<ResponseDTO> getAvgByColumn(@RequestParam("data") MultipartFile file, @RequestParam("column") String column){
       ResponseDTO responseDTO = new ResponseDTO();
       try{
           if(file == null || column == null || column.isEmpty()){
               return new ResponseEntity("Can not process invalid request",HttpStatus.BAD_REQUEST);
           }
           Map<String,List<Object>> map = csvDataPopulator.populateCsv(file);
           if(!map.containsKey(column)){
               return new ResponseEntity("No such column exists:"+column,HttpStatus.BAD_REQUEST);
           }
           responseDTO.setData(csvDataPopulator.getAvgOfCol(map,column));
       }catch(Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
