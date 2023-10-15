package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.guest;
import pojo.room;

import java.util.List;

public interface roomMapper {
    //就只进行查询操作了
    //添加房客信息的操作放在guestMapper里面了

     room selectById(String id);

    //查询空房间
    List<room> selectSingleRoom();
    List<room> selectDoubleRoom();
    List<room> selectAllRoom();

    void be_booked(@Param("rid") String rid,@Param("arrival_expected") String arrival_expected,@Param("bookphonenum")String bookphonenum);
    void reset_arrival_expected(String rid);
    void inti(String rid);
    List<room> get_be_booked();
}
