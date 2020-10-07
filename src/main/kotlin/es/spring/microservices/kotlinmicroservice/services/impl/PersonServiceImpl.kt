package es.spring.microservices.kotlinmicroservice.services.impl

import es.spring.microservices.kotlinmicroservice.model.api.PersonRequest
import es.spring.microservices.kotlinmicroservice.model.repository.Person
import es.spring.microservices.kotlinmicroservice.model.service.PersonVO
import es.spring.microservices.kotlinmicroservice.repositories.PersonRepository
import es.spring.microservices.kotlinmicroservice.services.PersonService
import ma.glasnost.orika.MapperFacade
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl : PersonService {

    val logger: Logger = LoggerFactory.getLogger(PersonServiceImpl::class.java)

    private final var personRepository : PersonRepository
    private final var defaultMapper : MapperFacade

    @Autowired
    constructor(personRepository : PersonRepository, defaultMapper : MapperFacade) {
        this.personRepository = personRepository
        this.defaultMapper = defaultMapper
    }

    override fun getPersonById(id: String): PersonVO? {
        return defaultMapper.map(personRepository.findById(id).orElseGet { null }, PersonVO::class.java)
    }

    override fun findPersonsByAge(age: Int): List<PersonVO> {
        return defaultMapper.mapAsList(personRepository.findByAge(age), PersonVO::class.java)
    }

    override fun savePerson(personRequest: PersonRequest): PersonVO {
        val personSaved = personRepository.save(defaultMapper.map(personRequest, Person::class.java))
        logger.info("Person saved: $personSaved")
        return defaultMapper.map(personSaved, PersonVO::class.java)

    }

}
