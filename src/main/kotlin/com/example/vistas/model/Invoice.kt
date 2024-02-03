package com.example.vistas.model

import jakarta.validation.constraints.NotBlank
import jakarta.persistence.*

@Entity
@Table(name = "invoice")
class InvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idn")
    var idn: Long? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "cod_invoice", nullable = false, unique = true, length = 10)
    var codInvoice: String? = null

    @Column(name = "cre_at_invoice", nullable = false)
    var creAtInvoice: java.sql.Timestamp? = null

    @Column(name = "tot_invoice", nullable = false, precision = 10, scale = 2)
    var totInvoice: Double? = null

    @Column(name = "idc_client")
    var idcClient: Long? = null

    // La relación con la entidad Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idc_client", referencedColumnName = "idc", insertable = false, updatable = false)
    var client: Client? = null
}