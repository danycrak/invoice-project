package com.example.vistas.service

import com.example.vistas.model.Detail
import com.example.vistas.model.Invoice
import com.example.vistas.repository.DetailRepository
import com.example.vistas.repository.InvoiceRepository
import com.example.vistas.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository
    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    //clase service

   @Transactional

    fun save(detail: Detail): Detail {
       try{


           //El objeto debe estar verificado.
           detail.quantity?.toString()?.takeIf { it.trim().isNotEmpty() }
                   ?: throw Exception("Cantidad no debe ser vacio")
           detail.price?.toString()?.takeIf { it.trim().isNotEmpty() }
                   ?: throw Exception("Precio no debe ser vacio")


        val product = productRepository.findById(detail.productId)
                ?: throw Exception("Id de Producto no existe")

           val detailSave = detailRepository.save(detail)
           product.apply {
               stock = stock?.minus(detail.quantity!!)
           }

///////
  val listDetail = detailRepository.findByInvoiceId(detail.invoiceId)
           var sum = 0.0

           // Sumar  detalles
           listDetail.map {item->
              sum += item.price?.times(item.quantity!!)!!
           }


           val invoice = invoiceRepository.findById(detail.invoiceId)

           invoice?.apply{
               total = sum
           }
            invoiceRepository.save(invoice!!)







///////
        productRepository.save(product)
       return detailSave
//     return detailsRepository.save(details)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }

    //clase service

    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")

            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    //clase service

    fun updateDescription (detail:Detail): Detail {
        try{
            val response = detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                /* description=cliente.description*/
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
//Clase Service

    fun listById (id:Long?):Detail?{
        return detailRepository.findById(id)
    }

    //clase service

    fun delete (id: Long?):Boolean?{
        try{
            val response = detailRepository.findById(id)
                    ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


}