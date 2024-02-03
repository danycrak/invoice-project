package com.example.vistas.repository

import com.example.vistas.model.InvoiceModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<InvoiceModel, Long?> {
    fun findById(idn: Long?):   InvoiceModel?
    // Otros métodos de consulta o personalizados si son necesarios

    @Query(nativeQuery = true)
    fun filterTotal (@Param ("value") value: Double?): List<InvoiceModel>?

    @Query(nativeQuery = true)
    fun filterClient (@Param ("value") value: Long?):List<InvoiceModel>?

}