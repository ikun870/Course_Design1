package gui.admin;

import gui.MyFrame;
import gui.layout.LayoutAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static gui.swing_demo.*;

public class admin_guest extends MyFrame {
    JButton add_guest=new JButton("增加客户");
    JButton delete_guest=new JButton("删除客户");
    JButton view_guest=new JButton("查找客户");
    JButton update_guest=new JButton("更新客户信息");

    JButton previous=new JButton("上一步");
    public admin_guest(){
        super("管理员");

        panel.setLayout(new exclusiveLayout());

        panel.add(add_guest);
        panel.add(delete_guest);
        panel.add(update_guest);
        panel.add(view_guest);
        panel.add(previous);

        add_guest.addActionListener((e) -> {
            test_add_guest();
            this.dispose();

        });
        delete_guest.addActionListener((e -> {
            test_delete_guest();
            this.dispose();
        }));
        view_guest.addActionListener((e)->{
            //进入到房费报账
        });
        update_guest.addActionListener((e)->{
            test_update_guest();
            this.dispose();
        });
        view_guest.addActionListener((e)->{
            test_view_guest();
            this.dispose();
        });
        previous.addActionListener((e)->{
            test_after_admin_login();
            this.dispose();
        });
    }
    class exclusiveLayout extends LayoutAdapter {
        public void layoutContainer(Container parent) {
            width = parent.getWidth();
            height = parent.getHeight();
            Component[] component = parent.getComponents();
            int h = height/15;

            for (int i = 0; i < 5; i++) {
                component[i].setBounds(width / 3-width/60, h, width*2/5, height / 8);
                h += component[0].getHeight()+width/35;
            }

        }
    }
}
