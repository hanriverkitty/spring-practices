package com.poscodx.guestbook3.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	PreparedStatement makeStatement(Connection connection) throws SQLException;
}
