package com.example.vistas.model


/*import jakarta.validation.Valid*/
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController   //Define una responsabilidad a un componente
@RequestMapping("/client")   //endpoint
class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list(client: com.example.vistas.model.Client, pageable: Pageable):ResponseEntity<*>  {
        val response= clientService.list(pageable,client)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @GetMapping("/{idc}")
    fun listById(@PathVariable("idc") idc: Long): ResponseEntity<*> {
        return ResponseEntity(clientService.listById(idc), HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody @Valid client: com.example.vistas.model.Client): ResponseEntity<com.example.vistas.model.Client> {
        return ResponseEntity(clientService.save(client), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody client: com.example.vistas.model.Client): ResponseEntity<com.example.vistas.model.Client> {
        return ResponseEntity(clientService.update(client), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody client: com.example.vistas.model.Client): ResponseEntity<com.example.vistas.model.Client> {
        return ResponseEntity(clientService.updateName(client), HttpStatus.OK)
    }




    @DeleteMapping("/delete/{idc}")
    fun delete(@PathVariable("idc") idc: Long): Boolean? {
        clientService.delete(idc)
        return true
    }
}