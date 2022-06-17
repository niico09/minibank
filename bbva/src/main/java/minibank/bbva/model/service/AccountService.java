package minibank.bbva.model.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Account;

@Repository("daoAccount")
public class AccountService implements DAO<Account> {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	@Resource(name = "template")
	public void setConnection(JdbcTemplate jdbc) throws SQLException {
		this.jdbc = jdbc;
	}

	@Override
	public boolean save(Account account) {

		if (account.getPrimaryOwner().equals(Boolean.TRUE)) {
			return savePrimary(account);
		} else {
			return saveSecundary(account);
		}

	}

	private boolean saveSecundary(Account account) {

		Account ac = read(String.valueOf(account.getNumber())).get();

		if (ac != null) {
			String sql = "INSERT INTO Account(Number,DniOwner,primaryOwner,CreateDate,InitialBalance,agreed, EndDate,TypeMoney, ActualBalance)"
					+ " VALUES (?,?,?,?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbc.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, ac.getNumber());
				ps.setLong(2, account.getDniOwner());
				ps.setBoolean(3, account.getPrimaryOwner());
				ps.setDate(4, (java.sql.Date) new Date());
				ps.setDouble(5, ac.getInitalBalance());
				ps.setString(6, ac.getAgreed());
				ps.setDate(7, (java.sql.Date) new Date());
				ps.setString(8, ac.getTypeMoney());
				ps.setDouble(9, ac.getActualBalance());
				return ps;
			}, keyHolder);
			account.setId(keyHolder.getKey().longValue());
			return account.getId() != 0;
		} else {
			return Boolean.FALSE;
		}

	}

	private boolean savePrimary(Account account) {
		String sql = "INSERT INTO Account(Number,DniOwner,primaryOwner,CreateDate,InitialBalance,agreed, EndDate,TypeMoney, ActualBalance)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, account.getNumber());
			ps.setLong(2, account.getDniOwner());
			ps.setBoolean(3, account.getPrimaryOwner());
			ps.setDate(4, (java.sql.Date) new Date());
			ps.setDouble(5, account.getInitalBalance());
			ps.setString(6, account.getAgreed());
			ps.setDate(7, (java.sql.Date) new Date());
			ps.setString(8, account.getTypeMoney());
			ps.setDouble(9, account.getActualBalance());
			return ps;
		}, keyHolder);
		account.setId(keyHolder.getKey().longValue());
		return account.getId() != 0;

	}

	@Override
	public Optional<Account> read(String number) {
		String sql = "SELECT * FROM Account WHERE Number = ?";
		Account a = jdbc.queryForObject(sql, this::mapearFilaAArtista, number);
		return Optional.ofNullable(a);
	}

	private Account mapearFilaAArtista(ResultSet rs, int rowNum) throws SQLException {

		var account = new Account();
		account.setActualBalance(rs.getDouble("ActualBalance"));
		account.setAgreed(rs.getString("agreed"));
		account.setCreateDate(rs.getDate("CreateDate"));
		account.setDniOwner(rs.getLong("DniOwner"));
		account.setEndDate(rs.getDate("EndDate"));
		account.setId(rs.getLong("id"));
		account.setInitalBalance(rs.getDouble("InitialBalance"));
		account.setNumber(rs.getLong("Number"));
		account.setPrimaryOwner(rs.getBoolean("primaryOwner"));
		account.setTypeMoney(rs.getString("TypeMoney"));

		return account;
	}

	@Override
	public void delete(Account t) {
		String sql = "DELETE FROM Account WHERE Number = ?";
		Object[] args = new Object[] { t.getNumber() };
		jdbc.update(sql, args);
	}

	@Override
	public Collection<Account> readAll() {
		String sql = "SELECT * FROM Account";
		return jdbc.query(sql, this::mapearFilaAArtista);
	}

	@Override
	public boolean update(Account account) {
		String sql = "UPDATE Account SET ActualBalance = ? WHERE Number = ?";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, account.getActualBalance());
			ps.setLong(2, account.getNumber());
			return ps;
		}, keyHolder);
		account.setId(keyHolder.getKey().longValue());
		return account.getId() != 0;
	}

	@Override
	public List<Account> readSpecific(String id) {
		String sql = "SELECT * FROM Account WHERE Number = ? ";
		var listAccount = new ArrayList<Account>();

		List<Map<String, Object>> rows = jdbc.queryForList(sql, id);
		for (Map row : rows) {
			var obj = new Account();
			obj.setActualBalance((double) row.get("ActualBalance"));
			obj.setAgreed((String) row.get("agreed"));
			obj.setCreateDate((Date) row.get("CreateDate"));
			obj.setDniOwner((long) row.get("DniOwner"));
			obj.setEndDate((Date) row.get("EndDate"));
			obj.setId((long) row.get("id"));
			obj.setInitalBalance((double) row.get("InitialBalance"));
			obj.setNumber((long) row.get("Number"));
			obj.setPrimaryOwner((boolean) row.get("primaryOwner"));
			obj.setTypeMoney((String) row.get("TypeMoney"));
			listAccount.add(obj);
		}
		return listAccount;
	}

	@Override
	public Optional<Account> getSpecialParam(String id) {
		throw new MethodNotFoundException();
	}

}
