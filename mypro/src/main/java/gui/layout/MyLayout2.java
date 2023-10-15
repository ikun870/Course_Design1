package gui.layout;

import gui.layout.LayoutAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyLayout2 extends LayoutAdapter {
    List<Component> list=new ArrayList<>();
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        list.add(comp);
    }


    @Override
    public void removeLayoutComponent(Component comp) {
        list.remove(comp);
    }

    @Override
    public void layoutContainer(Container parent) {
        int width=parent.getWidth();
        int height=parent.getHeight();

        int y=0;
        for(int i=0;i<list.size();i++){
            Component c= list.get(i);
            c.setBounds(width/4,y,width/2,height/12);
            y+=height/12;

        }

    }
}
