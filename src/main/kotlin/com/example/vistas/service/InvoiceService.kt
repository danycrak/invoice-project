package com.example.vistas.service

import com.example.vistas.model.Invoice
import com.example.vistas.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class InvoiceService {

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }
    //clase service

    fun save(details: Invoice): Invoice {
        try {
            return invoiceRepository.save(details)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    //clase service

    fun update(invoice: Invoice): Invoice {
        try {
            invoiceRepository.findById(invoice.id)
                    ?: throw Exception("ID no existe")

            return invoiceRepository.save(invoice)
        } catch (ex: Exception) {


            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }




    }

    fun filterTotal(value:Double?): List<Invoice>? {
        return invoiceRepository.filterTotal(value)
    }

    //clase service

    fun updateDescription (invoice: Invoice): Invoice {
        try{
            val response = invoiceRepository.findById(invoice.id)
                    ?: throw Exception("ID no existe")
            response.apply {
              //  precio=invoice.precio
            }
            return invoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
//Clase Service

    fun listById (id:Long?): Invoice?{
        return invoiceRepository.findById(id)
    }

    //clase service

    fun delete (id: Long?):Boolean?{
        try{
            val response = invoiceRepository.findById(id)
                    ?: throw Exception("ID no existe")
            invoiceRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}