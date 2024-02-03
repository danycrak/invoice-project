package com.example.vistas.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.persistence.*

@Entity
@Table(name = "product")
class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idp")
    var idp: Long? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "des_product", nullable = false, length = 100)
    var desProduct: String? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "bra_product", nullable = false, length = 50)
    var braProduct: String? = null

    @NotNull(message = "Campo obligatorio")
    @Column(name = "pri_product", nullable = false, precision = 10, scale = 2)
    var priProduct: Double? = null

    @NotNull(message = "Campo obligatorio")
    @Column(name = "stock_product", nullable = false)
    var stockProduct: Int? = null
}