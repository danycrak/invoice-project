package com.example.vistas.service

import com.example.vistas.model.DetailModel
import com.example.vistas.repository.DetailRepository
import com.example.vistas.repository.InvoiceRepository
import com.example.vistas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
//import javax.transaction.Transactional
//import javax.xml.bind.ValidationException
import  com.example.vistas.model.InvoiceModel
import jakarta.transaction.Transactional
import jakarta.validation.ValidationException

//import com.example.vistas.repository.ProductRepository

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var  invoiceRepository: InvoiceRepository

    fun list (pageable: Pageable,detail: DetailModel):Page<DetailModel>{
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return detailRepository.findAll(Example.of(detail, matcher), pageable)
    }



    @Transactional
    fun save(detail: DetailModel): DetailModel {

        try {
            productRepository.findById(detail.idpProduct)
                    ?: throw Exception("Id del Producto no encontrada")
            //El objeto debe estar verificado.
            detail.quaDetail?.toString()?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("Cantidad no debe ser vacio")
            detail.priDetail?.toString()?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("Precio no debe ser vacio")

            val detailSaved = detailRepository.save(detail)

            val prod = productRepository.findById(detail.idpProduct)
                    ?: throw Exception("ID no existe")

            prod.apply {

                stockProduct = stockProduct?.minus(detail.quaDetail!!)

            }

            prod.stockProduct?.let { currentStock ->
                val quantityToSubtract = detail.quaDetail ?: 0
                if (currentStock >= quantityToSubtract) {
                    prod.stockProduct = currentStock - quantityToSubtract
                } else {
                    throw ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "No hay suficientes productos disponibles"
                    )
                }
            } ?: throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado"
            )

            prod.stockProduct = (prod.stockProduct ?: 0) + (detail.quaDetail ?: 0)
            productRepository.save(prod)
///////////////////7

            updateInvoiceTotal(detail)

            return detailSaved

        }catch (ex : Exception){
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
    private fun updateInvoiceTotal(detail: DetailModel) {
        val listDetails = detailRepository.findByInvoice_Idn(detail.idnInvoice
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Factura no encontrada con ID ${detail.idnInvoice}")
        )

        var sum: Double = 0.0
        listDetails.forEach { item ->
            sum += (item.priDetail ?: 0.0) * (item.quaDetail ?: 0)
        }

        val invoice = detail.invoice
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Factura no encontrada con ID ${detail.idnInvoice}")

        invoice.totInvoice = sum
        invoiceRepository.save(invoice)
    }

    //  fun calculateAndUpdateTotal (detail : DetailModel){
    //    val totalCalculated = detailRepository.sumTotal(detail.idnInvoice?: 0L)
    //  val invoiceResponse = invoiceRepository.findById(detail.idnInvoice?: 0L).get()
    //invoiceResponse.apply {
    //  totInvoice=totalCalculated
    // }
    // invoiceRepository.save(invoiceResponse)
    //}


///////////////////////////








    fun update(detail: DetailModel): DetailModel {
        if (detail.idd == null) {
            //  throw ValidationException("ID no proporcionada para actualizar")
        }
        validateDetail(detail)
        return detailRepository.save(detail)
    }
    fun updateDetails(detail: DetailModel): DetailModel {
        if (detail.idd == null) {
             throw ValidationException("ID no proporcionada para actualizar detalles")
        }
        // Actualizar detalles específicos del detail si es necesario
        // Puedes implementar la lógica según tus requisitos
        // Por ejemplo: detail.qualification = newQualification
        return detailRepository.save(detail)
    }


    fun listById(idd: Long): DetailModel {
        return (detailRepository.findById(idd)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle no encontrado")) as DetailModel
    }


    fun delete(idd: Long) {
        val existingDetail = detailRepository.findById(idd)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice no encontrado") }

        detailRepository.delete(existingDetail)
    }


    private fun validateDetail(detail: DetailModel) {
        // Implementa las validaciones necesarias para el Detail aquí
        // Por ejemplo: asegurarse de que los campos obligatorios no estén vacíos
        // if (detail.quantity == null || ...)
    }
}

/* try {
           val savedDetail = detailRepository.save(detail)
           val productId = savedDetail.idpProduct

           // Obtener el producto asociado al detalle guardado
           val product = productRepository.findById(productId)

           if (product != null) {
               // Actualizar el stock del producto
               val newStock = (product.stockProduct ?: 0) - (savedDetail.quaDetail ?: 0)
               if (newStock >= 0) { // Verificar que el stock no sea negativo
                   product.stockProduct = newStock
                   productRepository.save(product)
               } else {
                   throw ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay suficiente stock disponible")
               }
           } else {
               throw ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")
           }

           return savedDetail
       } catch (ex: Exception) {
           throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
       }

       */