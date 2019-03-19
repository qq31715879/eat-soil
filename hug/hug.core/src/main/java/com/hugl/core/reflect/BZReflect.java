package com.hugl.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hug.core.string.BZString;

/**
 * 反射常用方法封装
 * 
 * @author chenjian
 */
public final class BZReflect {

	public static final Object NULL = null;

	/**
	 * 处理 method 的 involve 有参方法
	 * 
	 * @param t
	 *            执行方法的对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            入参值得数组
	 * @param parameterTypes
	 *            入参值的类型
	 */
	public static <T> Object invoke(T t, String methodName, Class<?>[] parameterTypes, Object... args)
			throws Exception {
		// Class<? extends Object> cls = getSuperClass(t.getClass());
		Class<? extends Object> cls = t.getClass();
		Method[] methods = cls.getDeclaredMethods();
		Method methodInvoke = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				methodInvoke = method;
				methodInvoke.setAccessible(true);
				parameterTypes = methodInvoke.getParameterTypes();
				break;
			}
		}
		return methodInvoke.invoke(t, args);
	}

	/**
	 * 处理 method 的 involve 有参或者无参方法
	 * 
	 * @param t
	 *            执行方法的对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            入参值得数组
	 * @param parameterTypes
	 *            入参值的类型自动获取
	 */
	public static <T> Object invoke(T t, String methodName, Object... args) throws Exception {
		return invoke(t, methodName, null, args);
	}

	/**
	 * 获取非Object最终父类
	 * 
	 * @param childClass
	 *            子类Class
	 */
	public static Class<? extends Object> getSuperClass(Class<? extends Object> childClass) {
		Class<? extends Object> superClass = childClass.getSuperclass();
		return superClass == Object.class ? childClass : getSuperClass(superClass);
	}

	/**
	 * @Description 获取T所有的Field
	 * @author chenjian
	 * @date 2016年9月22日 下午4:16:41
	 */
	public static <T> List<Field> getAllFields(Class<? super T> cls) {
		List<Field> fields = new ArrayList<Field>();
		while (cls != Object.class) {
			fields.addAll(Arrays.asList(cls.getDeclaredFields()));
			cls = cls.getSuperclass();
		}
		return fields;
	}

	/**
	 * @Description get实体类属性值
	 * @author chenjian
	 * @date 2016年9月22日 下午4:16:41
	 */
	public static <T> Object getEntityAttribute(T t, String name) throws Exception {
		return invoke(t, "get" + BZString.capitalize(name));
	}

	/**
	 * @Description set实体类属性值
	 * @author chenjian
	 * @date 2016年9月22日 下午4:16:41
	 */
	public static <T> Object setEntityAttribute(T t, String name, Object arg) throws Exception {
		return invoke(t, "set" + BZString.capitalize(name), arg);
	}
}
