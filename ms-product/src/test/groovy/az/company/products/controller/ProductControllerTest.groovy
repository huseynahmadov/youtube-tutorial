package az.company.products.controller

import az.company.products.model.request.CreateProductRequest
import az.company.products.model.response.ProductResponse
import az.company.products.service.abstraction.ProductService
import az.company.products.service.concrete.ProductServiceHandler
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static io.github.benas.randombeans.EnhancedRandomBuilder.aNewEnhancedRandom
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductControllerTest extends Specification {
    def random = aNewEnhancedRandom()
    ProductController productController
    ProductService productService
    MockMvc mockMvc

    void setup() {
        productService = Mock(ProductServiceHandler)
        productController = new ProductController(productService)
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build()
    }

    def "TestCreateProduct success"() {
        given:
        def createProductRequest = random.nextObject(CreateProductRequest)
        def url = "/v1/products"
        def createProductJsonRequest = """
            {
              "name": "${createProductRequest.name}",
              "description": "${createProductRequest.description}",
              "price": ${createProductRequest.price},
              "quantity": ${createProductRequest.quantity}
            }
"""
        when:
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(createProductJsonRequest))
                .andExpect(status().isCreated())

        then:
        1 * productService.createProduct(createProductRequest)
    }

    def "TestCreateProduct validation exception when field missing"() {
        given:
        def createProductRequest = random.nextObject(CreateProductRequest)
        def url = "/v1/products"
        def createProductJsonRequest = """
            {
              "description": "${createProductRequest.description}",
              "price": ${createProductRequest.price},
              "quantity": ${createProductRequest.quantity}
            }
"""
        when:
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(createProductJsonRequest))
                .andExpect(status().isBadRequest())

        then:
        0 * productService.createProduct(_)
    }

    def "TestGetProductById success"() {
        given:
        def url = "/v1/products/{id}"
        def id = random.nextLong()
        def productResponse = random.nextObject(ProductResponse)
        def productJsonResponse = """
            {
          "id": $productResponse.id,
          "name": "$productResponse.name",
          "description": "$productResponse.description",
          "price": $productResponse.price,
          "quantity": $productResponse.quantity
        }
"""
        when:
        def result = mockMvc.perform(get(url, id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

        then:
        1 * productService.getProductById(id) >> productResponse
        JSONAssert.assertEquals(productJsonResponse, result.response.contentAsString, true)
    }
}
