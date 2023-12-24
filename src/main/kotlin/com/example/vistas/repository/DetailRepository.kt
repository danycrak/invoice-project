package com.example.vistas.repository


import com.example.vistas.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository : JpaRepository<Detail, Long?> {

    fun findById(id: Long?): Detail?

   fun findByInvoiceId (invoiceId: Long?): List <Detail>
}