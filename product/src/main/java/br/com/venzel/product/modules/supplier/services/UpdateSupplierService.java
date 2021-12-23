package br.com.venzel.product.modules.supplier.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.supplier.dtos.RequestUpdateSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.exceptions.SupplierNotFoundException;
import br.com.venzel.product.modules.supplier.mappers.SupplierMapper;
import br.com.venzel.product.modules.supplier.models.Supplier;
import br.com.venzel.product.modules.supplier.repositories.SupplierRepository;
import br.com.venzel.product.modules.supplier.utils.SupplierMessageUtil;

@Service
public class UpdateSupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Transactional
    public ResponseSupplierDTO execute(RequestUpdateSupplierDTO requestDTO) {

        /* Find supplier by id */

        Optional<Supplier> optionalEntity = supplierRepository.findById(requestDTO.getId());

        /* Guard strategy */

        if (!optionalEntity.isPresent()) {
            throw new SupplierNotFoundException(SupplierMessageUtil.SUPPLIER_NOT_FOUND);
        }

        /* Get object */

        Supplier supplier = optionalEntity.get();

        /* Update object */

        supplier.setName(requestDTO.getName());

        /* Convert to dto and return */

        return supplierMapper.toDTO(supplier);
    }
}
