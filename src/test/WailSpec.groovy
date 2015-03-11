import com.subblesnask.types.Wail
import spock.lang.Specification
/**
 * Created by Modulus on 11.03.2015.
 */

class WailSpec extends Specification {


    def "new element not valid"(){
        when:
            wail = new Wail()

        then:
            wail.message.isEmpty()

    }

}