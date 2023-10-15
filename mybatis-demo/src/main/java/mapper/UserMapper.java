package mapper;

import pojo.User;

import java.util.List;

//代理里面放一些查询结果的返回值
public interface UserMapper {

    List<User> selectAll();//返回集合，里面都是User对象
    //当然我们是需要很多个返回对象还是一个返回对象，取决于UserMapper里面的查询语句
    User selectById(int id);
}
