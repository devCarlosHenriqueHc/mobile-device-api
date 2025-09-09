package com.mobiledevice.mappers;

import com.mobiledevice.dtos.CellphoneDTO;
import com.mobiledevice.entities.CellphoneEntity;
import com.mobiledevice.models.CellphoneModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CellphoneMapper {

    // Entity ↔ DTO
    CellphoneDTO toDto(CellphoneEntity entity);
    CellphoneEntity toEntity(CellphoneDTO dto);

    // Entity ↔ Model
    CellphoneModel toModel(CellphoneEntity entity);
    CellphoneEntity toEntity(CellphoneModel model);

    // List
    List<CellphoneDTO> toDtoList(List<CellphoneEntity> entities);
    List<CellphoneEntity> toEntityList(List<CellphoneDTO> dtos);
}

