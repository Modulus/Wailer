import com.subblesnask.types.Wail
import spock.lang.Specification

import javax.validation.Validation

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
        when:
            wail.setMessage("Gibberish"*200)
        then:
            def violations = validator.validate(wail)
            violations.size() == 1
    }

}