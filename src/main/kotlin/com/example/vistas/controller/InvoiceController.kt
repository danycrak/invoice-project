package com.example.vistas.controller


import com.example.vistas.model.InvoiceModel
import com.example.vistas.service.InvoiceService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController   //Define una responsabilidad a un componente
@RequestMapping("/invoice")   //endpoint
class InvoiceController {
    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list (invoice: InvoiceModel, pageable: Pageable):ResponseEntity<*>{
        val response= invoiceService.list(pageable,invoice)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @GetMapping("/filter-total/{value}")
    fun listFilterTotal(@PathVariable ("value") value: Double): ResponseEntity<*>{
        return  ResponseEntity(invoiceService.filterTotal(value), HttpStatus.OK)

    }
    @GetMapping("/filter-client/{value}")
    fun listTotals (@PathVariable("value") value: Long ):   ResponseEntity<*>{
        return ResponseEntity(invoiceService.filterClient(value), HttpStatus.OK)
    }

//@RequestParam searchValue:String

    @PostMapping
    fun save(@RequestBody @Valid invoice: InvoiceModel): ResponseEntity<InvoiceModel> {
        return ResponseEntity(invoiceService.save(invoice), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody invoice: InvoiceModel): ResponseEntity<InvoiceModel> {
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.OK)
    }

    @PatchMapping
    fun updateDetails(@RequestBody invoice: InvoiceModel): ResponseEntity<InvoiceModel> {
        return ResponseEntity(invoiceService.updateDetails(invoice), HttpStatus.OK)
    }

    @GetMapping("/{idn}")
    fun listById(@PathVariable("idn") idn: Long): ResponseEntity<*> {
        return ResponseEntity(invoiceService.listById(idn), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{idn}")
    fun delete(@PathVariable("idn") idn: Long): Boolean? {
        invoiceService.delete(idn)
        return true
    }
}