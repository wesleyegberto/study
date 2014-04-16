import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyShape
{
	private boolean filled;
	
	public MyOval() {}
	
	public MyOval(boolean filled)
	{
		this.filled = filled;
	}
	
	public MyOval(int x1, int y1, int x2, int y2, Color myColor, boolean filled)
	{
		super(x1, y1, x2, y2, myColor);
		this.filled = filled;
	}
	
	public boolean getFilled()
	{
		return filled;
	}
	
	public void setFilled(boolean filled)
	{
		this.filled = filled;
	}
	
	public void paintComponent(Graphics g)
	{
		draw(g);
	}
	
	public void draw(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(getMyColor());
			
		if(filled)
			g.fillOval(getX1(), getY1(), getX2(), getY2());
		else
			g.drawOval(getX1(), getY1(), getX2(), getY2());
	}
}