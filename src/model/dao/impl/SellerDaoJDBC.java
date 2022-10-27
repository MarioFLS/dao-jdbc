package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.Database;
import database.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

  private Connection conn;

  public SellerDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void deleteById(Integer id) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Seller> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    Department department = new Department();
    department.setId(rs.getInt("DepartmentId"));
    department.setName(rs.getString("Name"));
    return department;
  }

  private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
    Seller seller = new Seller();
    seller.setId(rs.getInt("Id"));
    seller.setName(rs.getString("Name"));
    seller.setEmail(rs.getString("Email"));
    seller.setBaseSalary(rs.getDouble("BaseSalary"));
    seller.setBirthDate(rs.getDate("BirthDate"));
    seller.setDepartment(department);
    return seller;
  }

  @Override
  public Seller findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
          + "FROM seller INNER JOIN department "
          + "ON seller.DepartmentId = department.Id "
          + "WHERE seller.Id = ?");

      st.setInt(1, id);
      rs = st.executeQuery();
      if (rs.next()) {
        Department department = instantiateDepartment(rs);
        department.setId(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepName"));
        Seller seller = instantiateSeller(rs, department);
        return seller;
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
  public void insert(Seller seller) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(Seller seller) {
    // TODO Auto-generated method stub

  }

}
