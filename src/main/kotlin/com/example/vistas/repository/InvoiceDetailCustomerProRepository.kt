package com.example.vistas.repository

import com.example.vistas.model.InvoiceDetailCustomerProductModel

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface InvoiceDetailCustomerProRepository : JpaRepository<InvoiceDetailCustomerProductModel, Long?> {

    @Query(nativeQuery = true, value = "SELECT * FROM invoice_detail_customer_product WHERE cod_invoice = :codInvoice")
    fun findByCodInvoice(codInvoice: String): List<InvoiceDetailCustomerProductModel>
}