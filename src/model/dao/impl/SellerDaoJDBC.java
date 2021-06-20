package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			pst = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Department dp = new Department();
				dp.setId(rs.getInt(6));
				dp.setName(rs.getString(7));
				
				Seller seller = new Seller();
				seller.setId(rs.getInt(1));
				seller.setName(rs.getString(2));
				seller.setEmail(rs.getString(3));
				seller.setBirthDate(rs.getDate(4));
				seller.setBaseSalary(rs.getDouble(5));
				seller.setDepartment(dp);
				
				return seller;
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DbException (e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
