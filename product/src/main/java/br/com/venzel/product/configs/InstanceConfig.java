package br.com.venzel.product.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.category.repositories.CategoryRepository;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.supplier.models.Supplier;
import br.com.venzel.product.modules.supplier.repositories.SupplierRepository;

@Configuration
public class InstanceConfig implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        // CATEGORY

        Category category_frios = Category.create("Queijo");
        Category category_condimentos = Category.create("Coloral");
        Category category_limpeza = Category.create("Sabonete");

        // SUPPLIER

        Supplier supplier_apex = Supplier.create("Apex");
        Supplier supplier_extra = Supplier.create("Extra");
        Supplier supplier_kibom = Supplier.create("kibom");

        // PRODUCT

        Product product_sal = Product.create("Sal", 5, category_condimentos, supplier_extra);
        Product product_monbiju = Product.create("Mombiju", 4, category_limpeza, supplier_extra);
        Product product_pinhosol = Product.create("Pinho sol", 2, category_limpeza, supplier_apex);


        // REPOSITORIES

        categoryRepository.saveAll(Arrays.asList(category_frios, category_condimentos, category_limpeza));
        supplierRepository.saveAll(Arrays.asList(supplier_apex, supplier_extra, supplier_kibom));
        productRepository.saveAll(Arrays.asList(product_sal, product_monbiju, product_pinhosol));
    }
}
