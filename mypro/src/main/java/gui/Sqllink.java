package gui;

import mapper.AdminMapper;
import mapper.guestMapper;
import mapper.roomMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Sqllink {
   public static SqlSession sqlSession;
   public static AdminMapper aM;
   public static guestMapper gM;
   public static roomMapper rM;
    public static void open() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession=sqlSessionFactory.openSession(true);
    }
    public static void getAdminMapper(){
         aM=sqlSession.getMapper(AdminMapper.class);
    }
    public static void getguestMapper(){
         gM=sqlSession.getMapper(guestMapper.class);
    }
    public static void getroomMapper(){
        rM=sqlSession.getMapper(roomMapper.class);
    }
    public static void close(){
        sqlSession.close();
    }
}
