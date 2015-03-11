import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.subblesnask.types.Wail
import spock.lang.Specification

import javax.validation.Validation
import java.time.LocalDate

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

    def "empty message gives two violations"(){
        when:
        wail.setMessage("")
        then:
        def violations = validator.validate(wail)
        violations.size() == 2
    }

    def "message is less than required length, gives validation error"(){
        given:
            wail = new Wail()
        when:
            wail.setMessage("to short")
        then:
            def violations = this.validator.validate(wail)
            violations.size() == 1
    }

    def "message is larger than max required length, gives validation error"(){
        when: "Message is way to long"
            wail.setMessage("Gibberish"*200)
        then: "Validation fails"
            def violations = validator.validate(wail)
            violations.size() == 1
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
            "to"*200    | "long"*40 || 2
            ""          | "JohnDoe" || 2
    }

    def "json serialization works"(){
        given:
            def final mapper = new ObjectMapper(new JsonFactory());
            wail = new Wail()
            wail.getDate() >> LocalDate.of(2014, 01, 04)
            wail.setMessage("I farted in the elevator today")
            wail.setPseudonym("Jane")
            wail.setDate(LocalDate.of(2014, 01, 04))
        expect:
            mapper.writeValueAsString(wail) == fixture("fixtures/wail.json")

    }

}