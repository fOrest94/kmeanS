public class Main 
{
	static double[][] array =
		{{1.0,1.0},
		{1.5,2.0},
		{3.0,4.0},
		{5.0,7.0},
		{3.5,5.0},
		{4.5,5.0},
		{3.5,4.5}};
	static Klaster_1 k1 = new Klaster_1();
	static Klaster_2 k2 = new Klaster_2();
	static int[] numer = new int[2];
	
	/**
	 * W ponizszej funkcji wybisuje wartosci 7 punktow w uk³adzie kartezjañskim
	 */
	
	public static void ShowArrayValues()
	{
		System.out.println("Tabela zawiera po³o¿enie \n7 punktów w uk³adzie kartezjañskim:\n");;
		for(int i = 0; i < 7; i++)
		{
			System.out.print((i+1)+") (");
			for(int j = 0; j < 2; j++)
			{
				if(j == 0)
				{
					System.out.print(array[i][j]+", ");
				}
				else
				{
					System.out.print(array[i][j]);
				}
				
			}
			System.out.print("),\n");
		}
	}
	
	/**
	 *  W tej funkcji dziele dane na dwa klastry. Wybieram tutaj te, które s¹
	 *  najbardziej od siebie oddalone
	 */
	
	public static void Operation_1()
	{
		double pointZero = 0.0;
		double x = 0.0;
		double y = 0.0;
		double max = 0.0;
		double min = 9.0;
		double[][] cluster1 = new double[1][2];
		double[][] cluster2 = new double[1][2];
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(j == 0)
				{
					x = array[i][j]-pointZero;
				}
				else if(j == 1)
				{
					y = array[i][j]-pointZero;
					if(Euclides(x,y)< min)
					{
						min = Euclides(x,y);
				
						for(int k = 0; k < 2; k++)
						{
							cluster1[0][k] = array[i][k];
							numer[0] = i;
						}	
					}
					if(Euclides(x,y)> max)
					{
						max = Euclides(x,y);
						for(int k = 0; k < 2; k++)
						{
							cluster2[0][k] = array[i][k];
							numer[1] = i;
						}	
					}
				}
			}
		}
		k1.setPolozenie(cluster1);
		k1.setId_I(numer[0]);
		k2.setPolozenie(cluster2);
		k2.setId_I(numer[1]);
	}
	
	/**
	 *  W tej funkcji sprawdzam, ktore punkty sa umieszczone najblizej danego z klastrow.
	 *  Wektor centroidow zmienia sie za kazdym razem w momencie gdy klaster przywlaszcza
	 *  sobie kolejne punkty
	 */
	public static void Operation_2()
	{
		int licznik1 = 0;
		int licznik2 = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(i < k2.getId_I())
			{
				if(array[i][0] != k1.getPolozenie()[0][0] && array[i][1] != k1.getPolozenie()[0][1])
				{
					k1.setId(i);
					k1.getPolozenie()[0][0] = k1.getPolozenie()[0][0] + array[i][0];
					k1.getPolozenie()[0][1] = k1.getPolozenie()[0][1] + array[i][1];
					licznik1++;
					if(licznik1 == 2)
					{
						k1.getPolozenie()[0][0] = (double)(Math.round(k1.getPolozenie()[0][1]/3*10))/10;
						k1.getPolozenie()[0][1] = (double)(Math.round(k2.getPolozenie()[0][1]/3*10))/10;
					}
				}
			}
			else
			{
				if((array[i][0] != k2.getPolozenie()[0][0] && array[i][1] != k2.getPolozenie()[0][1]))
				{
					k2.setId(i);
					k2.getPolozenie()[0][0] = k2.getPolozenie()[0][0] + array[i][0];
					k2.getPolozenie()[0][1] = k2.getPolozenie()[0][1] + array[i][1];
					licznik2++;
					if(licznik2 == 3)
					{
						k2.getPolozenie()[0][0] = (double)(Math.round(k2.getPolozenie()[0][0]/4*10))/10;
						k2.getPolozenie()[0][1] = (double)(Math.round(k2.getPolozenie()[0][1]/4*10))/10;
					}
				}
			}
		}
	}
	
	/**
	 * Funkcja ta jest powiazana z funkcja Operation_4. W tej funkcji Po raz koleny sprawdzam 
	 * czy dla nowego po³o¿enia wektora centroidów, kolejne punkty wci¹¿ nale¿¹ do 
	 * poszczególnych klastrów
	 */
	
	public static void Operation_3()
	{
		for(int i = 0; i < array.length; i++)
		{
			if(i < 3)
			{
				if(!(Euclides((array[i][0] - k1.getPolozenie()[0][0]), (array[i][1] - k1.getPolozenie()[0][1])) < 
					Euclides((array[i][0] - k2.getPolozenie()[0][0]), (array[i][1] - k2.getPolozenie()[0][1]))))
				{
					k1.removeId(i);
					k2.setId(i);
				}
			}
			else
			{
				if(!(Euclides((array[i][0] - k1.getPolozenie()[0][0]), (array[i][1] - k1.getPolozenie()[0][1])) > 
				Euclides((array[i][0] - k2.getPolozenie()[0][0]), (array[i][1] - k2.getPolozenie()[0][1]))))
				{
					k2.removeId(i);
					k1.setId(i);
				}
			}
		}
	}
	
	/*
	 * W funkcji tej ponownie obliczam wektor centroidów, gdy¿ po zmianach w funkcji 3 jego
	 * po³o¿enie uleg³o zmianie
	 */
	
	public static void Operation_4()
	{
		double[][] cluster1 = new double[1][2];
		double[][] cluster2 = new double[1][2];
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < k1.getId().size(); j++)
			{
				if(k1.getId().get(j) == i )
				{
					cluster1[0][0] = cluster1[0][0] + array[i][0];
					cluster1[0][1] = cluster1[0][1] + array[i][1];
				}
			}
			for(int j = 0; j < k2.getId().size(); j++)
			{
				if(k2.getId().get(j) == i)
				{
					cluster2[0][0] = cluster2[0][0] + array[i][0];
					cluster2[0][1] = cluster2[0][1] + array[i][1];
				}
			}
		}

		cluster1[0][0] = (double)(Math.round(cluster1[0][0]/k1.getId().size()*10))/10;
		cluster1[0][1] = (double)(Math.round(cluster1[0][1]/k1.getId().size()*10))/10;
		k1.setPolozenie(cluster1);
		
		cluster2[0][0] = (double)(Math.round(cluster2[0][0]/k2.getId().size()*10))/10;
		cluster2[0][1] = (double)(Math.round(cluster2[0][1]/k2.getId().size()*10))/10;
		k2.setPolozenie(cluster2);
	}
	
	public static double Euclides(double x, double y)
	{
		return Math.sqrt(x*x+y*y);
	}
	
	public static void main(String[] args) 
	{
		ShowArrayValues();
		Operation_1();System.out.println(k1.toString());
		Operation_2();System.out.println(k1.toString());
		Operation_3();System.out.println(k1.toString());
		Operation_4();System.out.println(k1.toString());
		System.out.println("\nResults: ");
		System.out.println(k1.toString());
		System.out.println(k2.toString());
	}
}
