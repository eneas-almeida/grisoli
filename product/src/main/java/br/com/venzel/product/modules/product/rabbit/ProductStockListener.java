package br.com.venzel.product.modules.product.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.product.dtos.ProductStockDTO;
import br.com.venzel.product.modules.product.services.UpdateProductStockService;

@Component
public class ProductStockListener {
    
    @Autowired
    private UpdateProductStockService updateProductStock;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void reciveProductStock(ProductStockDTO productStockDTO) {
        updateProductStock.execute(productStockDTO);
    }
}
