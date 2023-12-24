package com.example.vistas.service

import com.example.vistas.model.Product
import com.example.vistas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }
    //clase service

    fun save(details: Product): Product {
        try {
            return productRepository.save(details)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    //clase service

    fun update(product: Product): Product {
        try {
            productRepository.findById(product.id)
                    ?: throw Exception("ID no existe")

            return productRepository.save(product)
        }
        catch (ex:Exception){


            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    //clase service

    fun updateDescription (product: Product): Product {
        try{
            val response = productRepository.findById(product.id)
                    ?: throw Exception("ID no existe")
            response.apply {
              //  precio=product.precio
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
//Clase Service

    fun listById (id:Long?): Product?{
        return productRepository.findById(id)
    }

    //clase service

    fun delete (id: Long?):Boolean?{
        try{
            val response = productRepository.findById(id)
                    ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}