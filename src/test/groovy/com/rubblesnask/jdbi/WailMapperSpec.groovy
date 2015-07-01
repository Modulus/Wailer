package groovy.com.rubblesnask.jdbi

import com.rubblesnask.jdbi.WailMapper
import com.rubblesnask.types.Wail
import spock.lang.Specification

import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * Created by Modulus on 01.07.2015.
 */
class WailMapperSpec extends Specification{
    def resultSet
    def wail
    def mapper
    def timestamp



    def "Maps to expected Wail"(){
        setup:
            wail = new Wail()
            mapper = new WailMapper()
            resultSet = Mock(ResultSet)

            wail.setId(10)
            wail.setMessage("Hallo alle ihopa")
            wail.setPseudonym("Raglafanten")

            timestamp = new Timestamp(System.currentTimeMillis());
            wail.setDate(timestamp.toLocalDateTime())
            wail.setDownVotes(2)
            wail.setUpVotes(5)

        when:
            resultSet.getInt("id") >> wail.getId()
            resultSet.getString("message") >> wail.getMessage()
            resultSet.getString("name") >> wail.getPseudonym()
            resultSet.getInt("upvotes") >> wail.getUpVotes()
            resultSet.getInt("downvotes") >> wail.getDownVotes()

            resultSet.getTimestamp("timestamp") >> timestamp
        then:
            def result = mapper.map(0, resultSet, null )
            result.getId() == wail.getId()
            result.getMessage() == wail.getMessage()
            result.getPseudonym() == wail.getPseudonym()
            result.getUpVotes() == wail.getUpVotes()
            result.getDownVotes() == wail.getDownVotes()
            result.getDate() == wail.getDate()




    }
}
