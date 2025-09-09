package com.mobiledevice.services;

import com.mobiledevice.dtos.CellphoneDTO;
import com.mobiledevice.entities.CellphoneEntity;
import com.mobiledevice.mappers.CellphoneMapper;
import com.mobiledevice.models.CellphoneModel;
import com.mobiledevice.repositories.CellphoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CellphoneService {

    private final CellphoneRepository cellphoneRepository;
    private final CellphoneMapper cellphoneMapper;

    public CellphoneDTO add(CellphoneModel cellphoneModel) {
        CellphoneEntity newCellphone = cellphoneMapper.toEntity(cellphoneModel);
        CellphoneEntity saveCellphone = cellphoneRepository.save(newCellphone);
        return cellphoneMapper.toDto(saveCellphone);
    }

    public List<CellphoneDTO> getAll() {
        List<CellphoneEntity> cellphones = cellphoneRepository.findAll();
        return cellphoneMapper.toDtoList(cellphones);
    }

    public Optional<CellphoneDTO> getById(Long id) {
        Optional<CellphoneEntity> cellphoneOptional = cellphoneRepository.findById(id);
        return cellphoneOptional.map(cellphoneMapper::toDto);
    }

    public void delete(Long id) {
        cellphoneRepository.deleteById(id);
    }
}
