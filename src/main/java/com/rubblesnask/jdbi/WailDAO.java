package com.rubblesnask.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Modulus on 26.03.2015.
 */
public interface WailDAO {

    @SqlUpdate("create table wails (id int primary key not null, name varchar(255), message text not null, timestamp timestamp not null, is_offensive boolean default false)")
    void createWailsTable();


    @SqlUpdate("insert into wails(id, name, message, timestamp values(:id, :name, :message, :timestamp)")
    void insert(@Bind("id")int id, @Bind("name")String name, @Bind("message")String message, @Bind("timestamp")LocalDate timestamp);

    @SqlQuery("select message from wails where id = :id")
    WailDAO findWailById(@Bind("id")int id);

    List<WailDAO> findAll();
}
