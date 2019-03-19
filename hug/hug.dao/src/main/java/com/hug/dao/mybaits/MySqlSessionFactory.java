package com.hug.dao.mybaits;

import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hug.dao.bean.User;
import com.hug.dao.mapper.UserMapper;

public class MySqlSessionFactory {

	public static SqlSession getSqlSession(String id) throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		/**
		 * SqlSessionFactoryBuilder 生命周期存在于方法内, 构建完毕则释放 SqlSessionFactory
		 * 用来创建Connection(SqlSession) == DataSource 应该是单列
		 */
		// 1.读取XML, XMLConfigBuilder, parser成Configuration(存储所有属性配置)
		// 2.遍历XML的environments的节点, 根据传进来的ID查询或者找到defaul:id
		// 3.创建DataSource和TransactionFactory
		// 4.Environment == DataSource,包含 : ID, 数据源, 事务
		// 5.SqlSessionFactory是接口, 根据Configuration new出DefaultSqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, id);
		/**
		 * SqlSession == Connection
		 */
		// 1.创建Transaction
		// 2.根据ExecutorType 生成Executor
		// 2.1MyBatis 有三种Executor
		// 3.创建DefaultSqlSession 包含Configuration和Executor
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}

	public static void insert(UserMapper dao) {
		System.out.println(dao.insertUser(new User("pom", "白虎", 10)));
	}

	public static void selectMap(UserMapper dao) {
		Map<Integer, User> map = dao.selectMap();
		for (Integer key : map.keySet()) {
			System.out.println(key);
			System.out.println(map.get(key).getName());
		}
	}

	public static void main(String[] args) throws Exception {
		SqlSession session = getSqlSession("kael");
		/**
		 * DAO == Mapper 用来执行Sql 类似一条Sql
		 */
		// 1.configuration里面的MapperRegistry负责创建Mapper
		// 2.configuration里面有一个代理Mapper类Map,
		// 2.1Map->knownMappers:key->mapper.class,value代理工厂MapperProxyFactory对象
		// 2.2这里使用了伪自定义Classloader : Resource, 并且用了一个看不懂为啥要使用的工厂模式???
		// 3.mapperProxyFactory, 代理工厂用代理类创建了Mapper,这里使用的是JDK Proxy
		// 4.进入代理类, Map<Method, MapperMethod> methodCache用来cache
		// 5.进入MapperMethod类,处理MethodSignature和SqlCommand(命令模式)
		// 6.此处省略10000字最终Executor处理(生成的CachingExecutor)
		UserMapper mapper = session.getMapper(UserMapper.class);
		System.out.println(UserMapper.class.getName());
//		mapper.selectByNickName("MyStringTypeHandlers");
		
		mapper.selectByUser(new User().setId(1));
		session.commit();
		session.close();
	}

}
