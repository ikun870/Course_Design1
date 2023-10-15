package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.guest;
import pojo.room;

import java.util.List;

public interface guestMapper {
    //单条件动态查询
    guest selectByConditionSingle(guest g);
    //List<guest> selectByCondition(@Param("status") String status,@Param("gender")String gender);

      //重点使用这个，上面的byid，byname可以淘汰了
     // List<guest> selectByCondition(guest g);
     // List<guest> selectByCondition(Map map);

        //查找住户的房间号，先进行其编号查找
     // String search_room(String gno);//修改这个方法，进行嵌套查询


      //添加，只是执行一个动作
      //一旦添加了guest，那么living,room的信息也要改变
      void add_guest(guest g);

      String get_password(String username);
      String get_bookphonenum(String username);

      //用户注册
      void user_register(@Param("username")String username,@Param("password")String password,@Param("phonenum")String phonenum);

      List<guest> view_my_living(String bookphonenum);
        List<guest> view_my_check_out(String bookphonenum);

      List<room> view_my_booked_room(@Param("bookphonenum") String bookphonenum);
      void check_out(guest g);


}
