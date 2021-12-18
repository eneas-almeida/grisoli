package br.com.venzel.product.modules.supplier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.supplier.dtos.CreateRequestSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;
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
    public SupplierDTO execute(CreateRequestSupplierDTO req) {
        /* Verify supplier existence with name */        

        Boolean existsCatgory = supplierRepository.existsByName(req.getName());

        /*  Guard strategy */

        if (existsCatgory) {
            throw new SupplierAlreadyExistsException(SupplierMessageUtil.SUPPLIER_ALREADY_EXISTS);
        }

        /* Create object */

        Supplier supplier = Supplier.create(req.getName());

        /* Save data in repository */

        Supplier supplierSaved = supplierRepository.save(supplier);

        /* Parse entity to dto */

        SupplierDTO supplierDTO = supplierMapper.toDTO(supplierSaved);

        /* Return supplier dto */

        return supplierDTO;
    }
}
