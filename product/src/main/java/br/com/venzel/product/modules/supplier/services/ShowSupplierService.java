package br.com.venzel.product.modules.supplier.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.exceptions.SupplierNotFoundException;
import br.com.venzel.product.modules.supplier.mappers.SupplierMapper;
import br.com.venzel.product.modules.supplier.models.Supplier;
import br.com.venzel.product.modules.supplier.repositories.SupplierRepository;
import br.com.venzel.product.modules.supplier.utils.SupplierMessageUtil;

@Service
public class ShowSupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseSupplierDTO execute(Integer supplierId) {

        /* Find supplier by id */

        Optional<Supplier> optionalEntity = supplierRepository.findById(supplierId);

        /* Guard strategy */

        if (!optionalEntity.isPresent()) {
            throw new SupplierNotFoundException(SupplierMessageUtil.SUPPLIER_NOT_FOUND);
        }

        /* convert to dto and return */

        return supplierMapper.toDTO(optionalEntity.get());
    }
}
