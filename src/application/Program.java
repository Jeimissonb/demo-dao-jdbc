package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===| TESTING ONE: FINDBYID - SELLER |=== ");
		Seller seller = sellerDao.findById(2);
		
		System.out.println(seller);
		System.out.println();
		System.out.println("===| TESTING TWO: FINDBYDEPARTMENT - SELLER |=== ");
		Department dep = new Department(3, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		
		for (Seller x : list) {
			System.out.println(x);
		}
		System.out.println();
		System.out.println("===| TESTING THREE: FINDALL - SELLER |=== ");
		List<Seller> listAll = sellerDao.findAll();
		for (Seller x : listAll) {
			System.out.println(x);
		}

		
	
		
	}

}
