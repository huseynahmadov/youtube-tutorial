package az.company.products.mapper

import az.company.products.dao.entity.ProductEntity
import az.company.products.model.request.CreateProductRequest
import spock.lang.Specification

import static io.github.benas.randombeans.EnhancedRandomBuilder.aNewEnhancedRandom

class ProductMapperTest extends Specification {
    def random = aNewEnhancedRandom()

    def "TestBuildProductEntity"() {
        given:
        def createProductRequest = random.nextObject(CreateProductRequest)

        when:
        def actual = ProductMapper.PRODUCT_MAPPER.buildProductEntity(createProductRequest)

        then:
        actual.name == createProductRequest.name
        actual.description == createProductRequest.description
        actual.quantity == createProductRequest.quantity
        actual.price == createProductRequest.price
    }

    def "TestBuildProductResponse"() {
        given:
        def productEntity = random.nextObject(ProductEntity)

        when:
        def actual = ProductMapper.PRODUCT_MAPPER.buildProductResponse(productEntity)

        then:
        actual.id == productEntity.id
        actual.name == productEntity.name
        actual.quantity == productEntity.quantity
        actual.price == productEntity.price
        actual.description == productEntity.description
    }
}
