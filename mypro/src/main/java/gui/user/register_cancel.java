package gui.user;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static gui.Sqllink.gM;
import static gui.Sqllink.rM;
import static gui.swing_demo.*;

public class register_cancel extends MyFrame {
    JButton back=new JButton("返回");
    JButton register=new JButton("登记");
    JButton cancel=new JButton("取消预定");
    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"房间号","房间类型","预计入住时间","状态"};
    public register_cancel() throws IOException {
        super("我的客房");
        panel.setLayout(new exclusiveLayout());

        JScrollPane scrollPane=new JScrollPane(jt);
        panel.add(scrollPane, BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        jt.setModel(model);
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置为单项选择

        show_living();

        panel.add(back);

        back.addActionListener((e)->{
            test_after_user_login();
            this.dispose();
        });
        panel.add(register);
        register.addActionListener((e)->{
            //进入登记页面
            int index=jt.getSelectedRow();//value=listbox.getSelectedValue();
            //int index=list.getSelectedIndices();//多项选择的话，返回数组
            if(index>=0) {
                String rid=model.getValueAt(index,0).toString();
                try {
                    Sqllink.open();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Sqllink.getroomMapper();
                int price=rM.selectById(rid).getPrice();
                String type=rM.selectById(rid).getType();
                Sqllink.close();
                if(type.endsWith("单人间"))
                test_single_add_information(rid,price);
                else {
                    try {
                        test_double_add_information(rid,price);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,"请选择您要登记的房间！");
            }
        });
        panel.add(cancel);
        cancel.addActionListener((e)->{
            int index=jt.getSelectedRow();//value=listbox.getSelectedValue();
            //int index=list.getSelectedIndices();//多项选择的话，返回数组
            if(index>=0) {
                String rid=model.getValueAt(index,0).toString();
                try {
                    Sqllink.open();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Sqllink.getroomMapper();
                int select=JOptionPane.showConfirmDialog(this,
                        "房间号为"+rid+",您是否确认取消预定？",
                        "确认",
                        JOptionPane.YES_NO_OPTION);
                if(select==1){
                    //等待下一次确认
                }
                else {
                    rM.inti(rid);
                    try {
                        test_register_cancel();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.dispose();
                }
                Sqllink.close();
            }
            else  JOptionPane.showMessageDialog(this,"请选择您要取消预订的房间！");

        });
    }


    private void show_living() throws IOException {
        //连接数据库并显示
        java.util.List<room> rooms;
        Sqllink.open();
        Sqllink.getguestMapper();
        Sqllink.getroomMapper();
        List<room> roomss=gM.view_my_booked_room(user_login.bookphonenum);//被预定的

        for(room r:roomss){
            String rid=r.getId();
            String type=r.getType();
            model.addRow(new Object[]{rid,type,r.getArrival_expected(),r.getStatus()});
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
            component[1].setBounds(width/3-width/10,6*height/7,width/7,height/10);
            component[2].setBounds(1*width/2-width/10,6*height/7,width/7,height/10);
            component[3].setBounds(2*width/3-width/10,6*height/7,width/7,height/10);
        }
    }
}
