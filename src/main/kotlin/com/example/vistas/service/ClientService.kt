
package com.example.vistas.model

import com.example.vistas.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list ():List<Client>{
        return clientRepository.findAll()
    }
    //clase service

    fun save(details: Client): Client {
        try{
            return clientRepository.save(details)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }

    //clase service

    fun update(cliente: Client): Client {
        try {
            clientRepository.findById(cliente.id)
                    ?: throw Exception("ID no existe")

            return clientRepository.save(cliente)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    //clase service

    fun updateDescription (client:Client): Client {
        try{
            val response = clientRepository.findById(client.id)
                    ?: throw Exception("ID no existe")
            response.apply {
      /*          description=client.description*/
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
//Clase Service

    fun listById (id:Long?):Client?{
        return clientRepository.findById(id)
    }

    //clase service

    fun delete (id: Long?):Boolean?{
        try{
            val response = clientRepository.findById(id)
                    ?: throw Exception("ID no existe")
            clientRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


}