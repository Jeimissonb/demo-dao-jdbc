package application;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		
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
		
		System.out.println();
		System.out.println("===| TESTING FOUR: INSERT - SELLER |=== ");
		Seller seled = new Seller(null, "João", "João@gmail.com", new Date() , 3090.0, dep);
		sellerDao.insert(seled);
		System.out.println("Id: "+seled.getId());
		
		System.out.println();
		System.out.println("===| TESTING FIVE: UPDATE - SELLER |=== ");
		
		seller = sellerDao.findById(1);
		seller.setName("Optmus Prime");
		sellerDao.update(seller);
		


		
	
		
	}

}
