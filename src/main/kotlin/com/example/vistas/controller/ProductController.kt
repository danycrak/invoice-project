package com.example.vistas.controller


import com.example.vistas.model.ProductModel
import com.example.vistas.service.ProductService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController   //Define una responsabilidad a un componente
@RequestMapping("/product")   //endpoint
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list (productModel: ProductModel, pageable: Pageable):ResponseEntity<*>{
        val response= productService.list(pageable,productModel)
        return ResponseEntity(response, HttpStatus.OK)
    }

//@RequestParam searchValue:String

    @PostMapping
    fun save(@RequestBody @Valid product: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(productService.save(product), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody product: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(productService.update(product), HttpStatus.OK)
    }

    @PatchMapping
    fun updateDetails(@RequestBody product: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(productService.updateDetails(product), HttpStatus.OK)
    }

    @GetMapping("/{idp}")
    fun listById(@PathVariable("idp") idp: Long): ResponseEntity<*> {
        return ResponseEntity(productService.listById(idp), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{idp}")
    fun delete(@PathVariable("idp") idp: Long): Boolean? {
        return productService.delete(idp)
    }

}