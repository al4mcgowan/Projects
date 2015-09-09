package Model;

public class Game {
	private String title;
	private String publisher;
	private String console;
	private String price;
	private String stock;
	private String id;

	public Game(String t, String pb, String c, String p, String s) 
	{
		title = t;
		publisher = pb;
		console = c;
		price = p;
		stock = s;
		id = "0";
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getConsole()
	{
		return console;
	}

	public void setConsole(String console)
	{
		this.console = console;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getStock()
	{
		return stock;
	}

	public void setStock(int i)
	{
		this.stock = "" + i;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
}
