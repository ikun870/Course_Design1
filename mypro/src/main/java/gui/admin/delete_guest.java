package gui.admin;

import com.mysql.jdbc.StringUtils;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.guest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static gui.Sqllink.aM;
import static gui.swing_demo.test_admin_guest;
import static gui.swing_demo.test_delete_guest;

public class delete_guest extends MyFrame {
    JButton search=new JButton("查询");
    JButton back=new JButton("返回");
    JButton delete=new JButton("删除");
    JTextField no=new JTextField();
    JTextField name=new JTextField();
    JTextField rid=new JTextField();
    JTextField id=new JTextField();
    JComboBox<String> box=new JComboBox<>();
    JTable jt=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    Object[] titles={"编号","姓名","房间号","性别","身份证号码","状态"};
    public delete_guest(){
        super("管理员");

        panel.setLayout(new exclusiveLayout());

        panel.add(back);
        back.addActionListener((e ->{
            test_admin_guest();
            this.dispose();
        }));



        box.addItem("所有离开住户");
        box.addItem("按条件查询");
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
        panel.add(scrollPane, BorderLayout.CENTER);//scrollPane里面包含了list
        model.addColumn(titles[0]);
        model.addColumn(titles[1]);
        model.addColumn(titles[2]);
        model.addColumn(titles[3]);
        model.addColumn(titles[4]);
        model.addColumn(titles[5]);
        jt.setModel(model);
        //jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //"编号","姓名","房间号","性别","身份证号码"，“状态”

        jt.getTableHeader().setReorderingAllowed(false);//表头不可拖动
        jt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//设置为单项选择


        panel.add(new Label("(查找优先级由左至右，由上至下)"));
        panel.add(delete);

        delete.addActionListener((e)->{
            int[] index=jt.getSelectedRows();
            if(index.length>0){
                int[] array =new int[index.length];
                for(int i=0;i<index.length;i++){
                    array[i]=Integer.parseInt(model.getValueAt(index[i], 0).toString());
                }
                StringBuilder sum = new StringBuilder(model.getValueAt(index[0], 0).toString());
                for(int i=1;i<index.length;i++)
                {
                    sum.append("、").append(model.getValueAt(index[i], 0).toString());
                }
                int select=JOptionPane.showConfirmDialog(this,
                        "编号为"+sum+",您是否确认删除？",
                        "确认",
                        JOptionPane.YES_NO_OPTION);
                if(select==0){
                    try {
                        Sqllink.open();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Sqllink.getAdminMapper();
                    aM.delete_guests(array);
                    Sqllink.close();
                    JOptionPane.showMessageDialog(this,"删除成功");
                    test_delete_guest();
                    this.dispose();
                }
        }});
        box.addActionListener((e) -> {
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
        });
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
                java.util.List<guest> guests=new ArrayList<>();
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

    private void show_guests2(java.util.List<guest> guests) {
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        for(guest g:guests){
            if(g.getStatus().equals("已离开"))
                model.addRow(new Object[]{g.getNo(),g.getName(),g.getRid(),g.getGender(),g.getId(),g.getStatus()});
        }

    }

    private void show_guests(String s) throws IOException {
        List<guest> guests;
        Sqllink.open();
        Sqllink.getAdminMapper();
        guests=aM.selectAll_guests();
        int x=model.getRowCount();
        for(int i=0;i<x;i++)
            model.removeRow(0);
        for(guest g:guests){
            if(g.getStatus().equals("已离开"))
            model.addRow(new Object[]{g.getNo(),g.getName(),g.getRid(),g.getGender(),g.getId(),g.getStatus()});
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
            component[13].setBounds(width*4/5,height/20,width/6,height/10);
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
            int x5=14;
            int x6=6;
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
