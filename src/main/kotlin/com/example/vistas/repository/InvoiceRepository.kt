package com.example.vistas.repository

import com.example.vistas.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long?> {

    fun findById(id: Long?): Invoice?

    @Query(nativeQuery = true)
    fun filterTotal(@Param ("value") value: Double?):List<Invoice>?
}