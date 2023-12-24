package com.example.vistas.repository

import com.example.vistas.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : JpaRepository<Product, Long?> {

    fun findById(id: Long?): Product?


}