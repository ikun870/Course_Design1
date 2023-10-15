package gui.layout;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MyLayout implements LayoutManager2 {

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {

    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
            int width=parent.getWidth();
            int height=parent.getHeight();
            Component[] children=parent.getComponents();

            int y=0;
            for(int i=0;i<children.length;i++){
                Component c=children[i];
                c.setBounds(width/4,y,width/2,height/12);
                y+=height/12;

            }
    }
}
