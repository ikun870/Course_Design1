package gui.admin;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import gui.user.check_out;
import pojo.guest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static gui.Sqllink.*;
import static gui.Sqllink.gM;
import static gui.swing_demo.*;
import static gui.user.user_login.bookphonenum;

public class admin_check_out extends MyFrame {
    JButton back=new JButton("返回");
    JButton out=new JButton("退房");
    JButton search=new JButton("查询");
    JTextField rid=new JTextField();
    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"房间号","姓名","类型","入住时间","应退房时间","支付","状态"};
    public admin_check_out() throws IOException {
        super("管理员");
        panel.setLayout(new exclusiveLayout());

        JScrollPane scrollPane=new JScrollPane(jt);
        panel.add(scrollPane, BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        model.addColumn(titles[4]);
        model.addColumn(titles[5]);
        model.addColumn(titles[6]);
        jt.setModel(model);

        jt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//设置为多项选择

        show_living();

        panel.add(back);
        panel.add(new Label("房间号"));
        panel.add(rid);
        panel.add(search);
        panel.add(out);


        back.addActionListener((e)->{
            test_after_admin_login();
            this.dispose();
        });
        search.addActionListener((e)->{
            try {
                show_living2();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        out.addActionListener((e -> {
            int[] index=jt.getSelectedRows();//多项选择的话，返回数组
            if(index.length>0){
                List<guest> gs=new ArrayList<>();
                List<guest> gs2;
                int sum=0;
                String rrid=new String();
                for(int i=0;i<index.length;i++)
                {
                    int ii=index[i];
                    String rid = model.getValueAt(ii, 0).toString();
                    rrid+=rid+",";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Sqllink.open();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Sqllink.getroomMapper();
                    int price = rM.selectById(rid).getPrice();//获取房间价格
                    Sqllink.getAdminMapper();
                    gs2 = aM.select_guest_rid(rid);//
                    List<guest> gs3=new ArrayList<>();
                    gs3.add(gs2.get(0));
                    if(gs2.size()>=2){
                        if(gs2.get(0).getArrival().equals(gs2.get(1).getArrival()))
                            gs3.add(gs2.get(1));
                    }
                    gs2=gs3;
                    Sqllink.close();
                    int pay;//原来的房费
                    Date d2 = new Date();//现在的时间
                    try {
                        Date d1 = sdf.parse(gs2.get(0).getArrival());
                        int stay_true = (int) ((d2.getTime() - d1.getTime() + 43200000) / 86400000);
                        stay_true=stay_true==0?1:stay_true;
                        pay = stay_true * price;//实际房费
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String leave_true = format.format(d2);
                    sum+=pay;
                    for (guest g : gs2) {
                        g.setPay(pay);
                        g.setLeave_true(leave_true);
                        gs.add(g);
                    }
                }
                int select = JOptionPane.showConfirmDialog(this,
                        "房间号为" + rrid + "需付房费为"+sum+",您是否确认退房？",
                        "确认",
                        JOptionPane.YES_NO_OPTION);
                //实际上后面应该有一个实际的付费操作，完成之后才提交数据库，但是这里省略了，确认之后就可以退房
                if (select == 0) {
                    try {
                        Sqllink.open();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Sqllink.getguestMapper();
                    for (guest g : gs) {
                        gM.check_out(g);
                    }
                    Sqllink.close();
                    JOptionPane.showMessageDialog(this, "退房成功");
                    try {
                        test_admin_check_out();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.dispose();
                }
            }
            else {
                JOptionPane.showMessageDialog(this,"请选择您要退的房间！");
            }
        }));
    }

    private void show_living2() throws IOException {
        Sqllink.open();
        Sqllink.getroomMapper();
        Sqllink.getAdminMapper();
        String rid=this.rid.getText();
        java.util.List<guest> guests=aM.select_guest_rid2(rid);//住宿中的
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        // guest pre=null;
        for(guest g:guests){
//            if(pre!=null&&pre.getRid().equals(rid))
//                continue;
            String type=rM.selectById(rid).getType();
            String leave=g.getLeave_expected();
            if(g.getLeave_expected()!=null)
                leave=g.getLeave_expected().substring(0,16);
            model.addRow(new Object[]{rid,g.getName(),type,g.getArrival().substring(0,16),leave,g.getPay(),g.getStatus()});
            //  pre=g;
        }
        Sqllink.close();
    }

    private void show_living() throws IOException {
        //连接数据库并显示
        Sqllink.open();
        Sqllink.getroomMapper();
        Sqllink.getAdminMapper();
        java.util.List<guest> guests=aM.selectAll_guests_in();//住宿中的

       // guest pre=null;
        for(guest g:guests){
            String rid=g.getRid();
//            if(pre!=null&&pre.getRid().equals(rid))
//                continue;
            String type=rM.selectById(rid).getType();
            String leave=g.getLeave_expected();
            if(g.getLeave_expected()!=null)
                leave=g.getLeave_expected().substring(0,16);
            model.addRow(new Object[]{rid,g.getName(),type,g.getArrival().substring(0,16),leave,g.getPay(),g.getStatus()});
          //  pre=g;
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
            component[1].setBounds(1*width/8,6*height/7,width/7,height/10);
            component[2].setBounds(1*width/4+width/16,6*height/7,width/10,height/10);
            component[3].setBounds(width/3+width/10,6*height/7,width/7,height/10);
            component[4].setBounds(4*width/7+width/20,6*height/7,width/7,height/10);
            component[5].setBounds(5*width/6,6*height/7,width/7,height/10);

            int x1=10;
            int x2=10;
            int x3=15;
            int x4=20;
            int x5=20;
            int x6=10;
            int x=x1+x2+x3+x4+x5+x6;

            jt.getColumnModel().getColumn(0).setPreferredWidth(x1*width/x);
            jt.getColumnModel().getColumn(1).setPreferredWidth(x2*width/x);
            jt.getColumnModel().getColumn(2).setPreferredWidth(x3*width/x);
            jt.getColumnModel().getColumn(3).setPreferredWidth(x4*width/x);
            jt.getColumnModel().getColumn(4).setPreferredWidth(x5*width/x);
            jt.getColumnModel().getColumn(5).setPreferredWidth(x6*width/x);


        }
    }
}
