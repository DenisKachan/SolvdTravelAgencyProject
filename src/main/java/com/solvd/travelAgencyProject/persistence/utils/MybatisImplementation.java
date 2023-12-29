package com.solvd.travelAgencyProject.persistence.utils;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisImplementation {

    public static boolean flag = false;

    @Getter
    public static SqlSessionFactory sessionFactory;

    static {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis.config.xml")) {
            sessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
