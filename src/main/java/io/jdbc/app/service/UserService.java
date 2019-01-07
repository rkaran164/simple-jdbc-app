package io.jdbc.app.service;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import io.jdbc.app.entity.User;
import io.jdbc.app.response.BaseResponse;
import io.jdbc.app.utils.DBConstants;

@Service
public class UserService {
	
	@Autowired
	JdbcTemplate db;
	
	@Autowired
	DataSource dataSource;
	
	public Long insertUser(User user ) throws SQLException {
		String sql = "insert into users(name,email) values(?,?)";
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		db.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getName());
				statement.setString(2, user.getEmail());
				return statement;
			}
		},holder);
		return holder.getKey().longValue();
	}
	
	
	
	//get all user in the database
	public List<User> getAllUsers(){
		String sql = "select *from users";
		return db.query(sql, new ResultSetExtractor<List<User>>() {
			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> users = new ArrayList<>();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt(DBConstants.ID));
					user.setName(rs.getString(DBConstants.NAME));
					user.setEmail(rs.getString(DBConstants.EMAIL));
					users.add(user);
				}
				return users;
			}
		});
	}


	
	//get user
	public List<User> getUser(String email) throws SQLException {
		String sql = "select *from users where email = ? ";
		List<User> users = new ArrayList<User>();
		Connection conn = dataSource.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet res = statement.executeQuery();
		while(res.next()) {
			User user = new User();
			user.setId(res.getInt(DBConstants.ID));
			user.setName(res.getString(DBConstants.NAME));
			user.setEmail(res.getString(DBConstants.EMAIL));
			users.add(user);
		}
		return users;
	}
	
	
	//remove user
	public BaseResponse removeUser(int id) throws SQLException {
		BaseResponse response = new BaseResponse();
		response.setMsg("No user exits with this id="+id);
		response.setSuccess(false);
		String sql = "delete from users where id=?";
		Connection conn = dataSource.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		boolean success = statement.execute();
		if(success) {
			response.setMsg("User Deleted Successfully");
			response.setSuccess(success);
		}
		return response;
	}
	
	
	private ResultSetExtractor<List<User>> rse = new ResultSetExtractor<List<User>>() {
		@Override
		public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<User> users = new ArrayList<>();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(DBConstants.ID));
				user.setName(rs.getString(DBConstants.NAME));
				user.setEmail(rs.getString(DBConstants.EMAIL));
				users.add(user);
			}
			return users;
		}
	};
	
	
	private PreparedStatementSetter getPss(String email) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
			}
		};
	}
	 
}
