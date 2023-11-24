package com.example.vistas.model


/*import jakarta.validation.Valid*/
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/client")   //endpoint
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list ():List <Client>{
        return clientService.list()
    }

    @PostMapping
    fun save (@RequestBody /*@Valid*/ client:Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.save(client), HttpStatus.OK)
    }
    //clase controller
    @PutMapping
    fun update (@RequestBody client:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.update(client), HttpStatus.OK)
    }
    //clase  controller
    @PatchMapping
    fun updateDescription (@RequestBody client:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.updateDescription(client), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(clientService.listById (id), HttpStatus.OK)

    }

    //clase  controller
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return clientService.delete(id)
    }

}