package com.example.vistas.repository

import com.example.vistas.model.ProductModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductModel, Long?> {
    fun findById(idp: Long?): ProductModel?
    // Otros métodos de consulta o personalizados si son necesarios
}