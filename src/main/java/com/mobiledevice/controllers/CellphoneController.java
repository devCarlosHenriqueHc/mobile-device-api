package com.mobiledevice.controllers;

import com.mobiledevice.dtos.CellphoneDTO;
import com.mobiledevice.models.CellphoneModel;
import com.mobiledevice.services.CellphoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cellphones")
@RequiredArgsConstructor
public class CellphoneController {

    private final CellphoneService cellphoneService;

    @PostMapping
    public ResponseEntity<CellphoneDTO> createCellphone(@RequestBody CellphoneModel cellphoneModel) {
        CellphoneDTO newCellphone = cellphoneService.add(cellphoneModel);
        return new ResponseEntity<>(newCellphone, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CellphoneDTO>> getAllCellphones() {
        List<CellphoneDTO> cellphones = cellphoneService.getAll();
        return new ResponseEntity<>(cellphones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CellphoneDTO> getCellphoneById(@PathVariable Long id) {
        Optional<CellphoneDTO> cellphone = cellphoneService.getById(id);
        return cellphone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCellphone(@PathVariable Long id) {
        cellphoneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
