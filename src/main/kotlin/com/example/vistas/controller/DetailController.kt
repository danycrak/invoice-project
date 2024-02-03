package com.example.vistas.controller



import com.example.vistas.model.DetailModel
import com.example.vistas.service.DetailService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController   //Define una responsabilidad a un componente
@RequestMapping("/detail")   //endpoint
class DetailController {
    @Autowired
    lateinit var detailService: DetailService

    @GetMapping
    fun list (detail: DetailModel, pageable: Pageable):ResponseEntity<*>{
        val response= detailService.list(pageable,detail)
        return ResponseEntity(response, HttpStatus.OK)
    }

//@RequestParam searchValue:String

    @PostMapping
    fun save(@RequestBody @Valid detail: DetailModel): ResponseEntity<DetailModel> {
        return ResponseEntity(detailService.save(detail), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody detail: DetailModel): ResponseEntity<DetailModel> {
        return ResponseEntity(detailService.update(detail), HttpStatus.OK)
    }

    @PatchMapping
    fun updateDetails(@RequestBody detail: DetailModel): ResponseEntity<DetailModel> {
        return ResponseEntity(detailService.updateDetails(detail), HttpStatus.OK)
    }

    @GetMapping("/{idd}")
    fun listById(@PathVariable("idd") idd: Long): ResponseEntity<*> {
        return ResponseEntity(detailService.listById(idd), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{idd}")
    fun delete(@PathVariable("idd") idd: Long): Boolean? {
        detailService.delete(idd)
        return true

    }
}