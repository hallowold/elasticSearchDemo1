
--权限表s_right
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(1,'文章','/article','新增','实体','POST','/article/article');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(2,'文章','/article','批量删除','id数组','DELETE','/article/articles');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(3,'文章','/article','修改','实体','PUT','/article/article');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(4,'文章','/article','获取所有','无','GET','/article/articles');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(5,'文章','/article','根据标题模糊查询','字符串','GET','/article/name/{name}');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(6,'文章','/article','互动行为记录','文章id，行为id','GET','/article/interaction/articleId/{articleId}/mode/{mode}');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(7,'文章','/article','根据行为模式统计互动记录','行为id','GET','/article/interaction/articles/mode/{mode}');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(8,'权限','/right','新增','实体','POST','/right/right');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(9,'权限','/right','批量删除','id数组','DELETE','/right/rights');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(10,'权限','/right','修改','实体','PUT','/right/right');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(11,'权限','/right','获取所有','无','GET','/right/rights');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(12,'权限','/right','通过名称模糊查询','字符串','GET','/right/rights/name/{name}');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(13,'角色','/role','新增','实体','POST','/role/role');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(14,'角色','/role','批量删除','id数组','DELETE','/role/roles');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(15,'角色','/role','修改','实体','PUT','/role/role');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(16,'角色','/role','获取所有','无','GET','/role/roles');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(17,'角色','/role','通过名称模糊查询','字符串','GET','/role/roles/name/{name}');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(18,'用户','/user','新增','实体','POST','/user/user');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(19,'用户','/user','批量删除','id数组','DELETE','/user/users');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(20,'用户','/user','修改','实体','PUT','/user/user');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(21,'用户','/user','获取所有','无','GET','/user/users');
INSERT INTO s_right(id, method_name, method_path, name, remark, method_type, right_url) VALUES(22,'用户','/user','通过名称模糊查询','字符串','GET','/user/users/loginName/{loginName}');

--角色表s_role
INSERT INTO s_role(id, name, user_id) VALUES(1,'ADMIN',1);
INSERT INTO s_role(id, name, user_id) VALUES(2,'ADMIN',2);

--用户表s_user
INSERT INTO s_user(id, login_name, show_name, email, password) VALUES(1,'ADMIN', '系统管理员', 'a@b.c', 'cloversec');
INSERT INTO s_user(id, login_name, show_name, email, password) VALUES(1,'TEST', '测试用户', 'a@b.d', 'cloversec');

--角色权限表s_role_right
INSERT INTO s_role_right(id, role_id, right_id) VALUES(1,1,1');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(2,1,2');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(3,1,3');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(4,1,4');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(5,1,5');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(6,1,6');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(7,1,7');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(8,1,8');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(9,1,9');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(10,1,10');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(11,1,11');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(12,1,12');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(13,1,13');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(14,1,14');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(15,1,15');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(16,1,16');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(17,1,17');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(18,1,18');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(19,1,19');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(20,1,20');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(21,1,21');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(22,1,22');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(23,2,1');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(24,2,2');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(25,2,3');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(26,2,4');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(27,2,5');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(28,2,6');
INSERT INTO s_role_right(id, role_id, right_id) VALUES(29,2,7');