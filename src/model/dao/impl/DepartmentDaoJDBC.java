package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.Database;
import database.DbException;
import database.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
  private Connection conn;

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    Department department = new Department();
    department.setId(rs.getInt("Id"));
    department.setName(rs.getString("Name"));
    return department;
  }

  @Override
  public void deleteById(Integer id) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

      st.setInt(1, id);

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbIntegrityException(e.getMessage());
    } finally {
      Database.closeStatement(st);
    }
  }

  @Override
  public List<Department> findAll() {
    PreparedStatement st = null;
    ResultSet rs = null;
    List<Department> departments = new ArrayList<>();

    try {
      st = conn.prepareStatement("SELECT * FROM department;");
      rs = st.executeQuery();
      while (rs.next()) {
        departments.add(instantiateDepartment(rs));
      }

      return departments;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      Database.closeStatement(st);
      Database.closeResultSet(rs);
    }
  }

  @Override
  public Department findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement("SELECT * FROM department where id = ?;");
      st.setInt(1, id);
      rs = st.executeQuery();
      if (rs.next()) {
        return instantiateDepartment(rs);
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      Database.closeStatement(st);
      Database.closeResultSet(rs);
    }
  }

  @Override
  public void insert(Department department) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "INSERT INTO department " +
              "(Name) " +
              "VALUES " +
              "(?)",
          Statement.RETURN_GENERATED_KEYS);

      st.setString(1, department.getName());

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt(1);
          department.setId(id);
        }
      } else {
        throw new DbException("Unexpected error! No rows affected!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      Database.closeStatement(st);
    }

  }

  @Override
  public void update(Department department) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "UPDATE department " +
              "SET Name = ? " +
              "WHERE Id = ?");

      st.setString(1, department.getName());
      st.setInt(2, department.getId());

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      Database.closeStatement(st);
    }

  }

  public DepartmentDaoJDBC(Connection conn) {
    this.conn = conn;
  }
}
