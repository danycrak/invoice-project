package com.example.vistas.model

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "Detail")

class Detail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var quantity: Long? = null
    var price: Double? = null
    var invoiceId: Long? = null
    var productId: Long? = null


}