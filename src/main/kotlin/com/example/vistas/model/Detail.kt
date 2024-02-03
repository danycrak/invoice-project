package com.example.vistas.model

import jakarta.validation.constraints.NotNull
import jakarta.persistence.*

@Entity
@Table(name = "detail")
class DetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idd")
    var idd: Long? = null

    @NotNull(message = "Campo obligatorio")
    @Column(name = "qua_detail", nullable = false)
    var quaDetail: Int? = null

    @NotNull(message = "Campo obligatorio")
    @Column(name = "pri_detail", nullable = false, precision = 10, scale = 2)
    var priDetail: Double? = null

    @Column(name = "idn_invoice")
    var idnInvoice: Long? = null

    @Column(name = "idp_product")
    var idpProduct: Long? = null

    // Relación con la entidad Invoice
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idn_invoice", referencedColumnName = "idn", insertable = false, updatable = false)
    var invoice: InvoiceModel? = null

    // Relación con la entidad Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idp_product", referencedColumnName = "idp", insertable = false, updatable = false)
    var product: ProductModel? = null
}