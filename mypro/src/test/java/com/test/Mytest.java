//package com.test;
//
//import mapper.AdminMapper;
//import mapper.guestMapper;
//import mapper.roomMapper;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import pojo.guest;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import org.junit.Test;
//import pojo.liver;
//import pojo.room;
//
//public class Mytest {
//    @Test
//    //查找所有客人信息
//    public void testSelectAll() throws IOException {
//        //1.获取SqlSessionFactory
//
//        //1.加载mybatis的核心配置文件，获取sqlSessionFactory
//        //见入门链接
//        //String resource = "org/mybatis/example/mybatis-config.xml";
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        //3.获取Mapper接口的代理对象
//        guestMapper gM=sqlSession.getMapper(guestMapper.class);
//
//        //4.执行方法
//       // List<guest> guests=gM.selectAll();
//       // System.out.println(guests);
//
//        //5.释放志愿
//        sqlSession.close();
//    }
//    @Test
//    //单条件查询 身份证号，名字，客人编号
//    public void testSelectId() throws IOException {
//        String s="张";
//        String x="%"+s+"%";
//        String a="已离开";
//        char b='男';
//        int no=3;
//        String id="510225193232010087";
//        guest g=new guest();
// //       g.setGender(b);
////        g.setStatus(a);
//  //      g.setName(x);
// //       g.setNo(no);
////        g.setId(id);
////        Map map=new HashMap();
////        map.put("status",a);
//        //map.put("gender",b);
//        //接受参数
//
//        //1.加载mybatis的核心配置文件，获取sqlSessionFactory
//        //见入门链接
//        //String resource = "org/mybatis/example/mybatis-config.xml";
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        //3.获取Mapper接口的代理对象
//        guestMapper gM=sqlSession.getMapper(guestMapper.class);
//
//        //4.执行方法
//        //guest g=gM.selectById(id);
//       // guest gg= gM.selectByName(x);
//        //List<guest> gg=gM.selectByCondition(g);
//       // guest gg=gM.selectByCondition(g);
//       // System.out.println(gg);
//
//        //5.释放志愿
//        sqlSession.close();
//    }
//    @Test
//    //查找所有空单人间，空双人间，所有空房间
//    public void testSelectRoom() throws IOException {
//        //接受参数
//        //先让用户做选择是单人间还是双人间
//
//
//
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        roomMapper rM=sqlSession.getMapper(roomMapper.class);
//        //4.执行方法
//        //如果是看单人间
//        //List<room> rr=rM.selectSingleRoom();
//        //如果是想看双人间
//        //List<room> rr=rM.selectDoubleRoom();
//        //如果想查询所有空房间
//        List<room> rr=rM.selectAllRoom();
//        System.out.println(rr);
//
//        //5.释放志愿
//        sqlSession.close();
//    }
//    @Test
//    //单人添加
//    public void testAdd() throws IOException {
//
//        String reg="单人";
//        String name="盘古";
//        char gender='男';
//        String id="000000000000000002";
//        String status="住宿中";
//        String contact="10000002";
//
//        String rid="302";
//        Date date1 = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String arrival= format.format(date1);
//        int stay=3;
//        Calendar c=Calendar.getInstance();
//        c.add(Calendar.DATE,stay);
//        String leave_expected= format.format(c.getTime());
//
//        guest g=new guest();
//        g.setArrival(arrival);
//        g.setLeave_expected(leave_expected);
//        g.setReg(reg);
//        g.setId(id);
//        g.setName(name);
//        g.setRid(rid);
//        g.setStatus(status);
//        g.setContact(contact);
//        g.setGender(gender);
//        g.setPay(150);
//
//
//        //1.获取SqlSessionFactory
//
//        //1.加载mybatis的核心配置文件，获取sqlSessionFactory
//        //见入门链接
//        //String resource = "org/mybatis/example/mybatis-config.xml";
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        //3.获取Mapper接口的代理对象
//        guestMapper gM=sqlSession.getMapper(guestMapper.class);
//
//        //4.执行方法
//        //List<guest> guests=gM.selectAll();
//        //System.out.println(guests);
//        gM.add_guest(g);
//
//        sqlSession.commit();//添加事物
//        //5.释放志愿
//        sqlSession.close();
//    }
//    public guest pack(String arrival,String leave_expected,String rid,room r,int stay){
//        String reg="团体";
//        String name="澜";
//        char gender='男';
//        String id="123400000000000001";
//        String status="住宿中";
//        String contact="12300001";
//
//        guest g=new guest();
//        g.setArrival(arrival);
//        g.setLeave_expected(leave_expected);
//        g.setReg(reg);
//        g.setId(id);
//        g.setName(name);
//        g.setRid(rid);
//        g.setStatus(status);
//        g.setContact(contact);
//        g.setGender(gender);
//        g.setPay(r.getPrice()*stay);
//        return g;
//
//    }
//    public guest pack2(String arrival,String leave_expected,String rid,room r,int stay){
//        String reg="团体";
//        String name="李白";
//        char gender='男';
//        String id="123450000000000001";
//        String status="住宿中";
//        String contact="12340001";
//
//        guest g=new guest();
//        g.setArrival(arrival);
//        g.setLeave_expected(leave_expected);
//        g.setReg(reg);
//        g.setId(id);
//        g.setName(name);
//        g.setRid(rid);
//        g.setStatus(status);
//        g.setContact(contact);
//        g.setGender(gender);
//        g.setPay(r.getPrice()*stay);
//        return g;
//
//    }
//    public guest pack3(String arrival,String leave_expected,String rid,room r,int stay){
//        String reg="团体";
//        String name="王昭君";
//        char gender='女';
//        String id="123460000000000001";
//        String status="住宿中";
//        String contact="12370001";
//
//        guest g=new guest();
//        g.setArrival(arrival);
//        g.setLeave_expected(leave_expected);
//        g.setReg(reg);
//        g.setId(id);
//        g.setName(name);
//        g.setRid(rid);
//        g.setStatus(status);
//        g.setContact(contact);
//        g.setGender(gender);
//        g.setPay(r.getPrice()*stay);
//        return g;
//
//    }
//    @Test
//    //多人添加
//    public void testAdd2() throws IOException {//团体添加
//
//
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession();
//
//        //3.获取Mapper接口的代理对象
//        guestMapper gM=sqlSession.getMapper(guestMapper.class);
//        roomMapper rM=sqlSession.getMapper(roomMapper.class);
//        //4.执行方法
//
//        //用户有序地给出房间号和用户信息
//        //房间号是装在list里面的，用户选好房间
//        List<String> rids=new ArrayList<String>();
//        rids.add("208");//中档双人间208
//        rids.add("305");//低档单人间305
//        //我们肯定要确定总人数的num的
//        int num=3;
//        guest[] team=new guest[num];
//        Date date1 = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String arrival= format.format(date1);
//        int stay=7;//我们认为团体一起退房
//        Calendar c=Calendar.getInstance();
//        c.add(Calendar.DATE,stay);
//        String leave_expected= format.format(c.getTime());
//        for(String rid:rids){
//            //先用rid判断是单人间还是双人间
//            room r=rM.selectById(rid);
//           String type=r.getType();
//           if(type.equals("低档单人间")||type.equals("中档单人间")||type.equals("高档单人间")){
//               //可以给一点提示信息
//               guest g=pack(arrival,leave_expected,rid,r,stay);
//                gM.add_guest(g);
//
//               //最后可以通过主键返回 得到gM.no 可以交互一下
//           }
//           else{
//               //因为现在还没有交互，所以搞3个pack试一下
//               guest g1=pack2(arrival,leave_expected,rid,r,stay);
//               gM.add_guest(g1);
//
//               guest g2=pack3(arrival,leave_expected,rid,r,stay);
//               gM.add_guest(g2);
//
//            }
//
//        }
//
//        sqlSession.commit();//添加事物
//        //5.释放志愿
//        sqlSession.close();
//    }
//    @Test
//    //删除房间，用户
//    public void testAdd_DeleteRoomByAdmin() throws IOException {
//        //1.获取SqlSessionFactory
//
//        //1.加载mybatis的核心配置文件，获取sqlSessionFactory
//        //见入门链接
//        //String resource = "org/mybatis/example/mybatis-config.xml";
//        String resource="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        //2.获取SqlSession对象
//        SqlSession sqlSession=sqlSessionFactory.openSession(true);
//
//        //3.获取Mapper接口的代理对象
//        //guestMapper gM=sqlSession.getMapper(guestMapper.class);
//        AdminMapper aM=sqlSession.getMapper(AdminMapper.class);
//        roomMapper rM=sqlSession.getMapper(roomMapper.class);
//
////        room r=new room();
////        r.setId("402");
////        r.setType("低档双人间");
////        r.setPrice(100);
//        //4.执行方法
//       // aM.update_room_type(r);
////        int cont= aM.update_room_price(100,"低档双人间");
////        room x= rM.selectById("309");
//       // aM.add_room(r);
//       // System.out.println(x);
////        String[] s=new String[2];
////        s[0]="401";
////        s[1]="402";
//      //  sqlSession.commit();
//   //     aM.delete_room("402");
//
//
//        //删除用户
//        aM.delete_guests(new int[]{8});
//        //5.释放志愿
//        sqlSession.close();
//    }
//
//    //退房
//    //(1)查询当前时间，更新房费，管理员执行
// //   public int testUpdatePayByAdmin() throws IOException, ParseException {
////        String resource="mybatis-config.xml";
////        InputStream inputStream = Resources.getResourceAsStream(resource);
////        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////
////        SqlSession sqlSession=sqlSessionFactory.openSession(true);
////
////        AdminMapper aM=sqlSession.getMapper(AdminMapper.class);
////        roomMapper rM=sqlSession.getMapper(roomMapper.class);
////
////        String rid="302";
////        String get_time=aM.search_liver(rid).get(0).getArrival();
////       // 解析时间字符串
////        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date gd=sdf.parse(get_time);
////        Date now=new Date();
////        //当前时间减去到达时间
////        double diff1=(double) (now.getTime()-gd.getTime())/(1000*3600*24);
////        int diff2=(int)(diff1+0.5);
////
////        //计算实际房费
////        int pay=rM.selectById(rid).getPrice()*diff2;
////
////        sqlSession.close();
////        return pay;
//    //}
//    @Test
//    //(2)退房结账，管理员执行
//    public void testUpdateRoomByAdmin() throws IOException, ParseException {
//
////        String resource="mybatis-config.xml";
////        InputStream inputStream = Resources.getResourceAsStream(resource);
////        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////
////        //2.获取SqlSession对象
////        SqlSession sqlSession=sqlSessionFactory.openSession(true);
////
////        //3.获取Mapper接口的代理对象
////        guestMapper gM=sqlSession.getMapper(guestMapper.class);
////        AdminMapper aM=sqlSession.getMapper(AdminMapper.class);
////
////        String rid="302";
////        int pay=testUpdatePayByAdmin();
////        //需要用户的rid，no，pay
////        List<liver> x=aM.search_liver(rid) ;
////
////        //再次获取时间，添加到leave_true
////        Date date1 = new Date();
////        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String leve_true= format.format(date1);
////
////
////        //修改一下啊，弄一个连接查询或者嵌套查询
////        for(liver l:x) {
////            guest g=new guest();
////            g.setRid("302");
////            g.setPay(pay);
////            g.setNo(Integer.valueOf(l.getNo()));//注意这里转成Integer型
////            g.setLeave_true(leve_true);
////            aM.update_room(g);
////        }
////
////        //5.释放志愿
////        sqlSession.close();
//    }
//}
