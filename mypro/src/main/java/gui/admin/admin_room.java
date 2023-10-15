package gui.admin;

import gui.MyFrame;
import gui.layout.LayoutAdapter;

import javax.swing.*;
import java.awt.*;

import static gui.swing_demo.*;

public class admin_room extends MyFrame {
    JButton add_room=new JButton("增加客房");
    JButton delete_room=new JButton("删除客房");
    JButton view_room=new JButton("查看客房");
    JButton update_room=new JButton("更新客房信息");
    JButton previous=new JButton("返回");
    public admin_room(){
        super("管理员");
        panel.setLayout(new exclusiveLayout());

        panel.add(add_room);
        panel.add(delete_room);
        panel.add(view_room);
        panel.add(update_room);
        panel.add(previous);

        add_room.addActionListener((e) -> {
            test_add_room();
            this.dispose();
        });
        delete_room.addActionListener((e -> {
            test_delete_room();
            this.dispose();
        }));
        view_room.addActionListener((e)->{
            test_view_room();
            this.dispose();
        });
        update_room.addActionListener((e)->{
            test_update_room();
            this.dispose();
        });
        previous.addActionListener((e)->{
            test_after_admin_login();
            this.dispose();
        });
    }
    class exclusiveLayout extends LayoutAdapter {
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
            Component[] component = parent.getComponents();
            int h = 20;

            for (int i = 0; i < 5; i++) {
                component[i].setBounds(width / 3-10, h, width*2/5, height / 8);
                h += component[0].getHeight()+15;
            }

        }
    }
}
