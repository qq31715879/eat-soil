package com.hug.dao.mapper;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

import com.hug.dao.mybaits.MySqlSessionFactory;
import com.hug.dao.orm.CJBaseDAO;

public class TableToEntityDAO extends CJBaseDAO<Object> {

	public TableToEntityDAO(Connection connection, String... tableName) {
		super(connection, tableName);
	}

	public static void main(String[] args) throws Exception {
		String dataName = "carzone_order";
		String tableName = "t_order";
		dataName = "carzone0706";
		tableName = "t_sale_intent_price_apply_detail";
		SqlSession sqlSession = MySqlSessionFactory.getSqlSession("erp");
		Connection connection = sqlSession.getConnection();
		TableToEntityDAO dao = new TableToEntityDAO(connection, tableName);
		dao.selectColumnName(dataName);
		connection.close();
		sqlSession.close();
	}
}
