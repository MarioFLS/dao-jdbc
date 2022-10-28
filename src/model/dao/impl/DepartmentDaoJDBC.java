package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.Database;
import database.DbException;
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void insert(Department department) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(Department department) {
    // TODO Auto-generated method stub

  }

  public DepartmentDaoJDBC(Connection conn) {
    this.conn = conn;
  }
}
