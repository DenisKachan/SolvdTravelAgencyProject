package com.solvd.travelAgencyProject.persistence.interfaces;

import java.sql.SQLException;

public interface Create<T> {

   void create(T value) throws SQLException;
}
