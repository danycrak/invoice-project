package com.example.vistas.model

import jakarta.validation.constraints.NotBlank
import jakarta.persistence.*

@Entity
@Table(name = "client")
class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idc", updatable = false)
    var idc: Long? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "nui_client", nullable = false, unique = true, length = 50)
    var nuiClient: String? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "ful_nam_client", nullable = false, length = 100)
    var fulNamClient: String? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "address_client", nullable = false, length = 150)
    var addressClient: String? = null

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "ema_client", nullable = false, length = 50)
    var emaClient: String? = null

}