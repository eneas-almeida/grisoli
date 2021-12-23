package br.com.venzel.product.modules.supplier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.supplier.dtos.RequestCreateSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.exceptions.SupplierAlreadyExistsException;
import br.com.venzel.product.modules.supplier.mappers.SupplierMapper;
import br.com.venzel.product.modules.supplier.models.Supplier;
import br.com.venzel.product.modules.supplier.repositories.SupplierRepository;
import br.com.venzel.product.modules.supplier.utils.SupplierMessageUtil;

@Service
public class CreateSupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private SupplierMapper supplierMapper;

    @Transactional
    public ResponseSupplierDTO execute(RequestCreateSupplierDTO requestDTO) {

        /* Verify supplier existence with name in repository */        

        Boolean optionalEntity = supplierRepository.existsByName(requestDTO.getName());

        /*  Guard strategy */

        if (optionalEntity) {
            throw new SupplierAlreadyExistsException(SupplierMessageUtil.SUPPLIER_ALREADY_EXISTS);
        }

        /* Create object */

        Supplier supplier = Supplier.create(requestDTO.getName());

        /* Save data in repository */

        Supplier supplierSaved = supplierRepository.save(supplier);

        /* Parse entity to dto */

        ResponseSupplierDTO supplierDTO = supplierMapper.toDTO(supplierSaved);

        /* Return supplier dto */

        return supplierDTO;
    }
}
