package br.com.venzel.product.modules.supplier.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.supplier.dtos.ResponseListSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.RequestUpdateSupplierDTO;
import br.com.venzel.product.modules.supplier.models.Supplier;

@Component
public class SupplierMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseSupplierDTO toDTO(Supplier supplier) {
        return modelMapper.map(supplier, ResponseSupplierDTO.class);
    }

    public ResponseListSupplierDTO toListDTO(Supplier supplier) {
        return modelMapper.map(supplier, ResponseListSupplierDTO.class);
    }

    public List<ResponseListSupplierDTO> toCollectionModel(List<Supplier> suppliers) {
        return suppliers.stream()
                    .map(e -> toListDTO(e))
                    .collect(Collectors.toList());
    }

    public Page<ResponseListSupplierDTO> toCollectionPageModel(Page<Supplier> suppliers) {
        return suppliers.map(e -> toListDTO(e));
    }

    public void toCopyEntity(RequestUpdateSupplierDTO dto, Supplier supplier) {
        modelMapper.map(dto, supplier);
    }
}
