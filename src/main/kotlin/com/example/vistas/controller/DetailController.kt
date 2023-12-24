package com.example.vistas.controller

import com.example.vistas.model.Detail
import com.example.vistas.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/detail")   //endpoint
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])

class DetailController {

    @Autowired
    lateinit var detailService: DetailService

    @GetMapping
    fun list ():List <Detail>{
        return detailService.list()


    }



    @PostMapping
    fun save (@RequestBody detail:Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.save(detail), HttpStatus.OK)
    }


    //clase controller
    @PutMapping
    fun update (@RequestBody detail:Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.update(detail), HttpStatus.OK)

    }



    //clase  controller
    @PatchMapping
    fun updateDescription (@RequestBody detail:Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.updateDescription(detail), HttpStatus.OK)


    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(detailService.listById (id), HttpStatus.OK)



    }

    //clase  controller
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return detailService.delete(id)
    }




}