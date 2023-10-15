package gui.admin;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static gui.Sqllink.*;
import static gui.swing_demo.*;

public class delete_room extends MyFrame {

    JComboBox<String> type=new JComboBox<>();

    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"房间号","房间类型"};

    public delete_room(){
        super("管理员");


        panel.setLayout(new exclusiveLayout());

        panel.add(new Label("可删除的房间:"));
        panel.add(type);
        type.addItem("所有");
        type.addItem("单人间");
        type.addItem("双人间");

        JButton search=new JButton("查询");
        panel.add(search);

        JButton delete=new JButton("删除");
        panel.add(delete);

        JButton back=new JButton("返回");
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
        jt.setModel(model);
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置为单项选择

        panel.add(back);
        back.addActionListener((e)->{
            test_admin_room();
            this.dispose();
        });
        delete.addActionListener((e)->{
            //获得用户选择的
            int index=jt.getSelectedRow();//value=listbox.getSelectedValue();
            //int index=list.getSelectedIndices();//多项选择的话，返回数组
            if(index>=0){
                    String rid = model.getValueAt(index, 0).toString();
                    int select = JOptionPane.showConfirmDialog(this,
                            "房间号为" + rid + ",您是否确认删除？",
                            "确认",
                            JOptionPane.YES_NO_OPTION);
                    if (select == 1) {
                        //等待下一次确认
                    } else {
                            JOptionPane.showMessageDialog(this, "删除成功！");
                            try {
                                Sqllink.open();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            Sqllink.getAdminMapper();
                            aM.delete_room(rid);
                            Sqllink.close();
                            test_delete_room();
                            this.dispose();

                    }
            }
        });
    }
    public void show_rooms(String s) throws IOException {
        //连接数据库并显示
        List<room> rooms;
        Sqllink.open();
        Sqllink.getroomMapper();
        switch (s) {
            case "单人间":
                rooms=rM.selectSingleRoom();
                break;
            case "双人间":
                rooms=rM.selectDoubleRoom();
                break;
            default:
                rooms=rM.selectAllRoom();
        }
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        for(room r:rooms){
            model.addRow(new Object[]{r.getId(),r.getType()});

        }
        Sqllink.close();
    }

    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width= parent.getWidth();
            height=parent.getHeight();
            Component[] component=parent.getComponents();

            component[0].setBounds(width/6,height/20,width/7,height/10);
            component[1].setBounds(width/4+width/15,height/20,width/6,height/10);
            component[2].setBounds(width*2/5+2*width/15,height/20,width/6,height/10);
            component[3].setBounds(width*3/5+3*width/15,height/20,width/6,height/10);
            component[4].setBounds(0,height/6,width,11*height/12);
            component[5].setBounds(width/30,height/20,width/8,height/10);

        }
    }

}
