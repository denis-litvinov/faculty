package org.example.dao;

import java.sql.ResultSet;

public interface EntityMapper <T> {
    T mapRow(ResultSet rs);
}
