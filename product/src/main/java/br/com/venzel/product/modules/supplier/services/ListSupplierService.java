package br.com.venzel.product.modules.supplier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.venzel.product.modules.supplier.dtos.ResponseListSupplierDTO;
import br.com.venzel.product.modules.supplier.exceptions.SupplierNotFoundException;
import br.com.venzel.product.modules.supplier.mappers.SupplierMapper;
import br.com.venzel.product.modules.supplier.models.Supplier;
import br.com.venzel.product.modules.supplier.repositories.SupplierRepository;
import br.com.venzel.product.modules.supplier.utils.SupplierMessageUtil;

@Service
public class ListSupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;
    
    public Page<ResponseListSupplierDTO> execute(Integer page, Integer linesPerPage, String orderBy, String direction) {

        /* Pagination */

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        /* Find all in repository */

        Page<Supplier> suppliers = supplierRepository.findAll(pageRequest);

        /* Guard strategy */

        if (suppliers.isEmpty()) {
            throw new SupplierNotFoundException(SupplierMessageUtil.SUPPLIER_NOT_FOUND);
        }

        /* Convert to dto list and return */

        return supplierMapper.toCollectionPageModel(suppliers);
    }
}
