package com.solvd.travelAgencyProject.persistence.interfaces;

@FunctionalInterface
public interface GetByName<T>{

    T get(String name);
}
