package app;

import java.util.Date;

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
  }
}
