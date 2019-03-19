package com.hug.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hug.dao.bean.User;

@Repository
public interface UserMapper {
	/**
	 * @Description 对于简单语句来说，注解使代码显得更加简洁，然而 Java
	 *              注解对于稍微复杂的语句就会力不从心并且会显得更加混乱。因此，如果你需要做很复杂的事情，那么最好使用 XML 来映射语句。
	 *              选择何种方式以及映射语句的定义的一致性对你来说有多重要这些完全取决于你和你的团队。换句话说，永远不要拘泥于一种方式，
	 *              你可以很轻松的在基于注解和 XML 的语句映射方式间自由移植和切换
	 * @author chenjian
	 * @date 2018年4月11日 下午10:55:22
	 */
	@Select("select count(1) from user where id = #{arg0} and nick_name=#{arg1}")
	public int selectCont(int id, String nickName);

	@Insert("insert into user(name, nick_name, age) values(#{name}, #{nickName}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertUser(User user);

	public User selectById(int id);

	@Select("select * from user where nick_name = #{nick_name}")
	public String selectByNickName(String nickName);
	
	@Select("select * from user where id = #{id}")
	public String selectByUser(User user);

	/**
	 * @Description 返回map只能返回嵌套map
	 * @author chenjian
	 * @date 2018年4月12日 下午5:11:02
	 */
	@MapKey("id")
	public Map<Integer, User> selectMap();

}
