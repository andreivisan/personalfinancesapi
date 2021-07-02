package io.programminglife.personalfinancesapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.util.csv.CSVUtil;

@RestController
@RequestMapping("api/v1/fileupload")
public class FileUploadController {

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<CsvEntity>> uploadCsvFile(@RequestParam MultipartFile expensesCsv) {

        if (CSVUtil.hasCSVFormat(expensesCsv)) {
            try {
                return ResponseEntity.ok().body(CSVUtil.parseCsvToEntity(expensesCsv.getInputStream()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
