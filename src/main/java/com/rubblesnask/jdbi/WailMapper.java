package com.rubblesnask.jdbi;

import com.rubblesnask.types.Wail;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by Modulus on 26.03.2015.
 */
public class WailMapper implements ResultSetMapper<Wail> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WailMapper.class);

    public Wail map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        LOGGER.warn("Mapping wail");
        Wail wail = new Wail();
        int id = resultSet.getInt("id");
        String message = resultSet.getString("message");
        String name = resultSet.getString("name");
        Integer upVotes = resultSet.getInt("upvotes");
        int downVotes = resultSet.getInt("downvotes");
        LocalDateTime date = resultSet.getTimestamp("timestamp").toLocalDateTime();

        wail.setId(id);
        wail.setMessage(message);
        wail.setDate(date);
        wail.setUpVotes(upVotes);
        wail.setDownVotes(downVotes);
        wail.setPseudonym(name);

        LOGGER.warn("Finished mapping wail");

        return wail;
    }
}
