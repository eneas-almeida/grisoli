package br.com.venzel.product.modules.supplier.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.supplier.dtos.CreateSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ListSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.UpdateSupplierDTO;
import br.com.venzel.product.modules.supplier.models.Supplier;

@Component
public class SupplierMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SupplierDTO toDTO(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    public ListSupplierDTO toListDTO(Supplier supplier) {
        return modelMapper.map(supplier, ListSupplierDTO.class);
    }

    public List<ListSupplierDTO> toCollectionModel(List<Supplier> suppliers) {
        return suppliers.stream()
                    .map(e -> toListDTO(e))
                    .collect(Collectors.toList());
    }

    public Page<ListSupplierDTO> toCollectionPageModel(Page<Supplier> suppliers) {
        return suppliers.map(e -> toListDTO(e));
    }

    public Supplier toEntity(CreateSupplierDTO dto) {
        return modelMapper.map(dto, Supplier.class);
    }

    public void toCopyEntity(UpdateSupplierDTO dto, Supplier supplier) {
        modelMapper.map(dto, supplier);
    }
}
