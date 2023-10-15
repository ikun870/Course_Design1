package pojo;


import mapper.guestMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisDemo {
    public static void main(String[] args) throws IOException {


        //1.加载mybatis的核心配置文件，获取sqlSessionFactory
        //见入门链接
        //String resource = "org/mybatis/example/mybatis-config.xml";
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //3.执行sql语句,返回的user对象放入List
        //List<User> users=sqlSession.selectList("mapper.UserMapper.selectAll");
        //3.1 获取UserMapper接口的代理对象
        guestMapper gu=sqlSession.getMapper(guestMapper.class);//代理对象过程由mybatis内部实现
        //List<guest> guests= gu.selectAll();

       // System.out.println(guests);

        //4.释放资源
        sqlSession.close();
    }
}
