package es.spring.microservices.kotlinmicroservice.services

import es.spring.microservices.kotlinmicroservice.model.api.PersonRequest
import es.spring.microservices.kotlinmicroservice.model.service.PersonVO

interface PersonService {

    fun getPersonById(id: String): PersonVO?

    fun findPersonsByAge(age: Int): List<PersonVO>

    fun savePerson(personRequest: PersonRequest): PersonVO

}
