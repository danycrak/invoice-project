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
}


  /*  @NotBlank(message = "Campo obligatorio") //validate
    var nui: String? = null

    @NotBlank(message = "Campo obligatorio")  //validate

  /* var description: String? = null
    var nombre: String? = null
    var fecha: Date? = null */