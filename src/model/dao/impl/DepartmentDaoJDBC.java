package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
  private Connection conn;

  @Override
  public void deleteById(Integer id) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Department> findAll() {
    // TODO Auto-generated method stub
    return null;
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
