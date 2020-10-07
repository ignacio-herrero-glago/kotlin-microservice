package es.spring.microservices.kotlinmicroservice.controllers

import es.spring.microservices.kotlinmicroservice.configurations.API_BASE_PATH
import es.spring.microservices.kotlinmicroservice.model.api.PersonRequest
import es.spring.microservices.kotlinmicroservice.model.api.PersonVO
import es.spring.microservices.kotlinmicroservice.services.PersonService
import io.swagger.annotations.*
import ma.glasnost.orika.MapperFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

const val KOTLIN_MICROSERVICE_PATH = "/"
const val KOTLIN_MICROSERVICE_TAG = "Kotlin Microservice"

@RestController
@RequestMapping(value = [API_BASE_PATH + KOTLIN_MICROSERVICE_PATH])
@Api(tags = [KOTLIN_MICROSERVICE_TAG])
class KotlinController {

    private final var personService : PersonService
    private final var defaultMapper : MapperFacade

    @Autowired
    constructor(personService : PersonService, defaultMapper : MapperFacade) {
        this.personService = personService
        this.defaultMapper = defaultMapper
    }

    /**
     * Gets a person
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/person-by-id"])
    @ApiOperation(value = "Get a person")
    @ApiResponses(
            ApiResponse(code = HttpServletResponse.SC_OK, message = "ok"),
            ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "error")
    )
    fun getPersonById(@ApiParam(value = "person id", required = true) @RequestParam(required = true) id: String) : ResponseEntity<PersonVO?> {
        val personVO: PersonVO? = defaultMapper.map(personService.getPersonById(id), PersonVO::class.java)

        if (personVO != null)
            return ResponseEntity.ok(personVO)

        return ResponseEntity.ok().build()
    }

    /**
     * Finds persons by age
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/persons-by-age"])
    @ApiOperation(value = "Finds persons by age")
    @ApiResponses(
            ApiResponse(code = HttpServletResponse.SC_OK, message = "ok"),
            ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "error")
    )
    fun findPersonsByAge(@ApiParam(value = "person age", required = true) @RequestParam(required = true) age: Int) : ResponseEntity<List<PersonVO>> {
        val personsList : List<PersonVO> = defaultMapper.mapAsList(personService.findPersonsByAge(age), PersonVO::class.java)
        return ResponseEntity.ok(personsList)
    }

    /**
     * Saves a person
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], value = ["/save-person"])
    @ApiOperation(value = "Saves a person")
    @ApiResponses(
            ApiResponse(code = HttpServletResponse.SC_OK, message = "ok"),
            ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "error")
    )
    fun savePerson(@ApiParam(value = "person data", required = true) @RequestBody personRequest: PersonRequest) : ResponseEntity<PersonVO> {
        val person : PersonVO = defaultMapper.map(personService.savePerson(personRequest), PersonVO::class.java)
        return ResponseEntity.ok(person)
    }

}