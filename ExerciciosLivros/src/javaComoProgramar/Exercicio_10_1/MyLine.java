import java.awt.Color;
import java.awt.Graphics;

public class MyLine extends MyShape
{
	public MyLine()
	{
	}
	
	public MyLine(int x1, int y1, int x2, int y2, Color myColor)
	{
		super(x1, y1, x2, y2, myColor);
	}
	
	public void paintComponent(Graphics g)
	{
		draw(g);
	}
	
	public void draw(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(getMyColor());
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	}
}