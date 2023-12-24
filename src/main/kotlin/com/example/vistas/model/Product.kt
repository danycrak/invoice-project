package com.example.vistas.model

import jakarta.persistence.*

@Entity
@Table(name = "Product")

class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var price: String? = null
    var stock: Long? = null
    var brand: String? = null


}