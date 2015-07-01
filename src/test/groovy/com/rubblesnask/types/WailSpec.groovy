package groovy.com.rubblesnask.types

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.rubblesnask.types.Wail
import spock.lang.Specification

import javax.validation.Validation
import java.time.LocalDateTime

import static io.dropwizard.testing.FixtureHelpers.fixture
/**
 * Created by Modulus on 11.03.2015.
 */

class WailSpec extends Specification {
    def wail;
    def validator;

    def setup(){
        this.wail = new Wail()
        def validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    def "new element fields null"(){
        expect: this.wail.message == null
        and: this.wail.date != null
    }

    def "empty message gives three violations"(){
        when:
            wail.setMessage("")
        then:
            def violations = validator.validate(wail)
        violations.size() == 3
    }

    def "message is less than required length, gives validation errors"(){
        given:
            wail = new Wail()
        when:
            wail.setMessage("to short")
        then:
            def violations = this.validator.validate(wail)
            violations.size() == 2
    }

    def "message is larger than max required length, gives validation error"(){
        when: "Message is way to long"
            wail.setMessage("Gibberish"*200)
        then: "Validation fails"
            def violations = validator.validate(wail)
            violations.size() == 2
    }

    def "message can be set to null, will still not validate, but possible"(){
        when:
            wail.setMessage(null)
        then:
            notThrown(NullPointerException)
    }

    def "complex test validation"(){
        expect:
            wail.setMessage(message)
            wail.setPseudonym(name)

            def violations = validator.validate(wail)
            size == violations.size()
        where:
            message     |   name    || size
            "to"*200    | "long"*40 || 3
            ""          | "JohnDoe" || 3
    }

    def "json serialization and deserialization works"(){
        given:
            def final mapper = new ObjectMapper(new JsonFactory())
            wail = new Wail()
            //wail.getDate() >> LocalDate.of(2014, 01, 04)
            wail.setMessage("I farted in the elevator today")
            wail.setPseudonym("Jane")
            wail.setDate(LocalDateTime.of(2014, 01, 04, 4,55))
        expect:
            def expected = mapper.readValue(fixture("fixtures/wail.json"), Wail.class)
            expected == wail

    }

}