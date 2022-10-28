package app;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
  public static void main(String[] args) {

    // SellerDao sellerDao = DaoFactory.createSellerDao();

    // System.out.println("==== Buscando Vendedor por Id");
    // Seller seller = sellerDao.findById(3);

    // System.out.println(seller);

    // System.out.println("\n==== Buscando Vendedores pelo Departamento");
    // Department department = new Department(2, null);
    // List<Seller> list = sellerDao.findByDepartament(department);

    // for (Seller s : list) {
    // System.out.println(s);
    // }

    // System.out.println("\n==== Buscando todos os vendedores");
    // List<Seller> listAll = sellerDao.findAll();

    // for (Seller s : listAll) {
    // System.out.println(s);
    // }

    // System.out.println("\n==== Inserir novo Vendedor");
    // Seller newSeller = new Seller(null, "Fernando", "email@emailTop.com", new
    // Date(), 4000d, department);
    // sellerDao.insert(newSeller);
    // System.out.println("Inserido! Id do novo vendedor = " + newSeller.getId());

    // System.out.println("\n=== Atualizar Vendedor");
    // seller = sellerDao.findById(1);
    // seller.setName("Martha Waine");
    // sellerDao.update(seller);
    // System.out.println("Atualizado com sucesso");

    // System.out.println("\n=== Deletar um Vendedor");
    // sellerDao.deleteById(2);
    // System.out.println("Deletado com sucesso");

    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
    System.out.println("\n=== Pegando todos os Departamentos");
    List<Department> listDepartaments = departmentDao.findAll();

    for (Department department : listDepartaments) {
      System.out.println(department);
    }
  }
}
