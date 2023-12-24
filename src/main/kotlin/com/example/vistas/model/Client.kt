package com.example.vistas.model

import jakarta.persistence.*
import java.util.*



@Entity
@Table(name = "Client")
class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var fullname: String? = null
    var adrress: String? = null
    var email: String? = null

}