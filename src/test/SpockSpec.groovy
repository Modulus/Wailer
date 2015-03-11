import spock.lang.Specification

/**
 * Created by Modulus on 11.03.2015.
 */

class SpockSpec extends Specification {
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }
}