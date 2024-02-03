package com.example.vistas.model


import jakarta.validation.constraints.NotBlank
import jakarta.persistence.*

@Entity
@Table(name = "invoice_detail_customer_product") // Nombre de la vista
class InvoiceDetailCustomerProductModel {
    // Aquí se mapean los campos de la vista
    @Id
    @Column(name = "cod_invoice")
    var codInvoice: String? = null

    @Column(name = "customer_name")
    var customerName: String? = null

    @Column(name = "qua_detail")
    var quaDetail: Int? = null

    @Column(name = "pri_detail")
    var priDetail: Double? = null

    @Column(name = "des_product")
    var desProduct: String? = null

    @Column(name = "bra_product")
    var braProduct: String? = null
}