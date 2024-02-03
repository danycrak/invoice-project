package com.example.vistas.service

import com.example.vistas.model.ProductModel
import com.example.vistas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.transaction.annotation.Transactional;
//import javax.validation.ValidationException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list (pageable: Pageable,productModel:ProductModel):Page<ProductModel>{
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return productRepository.findAll(Example.of(productModel, matcher), pageable)
    }

    fun save(product: ProductModel): ProductModel {
        validateProduct(product)
        return productRepository.save(product)
    }

    fun update(product: ProductModel):ProductModel {
        if (product.idp == null) {
            //          throw ValidationException("ID no proporcionada para actualizar")
        }
        validateProduct(product)
        return productRepository.save(product)
    }


    fun updateDetails(product: ProductModel): ProductModel {
        if (product.idp == null) {
            //        throw ValidationException("ID no proporcionada para actualizar detalles")
        }
        // Actualizar detalles específicos del producto si es necesario
        // Puedes implementar la lógica según tus requisitos
        // Por ejemplo: product.stock = newStock
        return productRepository.save(product)
    }

    fun listById(idp: Long): ProductModel {
        return (productRepository.findById(idp)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")) as ProductModel
    }


    fun delete(idp: Long):Boolean? {
        try{
            val response = productRepository.findById(idp)
                    ?: throw Exception("ID no existe")
            productRepository.deleteById(idp!!)
            return true
        }
        catch (ex:Exception){

            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    private fun validateProduct(product: ProductModel) {
        // Implementa las validaciones necesarias para el Product aquí
        // Por ejemplo: asegurarse de que los campos obligatorios no estén vacíos
        // if (product.desProduct.isNullOrBlank() || ...)
    }
}