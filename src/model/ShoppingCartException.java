package model;

public class ShoppingCartException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShoppingCartException()
    {
    }

    public ShoppingCartException(String message)
    {
        super(message);
    }
}
