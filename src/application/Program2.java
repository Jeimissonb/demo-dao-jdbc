package application;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	public static void main (String [] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n=== Find by id ===");
		Department dep = depDao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n=== Find all ===");
		List <Department> list = depDao.findAll();
		for (Department x : list) {
			System.out.println(x);
		}
		
		/* System.out.println("\n=== Insert ===");
		dep = new Department(null, "Jogos");
		depDao.insert(dep);  */
		
		System.out.println("\n=== Update ===");
		Department deper = depDao.findById(1);
		deper.setName("Contabilidade");
		depDao.update(deper);

		System.out.println("\n=== Delete ===");
		System.out.print("Type a id number to be deleted: ");
		int id = sc.nextInt();
		depDao.deleteById(id);
		
		
		
		
		
		sc.close();
	}
}
