package gui.admin;

import com.mysql.jdbc.StringUtils;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.guest;
import pojo.room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static gui.Sqllink.*;
import static gui.swing_demo.*;

public class add_guest extends MyFrame {

    JTextField name=new JTextField();
    JComboBox<Character> gender=new JComboBox<>();
    JTextField id=new JTextField();
    JTextField contact=new JTextField();
    JTextField stay=new JTextField();
    JTextField rid=new JTextField();
    JButton submit=new JButton("确认信息");
    JButton back=new JButton("返回");
    public add_guest(){
        super("用户填写个人信息:");

        panel.setLayout(new exclusiveLayout());

        JLabel l1= new JLabel("请填写个人信息！");
        l1.setFont(new Font("楷体",Font.BOLD,14));
        panel.add(l1);


        JLabel l2=new JLabel("姓名：");
        panel.add(l2);
        name.setColumns(20);
        panel.add(name);

        JLabel l3=new JLabel("性别：");
        panel.add(l3);
        gender.addItem('男');
        gender.addItem('女');
        panel.add(gender);

        JLabel l4=new JLabel("身份证号码：");
        panel.add(l4);
        id.setColumns(20);
        panel.add(id);

        JLabel l5=new JLabel("联系电话：");
        panel.add(l5);
        contact.setColumns(20);
        panel.add(contact);


        JLabel l6=new JLabel("预计住宿天数：");
        panel.add(l6);
        stay.setColumns(8);
        panel.add(stay);

        JLabel l7=new JLabel("房间号：");
        panel.add(l7);
        stay.setColumns(8);
        panel.add(rid);


        panel.add(back);
        panel.add(submit);

        back.addActionListener((e)->{
            test_after_admin_login();
            this.dispose();
        });
        submit.addActionListener((e)->{
            guest g=new guest();
            boolean b;
            try {
                b = judge(g);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(b){
                int select=JOptionPane.showConfirmDialog(this,
                        "房间号为"+rid.getText()+",您是否确认？",
                        "信息确认",
                        JOptionPane.YES_NO_OPTION);
                if(select==1){
                    //等待下一次确认
                }
                else{
                    JOptionPane.showMessageDialog(this,"填写完成！");
                    try {
                        Sqllink.open();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Sqllink.getguestMapper();
                    Sqllink.getroomMapper();
                    gM.add_guest(g);
                    Sqllink.close();
                    test_after_admin_login();
                    this.dispose();
                }


            }
            else{
                JOptionPane.showMessageDialog(this,"请确认所填信息正确！");
            }

        });
    }
    public boolean judge(guest g) throws IOException {
        String name=this.name.getText();
        char gender=this.gender.getItemAt(this.gender.getSelectedIndex());
        String id=this.id.getText();
        String contact=this.contact.getText();
        String stay=this.stay.getText();
        String rid=this.rid.getText();

        if(rid.length()==3&&name.length()>1&&id.length()==18&&contact.length()==11&& StringUtils.isStrictlyNumeric(stay)&&!stay.equals("0"))
        {
            g.setName(name);
            g.setId(id);
            g.setGender(gender);
            g.setContact(contact);
            g.setRid(rid);
            g.setStatus("住宿中");

            Sqllink.open();
            Sqllink.getAdminMapper();
            room r=aM.select_room_by_id(rid);
            Sqllink.close();
            g.setPay(r.getPrice()*Integer.parseInt(stay));

            if(!r.getStatus().equals("空"))
                return false;

            Date date1 = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String arrival= format.format(date1);
            g.setArrival(arrival);
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DATE,Integer.parseInt(stay));
            String leave_expected= format.format(c.getTime());
            g.setLeave_expected(leave_expected);
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

            children[0].setBounds(width/30,0,width/4,height/12);
            int y=0;
            for(int i=1;i<children.length-6;i++){
                Component c=children[i];
                c.setBounds(width/4,y,width/2,height/12);
                y+=height/12;
            }
            children[children.length-6].setBounds(width/4,y,width/5,height/12);
            children[children.length-5].setBounds(width/4,y+height/12,width/5,height/12);
            children[children.length-4].setBounds(width/2,y,width/2,height/12);
            children[children.length-3].setBounds(width/2,y+height/12,width/5,height/12);
            children[children.length-2].setBounds(width/4,y+height/5,width/5,height/12+height/225);
            children[children.length-1].setBounds(width*2/3-width/7,y+height/5,width/5,height/12+height/225);
        }
    }
}
