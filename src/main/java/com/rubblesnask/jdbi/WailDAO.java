package com.rubblesnask.jdbi;

import com.subblesnask.types.Wail;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.Iterator;

/**
 * Created by Modulus on 26.03.2015.
 */
//@RegisterMapper(WailMapper.class)
public interface WailDAO {

    @SqlUpdate("create table wails (id int primary key not null, name varchar(255), message text not null, timestamp timestamp not null, is_offensive boolean default false)")
    void createWailsTable();


    @SqlUpdate("insert into wails(name, message, timestamp)  values(:name, :message, :timestamp)")
    void insert(@Bind("name")String name, @Bind("message")String message, @Bind("timestamp")Timestamp timestamp);

    @Mapper(WailMapper.class)
    @SqlQuery("select * from wails where id = :id")
    Wail findWailById(@Bind("id")int id);

    @Mapper(WailMapper.class)
    @SqlQuery("select * from wails limit 50")
    Iterator<WailDAO> findAll();
}
