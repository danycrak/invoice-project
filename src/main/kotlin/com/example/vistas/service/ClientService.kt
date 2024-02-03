
package com.example.vistas.model

import com.example.vistas.model.Client

import com.example.vistas.repository.ClientRepository
import jakarta.validation.ValidationException

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
//import javax.validation.ValidationException;


@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository
    fun list (pageable: Pageable,client: Client):Page<Client>{
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return clientRepository.findAll(Example.of(client, matcher), pageable)
    }

    fun save(client: Client): Client {
        validateClient(client)
        return clientRepository.save(client)
    }


    fun update(client: Client): Client {
        if (client.idc == null) {

              throw ValidationException("ID no proporcionada para actualizar")
        }
        validateClient(client)
        return clientRepository.save(client)
    }


    fun updateName(client: Client): Client {
        if (client.idc == null) {
              throw ValidationException("ID no proporcionada para actualizar el nombre")
        }
        val existingClientOptional = clientRepository.findById(client.idc!!)
        val existingClient = existingClientOptional.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")
        }

        existingClient.fulNamClient = client.fulNamClient // Actualizar solo el nombre

        return clientRepository.save(existingClient)
    }


    fun listById(idc: Long): Client {
        return (clientRepository.findById(idc)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")) as Client
    }


    fun delete(idc: Long) {
        val existingClient = clientRepository.findById(idc)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice no encontrado") }

        clientRepository.delete(existingClient)
    }


    private fun validateClient(client: Client) {
        // Realizar validaciones sobre el cliente aquí
        // Por ejemplo: asegurarse de que los campos obligatorios no estén vacíos
        if (client.nuiClient.isNullOrBlank() || client.fulNamClient.isNullOrBlank() || client.addressClient.isNullOrBlank()) {
             throw ValidationException("Campos obligatorios no pueden estar vacíos")
        }
    }
}