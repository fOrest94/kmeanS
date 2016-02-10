import java.util.ArrayList;

public class Klaster_1 
{
	private double[][] polozenie = new double[1][2];
	private ArrayList<Integer> id = new ArrayList<Integer>();
	private int id_I;
	
	public Klaster_1()
	{
		
	}
	public Klaster_1(double[][] polozenie, ArrayList<Integer> id) 
	{
		this.polozenie = polozenie;
		this.id = id;
	}

	public int getId_I() 
	{
		return id_I;
	}
	
	public void setId_I(int id_I) 
	{
		this.id_I = id_I;
		this.id.add(id_I);
	}
	
	public double[][] getPolozenie() 
	{
		return polozenie;
	}
	
	public void setPolozenie(double[][] pol) 
	{
		this.polozenie = pol;
	}
	
	public ArrayList<Integer> getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id.add(id);
	}
	
	public void removeId(int id)
	{
		this.id.remove(id);
	}
	
	public String toString()
	{
		String p1 = "Klaster 1 to punkty: "+
		id.toString()+
		" Mean Vector (centroid): ("+polozenie[0][0]+", "+polozenie[0][1]+")";
		return p1;
			
		
	}
}
