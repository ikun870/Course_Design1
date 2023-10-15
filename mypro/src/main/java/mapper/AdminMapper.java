package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.guest;
import pojo.liver;
import pojo.room;

import java.util.List;

//代理里面放一些查询结果的返回值
public interface AdminMapper {
    //登录
    String get_password(String adminname);
    //更新房间的信息,返回一个成功修改的数目
    //更新房间的类型
    //批量更新房间的单价
    void update_room_inf(@Param("rid") String id,@Param("price") int price,@Param("type") String type);

    //按id删除房间
    void delete_room(String id);//根据id删除
    //批量删除房间
//    void delete_rooms(@Param("s")String[] s);
    //增加房间
    void  add_room(room r);//明天测试一下delete，add

    //退房操作 必须只有用户退房缴费后，管理员才会更新房间状态
    //需要用户的rid，no，pay
    void update_room(guest g);

    //living中的查找统一成一个方法，考虑到双人间，返回集合
    //后面请删除这个方法
//    List<liver> search_liver(String rid);

    //客户删除功能，（感觉不是必须的功能），每隔一段时间进行信息删除？
    void delete_guests(int[] array);

    List<room> selectAll();
    List<room> select_single();
    List<room> select_double();
    List<room> select_ksingle();
    List<room> select_kdouble();
    room select_room_by_id(String id);
    List<guest> selectAll_guests();
    List<guest> selectAll_guests_in();
    List<guest> select_guest_no(int no);
    List<guest> select_guest_id(String id);
    List<guest> select_guest_name(String name);
    List<guest> select_guest_rid(String rid);
    List<guest> select_guest_rid2(String rid);
    guest select_guest_living(String gno);
    void update_guest(guest g);
    void check_out(guest g);
}
