package az.company.orders.client;

import az.company.orders.client.decoder.CustomErrorDecoder;
import az.company.orders.model.client.request.ReduceQuantityRequest;
import az.company.orders.model.client.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-product",
        url = "http://localhost:9999/v1/products",
        configuration = CustomErrorDecoder.class
)
public interface ProductClient {
    @PostMapping("/reduce-quantity")
    void reduceQuantity(@RequestBody ReduceQuantityRequest reduceQuantityRequest);

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
