package gui.teambook;

import com.mysql.jdbc.StringUtils;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import gui.user.user_login;
import pojo.guest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static gui.Sqllink.gM;
import static gui.Sqllink.rM;
import static gui.swing_demo.test_after_user_login;

public class double_add_information extends MyFrame {
    JTextField name2=new JTextField();
    JComboBox<Character> gender2=new JComboBox<>();
    JTextField id2=new JTextField();
    JTextField contact2=new JTextField();

    JTextField name1=new JTextField();
    JComboBox<Character> gender1=new JComboBox<>();
    JTextField id1=new JTextField();
    JTextField contact1=new JTextField();
    JTextField stay1=new JTextField();
    //   JComboBox<String> arrival=new JComboBox<>();
    JButton submit=new JButton("确认信息");
    JButton back=new JButton("返回");
    public double_add_information(String rid, int price){
        super("用户填写个人信息:");

        panel.setLayout(new exclusiveLayout());

//        JLabel l1= new JLabel("请填写个人信息！");
//        l1.setFont(new Font("楷体",Font.BOLD,14));
//        panel.add(l1);

        JLabel l22=new JLabel("姓名1：");
        panel.add(l22);
        name1.setColumns(20);
        panel.add(name1);

        JLabel l33=new JLabel("性别1：");
        panel.add(l33);
        gender1.addItem('男');
        gender1.addItem('女');
        panel.add(gender1);

        JLabel l44=new JLabel("身份证号码1：");
        panel.add(l44);
        id1.setColumns(20);
        panel.add(id1);

        JLabel l55=new JLabel("联系电话1：");
        panel.add(l55);
        contact1.setColumns(20);
        panel.add(contact1);


        JLabel l66=new JLabel("预计住宿天数：");
        panel.add(l66);
        stay1.setColumns(8);
        panel.add(stay1);

        JLabel l2=new JLabel("姓名2：");
        panel.add(l2);
        name2.setColumns(20);
        panel.add(name2);

        JLabel l3=new JLabel("性别2：");
        panel.add(l3);
        gender2.addItem('男');
        gender2.addItem('女');
        panel.add(gender2);

        JLabel l4=new JLabel("身份证号码2：");
        panel.add(l4);
        id2.setColumns(20);
        panel.add(id2);

        JLabel l5=new JLabel("联系电话2：");
        panel.add(l5);
        contact2.setColumns(20);
        panel.add(contact2);


        JLabel l6=new JLabel("预计住宿天数：");
        panel.add(l6);

        panel.add(back);
        panel.add(submit);

        back.addActionListener((e)->{
            test_after_user_login();
            this.dispose();
        });
        submit.addActionListener((e)->{
            guest g1=new guest();
            guest g2=new guest();
            List<guest> guests=new ArrayList<>();
            guests.add(g1);
            guests.add(g2);
            boolean b=judge(guests);
            if(b){
                int pay=price*Integer.valueOf(stay1.getText());
                int select=JOptionPane.showConfirmDialog(this,
                        "房间号为"+rid+",房费为"+pay+",您是否确认？",
                        "信息确认",
                        JOptionPane.YES_NO_OPTION);
                if(select==1){
                    //等待下一次确认
                }
                else{
                    //提交数据库并提示 切换另一个界面

  //                  g1.setReg("团体");
                    g1.setStatus("住宿中");
                    g1.setRid(rid);
                    g1.setPay(pay);
                    g1.setBookphonenum(user_login.bookphonenum);

   //                 g2.setReg("团体");      不要这个
                    g2.setStatus("住宿中");
                    g2.setRid(rid);
                    g2.setPay(pay);
                    g2.setBookphonenum(user_login.bookphonenum);

                    JOptionPane.showMessageDialog(this,"填写完成！");
                    try {
                        Sqllink.open();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Sqllink.getguestMapper();
                    Sqllink.getroomMapper();
                    gM.add_guest(g1);//后面再处理，两个人一样的情况
                    gM.add_guest(g2);
                    rM.reset_arrival_expected(rid);
                    Sqllink.close();
                    //回退到用户界面
                    test_after_user_login();
                    this.dispose();
                }


            }
            else{
                JOptionPane.showMessageDialog(this,"请确认所填信息正确！");
            }

        });
    }
    public boolean judge(List<guest> guests){
        String name1=this.name1.getText();
        char gender1=this.gender1.getItemAt(this.gender1.getSelectedIndex());
        String id1=this.id1.getText();
        String contact1=this.contact1.getText();
        String stay1=this.stay1.getText();

        String name2=this.name2.getText();
        char gender2=this.gender2.getItemAt(this.gender2.getSelectedIndex());
        String id2=this.id2.getText();
        String contact2=this.contact2.getText();

        if(name1.length()>1&&id1.length()==18&&contact1.length()==11&& StringUtils.isStrictlyNumeric(stay1)&&!stay1.equals("0")
        &&name2.length()>1&&id2.length()==18&&contact2.length()==11)
        {
            guest g1=guests.get(0);
            g1.setName(name1);
            g1.setId(id1);
            g1.setGender(gender1);
            g1.setContact(contact1);
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String arrival= format.format(date);
            g1.setArrival(arrival);
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DATE,Integer.valueOf(stay1));
            String leave_expected= format.format(c.getTime());
            g1.setLeave_expected(leave_expected);

            guest g2=guests.get(1);
            g2.setName(name2);
            g2.setId(id2);
            g2.setGender(gender2);
            g2.setContact(contact2);
            g2.setArrival(arrival);
            g2.setLeave_expected(leave_expected);
            return true;
        }
        return false;
    }
    //用内部类来写布局
    private class exclusiveLayout extends LayoutAdapter {

        @Override
        public void layoutContainer(Container parent) {
            width=parent.getWidth();
            height=parent.getHeight();
            Component[] children=parent.getComponents();

           // children[0].setBounds(width/30,0,width/4,height/12);
            int y=0;
            int x=0;
            for(int i=0;i<10;i++){
                Component c=children[i];
                c.setBounds(width/20,x,2*width/5,height/12);
                x+=height/12;
            }
            for(int i=10;i<children.length-3;i++){
                Component c=children[i];
                c.setBounds(width/20+width/2,y,2*width/5,height/12);
                y+=height/12;
            }
            children[children.length-2].setBounds(width*4/7,y+height/12,width/7,height/12+height/225);
            children[children.length-1].setBounds(width*3/4,y+height/12,width/7,height/12+height/225);
        }
    }
}
