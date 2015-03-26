package com.rubblesnask.jdbi;

import com.subblesnask.types.Wail;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by Modulus on 26.03.2015.
 */
public class WailMapper implements ResultSetMapper<Wail> {
    public Wail map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Wail wail = new Wail();
        Integer id = resultSet.getInt("id");
        String message = resultSet.getString("messsage");
        String name = resultSet.getString("name");
        LocalDateTime date = resultSet.getTimestamp("timestamp").toLocalDateTime();
        return wail;
    }
}
