package com.solvd.travelAgencyProject.persistence.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Create<T> {

    Connection create(T value) throws SQLException;
}
