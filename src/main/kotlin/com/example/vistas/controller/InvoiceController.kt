package com.example.vistas.controller

import com.example.vistas.model.Invoice
import com.example.vistas.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/invoice")   //endpoint

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list ():List <Invoice>{
        return invoiceService.list()
    }

    @GetMapping("/filter-total/{value}")
    fun listFilterTotal(@PathVariable("value") value:Double):ResponseEntity<*>{
        return  ResponseEntity(invoiceService.filterTotal(value),HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody invoice:Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.save(invoice), HttpStatus.OK)
    }
    //clase controller
    @PutMapping
    fun update (@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.OK)
    }
    //clase  controller
    @PatchMapping
    fun updateDescription (@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.updateDescription(invoice), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(invoiceService.listById (id), HttpStatus.OK)

    }

    //clase  controller
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return invoiceService.delete(id)
    }

}