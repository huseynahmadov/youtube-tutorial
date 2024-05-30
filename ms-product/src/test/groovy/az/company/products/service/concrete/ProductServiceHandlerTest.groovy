package az.company.products.service.concrete

import az.company.products.dao.entity.ProductEntity
import az.company.products.dao.repository.ProductRepository
import az.company.products.exception.NotFoundException
import az.company.products.model.request.CreateProductRequest
import az.company.products.model.response.ProductResponse
import az.company.products.service.abstraction.ProductService
import spock.lang.Specification

import static az.company.products.mapper.ProductMapper.PRODUCT_MAPPER
import static io.github.benas.randombeans.EnhancedRandomBuilder.aNewEnhancedRandom
import static java.util.Optional.empty
import static java.util.Optional.of

class ProductServiceHandlerTest extends Specification {
    def random = aNewEnhancedRandom()
    ProductService productService
    ProductRepository productRepository

    void setup() {
        productRepository = Mock()
        productService = new ProductServiceHandler(productRepository)
    }

    def "TestCreateProduct success"() {
        given:
        def createProductRequest = random.nextObject(CreateProductRequest)

        when:
        productService.createProduct(createProductRequest)

        then:
        1 * productRepository.save(PRODUCT_MAPPER.buildProductEntity(createProductRequest))
    }

    def "TestGetProductById success"() {
        given:
        def id = random.nextLong()

        def productEntity = random.nextObject(ProductEntity)


        when :
        def actual = productService.getProductById(id)

        then:
        1 * productRepository.findById(id) >> of(productEntity)
    }

    def "TestGetProductByIdShouldThrowNotFoundExceptionWhenProductDoesntExist"() {
        given:
        def id = random.nextLong()

        when :
        productService.getProductById(id)

        then:
        1 * productRepository.findById(id) >> empty()
        def exception = thrown(NotFoundException)
        exception.message == "Product not found with id: " + id
    }
}
