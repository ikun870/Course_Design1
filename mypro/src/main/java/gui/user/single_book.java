package gui.user;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static gui.Sqllink.gM;
import static gui.Sqllink.rM;
import static gui.swing_demo.test_after_user_login;

import static gui.user.user_login.bookphonenum;

//仅仅是预订，不是办理入住
public class single_book extends MyFrame {
    JComboBox<String> type=new JComboBox<>();

    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();

    //创建表格
   // Object[][] data;

    Object[] titles={"房间号","房间类型","价格(每晚)"};

    public single_book(){
        super("单人订房");


        panel.setLayout(new exclusiveLayout());

        panel.add(new Label("可选房间:"));
        panel.add(type);
        type.addItem("所有");
        type.addItem("单人间");
        type.addItem("双人间");

        JButton search=new JButton("查询");
        panel.add(search);

        JButton book=new JButton("预定");
        panel.add(book);

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
        model.addColumn(titles[2]);
        jt.setModel(model);
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置为单项选择

        panel.add(back);
        back.addActionListener((e)->{
            test_after_user_login();
            this.dispose();
        });
        book.addActionListener((e)->{
            //获得用户选择的
            int index=jt.getSelectedRow();//value=listbox.getSelectedValue();
            //int index=list.getSelectedIndices();//多项选择的话，返回数组
            if(index>=0){
                String rid=model.getValueAt(index,0).toString();
                int price=Integer.parseInt(model.getValueAt(index,2).toString());
                int select=JOptionPane.showConfirmDialog(this,
                        "房间号为"+rid+",您是否确认？",
                        "确认",
                        JOptionPane.YES_NO_OPTION);
                if(select==1){
                    //等待下一次确认
                }
                else{
                    //输入预计入住时间
                    Date date1 = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String arrival1= format.format(date1);
                    Calendar c=Calendar.getInstance();
                    c.add(Calendar.DATE,Integer.valueOf(1));
                    String arrival2= format.format(c.getTime());
                    c.add(Calendar.DATE,Integer.valueOf(1));
                    String arrival3= format.format(c.getTime());
                    c.add(Calendar.DATE,Integer.valueOf(1));
                    String arrival4= format.format(c.getTime());
                    Object[] arrival = {arrival1,arrival2,arrival3,arrival4};
                    String arrival_expected= (String) JOptionPane.showInputDialog(null,"预计登记入住时间","预定",JOptionPane.QUESTION_MESSAGE,null,arrival,null);
                    //提交数据库并提示 切换另一个界面
                    if(arrival_expected!=null)
                    {
                        JOptionPane.showMessageDialog(this,"预定成功！");
                        try {
                            Sqllink.open();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Sqllink.getroomMapper();
                        rM.be_booked(rid,arrival_expected,bookphonenum);//arrival_expecte登记完成后删除
                        Sqllink.close();
                        test_after_user_login();
                        this.dispose();
                    }

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
            model.addRow(new Object[]{r.getId(),r.getType(),r.getPrice()});

        }
        Sqllink.close();
    }

    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width= parent.getWidth();
            height=parent.getHeight();
            Component[] component=parent.getComponents();

            component[0].setBounds(width/6,height/20,width/11,height/10);
            component[1].setBounds(width/4+width/30,height/20,width/5,height/10);
            component[2].setBounds(width*2/5+2*width/15,height/20,width/6,height/10);
            component[3].setBounds(width*3/5+3*width/15,height/20,width/6,height/10);
            component[4].setBounds(0,height/6,width,11*height/12);
            component[5].setBounds(width/30,height/20,width/8,height/10);

        }
    }
}
