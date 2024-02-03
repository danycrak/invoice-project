package com.example.vistas.service

import com.example.vistas.model.InvoiceModel
import com.example.vistas.repository.InvoiceRepository
import jakarta.validation.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.transaction.annotation.Transactional;
//import javax.validation.ValidationException


@Service
class InvoiceService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list (pageable: Pageable,invoice: InvoiceModel):Page<InvoiceModel>{
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return invoiceRepository.findAll(Example.of(invoice, matcher), pageable)
    }

    fun filterTotal (value: Double) : List<InvoiceModel>? {
        return invoiceRepository.filterTotal(value)

    }
    fun filterClient(value:Long?): List<InvoiceModel>? {
        return invoiceRepository.filterClient(value)
    }


    fun save(invoice: InvoiceModel): InvoiceModel {
        validateInvoice(invoice)
        return invoiceRepository.save(invoice)
    }


    fun update(invoice: InvoiceModel): InvoiceModel {
        if (invoice.idn == null) {
            throw ValidationException("ID no proporcionada para actualizar")
        }
        validateInvoice(invoice)
        return invoiceRepository.save(invoice)
    }

    fun updateDetails(invoice: InvoiceModel): InvoiceModel {
        if (invoice.idn == null) {
                throw ValidationException("ID no proporcionada para actualizar detalles")
        }
        // Actualizar detalles específicos del invoice si es necesario
        // Puedes implementar la lógica según tus requisitos
        // Por ejemplo: invoice.detail = invoiceDetails
        return invoiceRepository.save(invoice)
    }


    fun listById(idn: Long):    InvoiceModel {
        return (invoiceRepository.findById(idn)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice no encontrado")) as InvoiceModel
    }


    fun delete(idn: Long) {
        val existingInvoice = invoiceRepository.findById(idn)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice no encontrado") }

        invoiceRepository.delete(existingInvoice)
    }

    private fun validateInvoice(invoice: InvoiceModel) {
        // Implementa las validaciones necesarias para el Invoice aquí
        // Por ejemplo: asegurarse de que los campos obligatorios no estén vacíos
        // if (invoice.codInvoice.isNullOrBlank() || ...)
    }



}
