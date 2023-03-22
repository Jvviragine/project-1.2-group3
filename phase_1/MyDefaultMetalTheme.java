package phase_1;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

//? THE BLACK BACKGROUND IMPLEMENTATION DONE BELOW

public class MyDefaultMetalTheme extends DefaultMetalTheme 
{
    public ColorUIResource getWindowTitleInactiveBackground() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getWindowTitleBackground() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControlHighlight() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControlDarkShadow() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControl() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getControlDarkShadow() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getControl() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
}
