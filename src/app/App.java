package app;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
  public static void main(String[] args) {

    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("==== Buscando Vendedor por Id");
    Seller seller = sellerDao.findById(3);

    System.out.println(seller);

    System.out.println("\n==== Buscando Vendedores pelo Departamento");
    Department department = new Department(2, null);
    List<Seller> list = sellerDao.findByDepartament(department);

    System.out.println(list);

  }
}
