package gui.user;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.guest;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static gui.Sqllink.gM;
import static gui.Sqllink.rM;
import static gui.swing_demo.test_after_user_login;
import static gui.user.user_login.bookphonenum;

public class view_book extends MyFrame {
    JButton back=new JButton("返回");
    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"房间号","类型","入住时间","应退房时间","支付","状态"};
    public view_book() throws IOException {
        super("我的客房");
        panel.setLayout(new exclusiveLayout());

        JScrollPane scrollPane=new JScrollPane(jt);
        panel.add(scrollPane,BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        model.addColumn(titles[4]);
        model.addColumn(titles[5]);
        jt.setModel(model);

        show_living();

        panel.add(back);

        back.addActionListener((e)->{
            test_after_user_login();
            this.dispose();
        });
    }
    private void show_living() throws IOException {
            //连接数据库并显示
            List<room> rooms;
            Sqllink.open();
            Sqllink.getguestMapper();
            Sqllink.getroomMapper();
            List<guest> guests=gM.view_my_living(bookphonenum);//已离开和住宿中的
            List<room> roomss=gM.view_my_booked_room(bookphonenum);//被预定的

            guest pre = null;
            for(guest g:guests){
                String rid=g.getRid();
                if(pre!=null&&pre.getRid().equals(rid))
                    continue;
                String type=rM.selectById(rid).getType();

                int no=g.getNo();
                guest gg=new guest();
                gg.setNo(no);
                String status=gM.selectByConditionSingle(gg).getStatus();
                String leave=g.getLeave_expected();
               if(g.getLeave_expected()!=null)
                    leave=g.getLeave_expected().substring(0,10);
                model.addRow(new Object[]{rid,type,g.getArrival().substring(0,10),leave,g.getPay(),status});
                pre=g;
            }
        for(room r:roomss){
            String rid=r.getId();
            String type=r.getType();
            model.addRow(new Object[]{rid,type,r.getArrival_expected(),null,null,r.getStatus()});
        }
            Sqllink.close();

    }
    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width= parent.getWidth();
            height=parent.getHeight();
            Component[] component=parent.getComponents();

            component[0].setBounds(0,0,width,5*height/6);
            component[1].setBounds(2*width/5,6*height/7,width/5,height/10);
        }
    }
}
