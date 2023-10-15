package gui.admin;

import com.mysql.jdbc.StringUtils;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.guest;
import pojo.room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static gui.Sqllink.aM;
import static gui.swing_demo.test_admin_guest;

public class view_guest extends MyFrame {
    JButton search=new JButton("查询");
    JButton back=new JButton("返回");
    JTextField no=new JTextField();
    JTextField name=new JTextField();
    JTextField rid=new JTextField();
    JTextField id=new JTextField();
    JComboBox<String> box=new JComboBox<>();
    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"编号","姓名","房间号","性别","身份证号码","联系电话","状态","支付","入住时间","房间到期时间","实际离开时间","预订电话"};
    public view_guest(){
        super("管理员");

        panel.setLayout(new exclusiveLayout());

        panel.add(back);
        back.addActionListener((e ->{
            test_admin_guest();
            this.dispose();
        }));


        box.addItem("按条件查询");
        box.addItem("所有历史住户");
        box.addItem("当前住户");
        panel.add(box);

        panel.add(search);


        JLabel l4=new JLabel("编号:");
        panel.add(l4);
        panel.add(no);

        JLabel l3=new JLabel("身份证号码:");
        panel.add(l3);
        panel.add(id);

        JLabel l1=new JLabel("姓名:");
        panel.add(l1);
        panel.add(name);

        JLabel l2=new JLabel("房间号:");
        panel.add(l2);
        panel.add(rid);


        JScrollPane scrollPane=new JScrollPane(jt);
        panel.add(scrollPane,BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        model.addColumn(titles[4]);
        model.addColumn(titles[5]);
        model.addColumn(titles[6]);
        model.addColumn(titles[7]);
        model.addColumn(titles[8]);
        model.addColumn(titles[9]);
        model.addColumn(titles[10]);
        model.addColumn(titles[11]);
        jt.setModel(model);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //"编号","姓名","房间号","性别","身份证号码","联系电话","状态","支付","入住时间","房间到期时间","实际离开时间","预订电话"

        jt.setEnabled(false);//设置不能编辑
        jt.getTableHeader().setReorderingAllowed(false);//表头不可拖动

        panel.add(new Label("(查找优先级由左至右，由上至下)"));

       box.addActionListener((e -> {
           if(!box.getItemAt(box.getSelectedIndex()).equals("按条件查询"))
           {
               no.setEditable(false);
               id.setEditable(false);
               name.setEditable(false);
               rid.setEditable(false);
           }
           else {
               no.setEditable(true);
               id.setEditable(true);
               name.setEditable(true);
               rid.setEditable(true);
           }
       }));
        search.addActionListener((e)->{
            String s = box.getItemAt(box.getSelectedIndex());

            String no=this.no.getText();
            String id=this.id.getText();
            String name=this.name.getText();
            String rid=this.rid.getText();
            if(s.equals("按条件查询"))
            {
                try {
                    Sqllink.open();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Sqllink.getAdminMapper();
                List<guest> guests=new ArrayList<>();
                if (!no.equals("")&& StringUtils.isStrictlyNumeric(no)&&!no.equals("0")) {
                    guests=aM.select_guest_no(Integer.parseInt(no));
                } else if (id.length()==18) {
                    guests=aM.select_guest_id(id);
                } else if (!name.equals("")) {
                    guests=aM.select_guest_name(name);
                } else if (rid.length()==3) {
                    guests=aM.select_guest_rid(rid);
                }
                else{
                    JOptionPane.showMessageDialog(this,"请输入信息再查找！");
                }
                Sqllink.close();
                show_guests2(guests);
            }
            else {
                try {
                    show_guests(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });
    }

    private void show_guests2(List<guest> guests) {
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        //"编号","姓名","房间号","性别","身份证号码","联系电话","状态","支付","入住时间","房间到期时间","实际离开时间","预订电话"
        for(guest g:guests){
            String arr=g.getArrival().substring(0,19);
            String exp=g.getLeave_expected().substring(0,19);
            String tru=null;
            if(g.getLeave_true()!=null)
            tru=g.getLeave_true().substring(0,19);
            model.addRow(new Object[]{g.getNo(),g.getName(),g.getRid(),g.getGender(),g.getId(),g.getContact(),g.getStatus(),g.getPay(),arr,exp,tru,g.getBookphonenum()});
        }

    }

    private void show_guests(String s) throws IOException {
        List<guest> guests;
        Sqllink.open();
        Sqllink.getAdminMapper();
        switch (s) {
            case "当前住户":
                guests= aM.selectAll_guests_in();
                break;
            default:
                guests=aM.selectAll_guests();
        }
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        //"编号","姓名","房间号","性别","身份证号码","联系电话","状态","支付","入住时间","房间到期时间","实际离开时间","预订电话"
        for(guest g:guests){
            //g.getArrival(),g.getLeave_expected(),g.getLeave_true()
            String arr=g.getArrival().substring(0,19);
            String exp=g.getLeave_expected().substring(0,19);
            String tru=null;
            if(g.getLeave_true()!=null)
            tru=g.getLeave_true().substring(0,19);
            model.addRow(new Object[]{g.getNo(),g.getName(),g.getRid(),g.getGender(),g.getId(),g.getContact(),g.getStatus(),g.getPay(),arr,exp,tru,g.getBookphonenum()});
        }
        Sqllink.close();
    }

    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width= parent.getWidth();
            height=parent.getHeight();
            Component[] component=parent.getComponents();

            component[0].setBounds(width/10,height/20,width/6,height/10);
            component[1].setBounds(width/3,height/20,width/5,height/10);
            component[2].setBounds(width*3/5,height/20,width/6,height/10);
            component[3].setBounds(width/10,height/5,width/10,height/10);
            component[4].setBounds(width/6,height/5,width/9,height/10);
            component[5].setBounds(width/3,height/5,width/6,height/10);
            component[6].setBounds(width/2,height/5,width/4+width/30,height/10);
            component[7].setBounds(width/10,height/5+3*height/20,width/10,height/10);
            component[8].setBounds(width/6,height/5+3*height/20,width/9,height/10);
            component[9].setBounds(width/3,height/5+3*height/20,width/6,height/10);
            component[10].setBounds(width/2,height/5+3*height/20,width/7,height/10);
            component[12].setBounds(width*2/3,height/5+3*height/20,width/3,height/10);
            component[11].setBounds(0,height/2,width,height/2);

            int x1=6;
            int x2=6;
            int x3=6;
            int x4=6;
            int x5=16;
            int x6=11;
            int x7=6;
            int x8=6;
            int x9=15;
            int x10=15;
            int x11=15;
            int x12=11;
            int x=x1+x2+x3+x4+x5+x6+x7+x8+x9+x10+x11+x12;

            jt.getColumnModel().getColumn(0).setPreferredWidth(x1*width/x);
            jt.getColumnModel().getColumn(1).setPreferredWidth(x2*width/x);
            jt.getColumnModel().getColumn(2).setPreferredWidth(x3*width/x);
            jt.getColumnModel().getColumn(3).setPreferredWidth(x4*width/x);
            jt.getColumnModel().getColumn(4).setPreferredWidth(x5*width/x);
            jt.getColumnModel().getColumn(5).setPreferredWidth(x6*width/x);
            jt.getColumnModel().getColumn(6).setPreferredWidth(x7*width/x);
            jt.getColumnModel().getColumn(7).setPreferredWidth(x8*width/x);
            jt.getColumnModel().getColumn(8).setPreferredWidth(x9*width/x);
            jt.getColumnModel().getColumn(9).setPreferredWidth(x10*width/x);
            jt.getColumnModel().getColumn(10).setPreferredWidth(x11*width/x);
            jt.getColumnModel().getColumn(11).setPreferredWidth(x12*width/x);

        }
    }
}
