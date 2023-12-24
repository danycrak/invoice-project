package com.example.vistas.controller

import com.example.vistas.model.Product
import com.example.vistas.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/product")   //endpoint
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])

class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list ():List <Product>{
        return productService.list()
    }

    @PostMapping
    fun save (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(product), HttpStatus.OK)
    }
    //clase controller
    @PutMapping
    fun update (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.OK)
    }
    //clase  controller
    @PatchMapping
    fun updateDescription (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateDescription(product), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(productService.listById (id), HttpStatus.OK)

    }

    //clase  controller
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return productService.delete(id)
    }

}