package gui.admin;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import gui.user.single_book;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static gui.Sqllink.aM;
import static gui.Sqllink.rM;
import static gui.swing_demo.test_admin_room;
import static gui.swing_demo.test_after_user_login;

public class view_room extends MyFrame {
    JTable jt=new JTable();
    JComboBox<String> type=new JComboBox<>();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"房间号","房间类型","价格(每晚)","状态"};
    public view_room(){
        super("管理员");
        panel.setLayout(new exclusiveLayout());

        panel.add(new Label("房间:"));
        panel.add(type);
        type.addItem("所有");
        type.addItem("单人间");
        type.addItem("双人间");
        type.addItem("空闲单人间");
        type.addItem("空闲双人间");

        JButton search=new JButton("查询");
        panel.add(search);

        JButton back=new JButton("返回");
        panel.add(back);

        search.addActionListener((e)->{
            //根据用户选择的内容进行查询操作
            String s= type.getItemAt(type.getSelectedIndex());
            try {
                show_rooms(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JScrollPane scrollPane=new JScrollPane(jt);
        panel.add(scrollPane,BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        jt.setModel(model);
        jt.setCellSelectionEnabled(false);;//设置为不能选择

        panel.add(back);
        back.addActionListener((e)->{
            test_admin_room();
            this.dispose();
        });

    }
    public void show_rooms(String s) throws IOException {
        //连接数据库并显示
        List<room> rooms;
        Sqllink.open();
        Sqllink.getAdminMapper();
        switch (s) {
            case "单人间":
                rooms= aM.select_single();
                break;
            case "双人间":
                rooms=aM.select_double();
                break;
            case "空闲单人间":
                rooms=aM.select_ksingle();
                break;
            case "空闲双人间":
                rooms=aM.select_kdouble();
                break;
            default:
                rooms=aM.selectAll();
        }
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        for(room r:rooms){
            model.addRow(new Object[]{r.getId(),r.getType(),r.getPrice(),r.getStatus()});
        }
        Sqllink.close();
    }
    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width= parent.getWidth();
            height=parent.getHeight();
            Component[] component=parent.getComponents();

            component[0].setBounds(width/7,height/20,width/11,height/10);
            component[1].setBounds(width/4,height/20,width/6,height/10);
            component[2].setBounds(width*2/5+2*width/15,height/20,width/6,height/10);
            component[4].setBounds(width*3/5+3*width/15,height/20,width/6,height/10);
            component[3].setBounds(0,height/6,width,11*height/12);

        }
    }
}
