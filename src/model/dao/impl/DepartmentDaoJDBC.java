package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	private Connection conn;
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO department "
					+ "(Name) "
					+ "VALUES "
					+ "(?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getName());
			
			int rows = pst.executeUpdate();
			
			if (rows > 0) {
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					System.out.println("SUCESS id: "+ obj.getId());
				}
				

			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());
			
			pst.executeUpdate();
			
		//	int rows = pst.executeUpdate();

		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("Delete from department where id = ?");
			pst.setInt(1, id);
			
			int rows = pst.executeUpdate();
			
			if (rows == 0) {
				throw new SQLException("ID DOESNT EXISTS");
			}
			
			System.out.println("Sucess!");
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public Department findById(Integer id) {
		conn = DB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT * from department WHERE department.Id = ?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				Department dep = instantiatingDep(rs);
				return dep;
			}
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
		
	}

	private Department instantiatingDep(ResultSet rs) throws SQLException {
		Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
		return dep;
	}
	
	

	@Override
	public List<Department> findAll() {
		conn = DB.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from department ORDER BY Name");
			
			List <Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department dep = instantiatingDep(rs);
				list.add(dep);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		
	}

}
