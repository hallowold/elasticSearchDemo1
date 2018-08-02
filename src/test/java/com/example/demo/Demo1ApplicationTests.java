package com.example.demo;

import com.example.demo.common.util.StringUtil;
import com.example.demo.security.config.LoginSuccessHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Predicate;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Demo1ApplicationTests {

	@Test
	public void testLameda() {
		// 不使用lambda表达式为每个订单加上12%的税
//		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//		for (Integer cost : costBeforeTax) {
//			double price = cost + .12*cost;
//			System.out.println(price);
//		}

// 使用lambda表达式
		List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax2.forEach(i -> System.out.println(i));
//		costBeforeTax2.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
	}

	@Test
	public void makeSql() {
		String dataStr = "1,文章,/article,新增,实体,POST,/article/article;2,文章,/article,批量删除,id数组,DELETE,/article/articles;3,文章,/article,修改,实体,PUT,/article/article;4,文章,/article,获取所有,无,GET,/article/articles;5,文章,/article,根据标题模糊查询,字符串,GET,/article/name/{name};6,文章,/article,互动行为记录,文章id，行为id,GET,/article/interaction/articleId/{articleId}/mode/{mode};7,文章,/article,根据行为模式统计互动记录,行为id,GET,/article/interaction/articles/mode/{mode};8,权限,/right,新增,实体,POST,/right/right;9,权限,/right,批量删除,id数组,DELETE,/right/rights;10,权限,/right,修改,实体,PUT,/right/right;11,权限,/right,获取所有,无,GET,/right/rights;12,权限,/right,通过名称模糊查询,字符串,GET,/right/rights/name/{name};13,角色,/role,新增,实体,POST,/role/role;14,角色,/role,批量删除,id数组,DELETE,/role/roles;15,角色,/role,修改,实体,PUT,/role/role;16,角色,/role,获取所有,无,GET,/role/roles;17,角色,/role,通过名称模糊查询,字符串,GET,/role/roles/name/{name};18,用户,/user,新增,实体,POST,/user/user;19,用户,/user,批量删除,id数组,DELETE,/user/users;20,用户,/user,修改,实体,PUT,/user/user;21,用户,/user,获取所有,无,GET,/user/users;22,用户,/user,通过名称模糊查询,字符串,GET,/user/users/loginName/{loginName}";
		String[] templateArray = {"INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(",
										",'" ,"','" ,"','" ,"','" ,"','" ,"','" ,"');"};
//		String dataStr = "1,1,1;2,1,2;3,1,3;4,1,4;5,1,5;6,1,6;7,1,7;8,1,8;9,1,9;10,1,10;11,1,11;12,1,12;13,1,13;14,1,14;15,1,15;16,1,16;17,1,17;18,1,18;19,1,19;20,1,20;21,1,21;22,1,22;23,2,1;24,2,2;25,2,3;26,2,4;27,2,5;28,2,6;29,2,7";
//		String[] templateArray = {"INSERT INTO s_role_right(id, role_id, right_id) VALUES(",
//										",", ",", ");"};

		String[] dataArray = dataStr.split(";");
		List<String> sqlList = new ArrayList<>();
		for(String str : dataArray) {
			String sql = templateArray[0];
			String[] assemblyArray = str.split(",");
			for (int i = 0; i < assemblyArray.length; i++) {
				sql = sql + assemblyArray[i] + templateArray[i + 1];
			}
			sqlList.add(sql);
		}
		sqlList.stream().forEach(str -> System.out.println(str));
	}

	@Test
	public void testSpecialStrChecking() {
		String[] testArray = new String[] {"_", "%", "*", "'","''", "aawww223", "sdf_ieww0", "文科无人机%", "we*无_法问%问155481"};
		Arrays.stream(testArray).forEach(str-> System.out.println(StringUtil.changeSpecialCharacter(str)));
	}

	static class Student{
		String name = null ;
		int number = -1 ;
		String sex = null ;
		int age = -1 ;
		String school = null ;

		public static class StudentBuilder{
			String name = null ;
			int number = -1 ;
			String sex = null ;
			int age = -1 ;
			String school = null ;
			public StudentBuilder setName(String name) {
				this.name = name;
				return  this ;
			}

			public StudentBuilder setNumber(int number) {
				this.number = number;
				return  this ;
			}

			public StudentBuilder setSex(String sex) {
				this.sex = sex;
				return  this ;
			}

			public StudentBuilder setAge(int age) {
				this.age = age;
				return  this ;
			}

			public StudentBuilder setSchool(String school) {
				this.school = school;
				return  this ;
			}
			public Student build() {
				return new Student(this);
			}
		}

		public Student(StudentBuilder builder){
			this.age = builder.age;
			this.name = builder.name;
			this.number = builder.number;
			this.school = builder.school ;
			this.sex = builder.sex ;
		}

		@Override
		public String toString() {
			return "Student{" +
					"name='" + name + '\'' +
					", number=" + number +
					", sex='" + sex + '\'' +
					", age=" + age +
					", school='" + school + '\'' +
					'}';
		}
	}

	@Test
	public  void test( ){
		Student a = new Student.StudentBuilder().setAge(13).setName("LiHua").build();
		Student b = new Student.StudentBuilder().setSchool("sc").setSex("Male").setName("ZhangSan").build();
		System.out.println(a);
		System.out.println(b);
	}

	@Test
	public void test2() {
		List<Integer> list = null;
		System.out.println(list == null);
		System.out.println(list.isEmpty());
	}


}
