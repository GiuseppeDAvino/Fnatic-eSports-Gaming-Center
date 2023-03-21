package model;
import java.util.*;
import java.io.*;

public class ShoppingCart implements java.io.Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The shopping cart items are stored in a Vector.
    @SuppressWarnings("rawtypes")
	protected Vector items;

    @SuppressWarnings("rawtypes")
	public ShoppingCart()
    {
        items = new Vector();
    }

/** Returns a Vector containing the items in the cart. The Vector
 *  returned is a clone, so modifying the vector won't affect the
 *  contents of the cart.
 */
    @SuppressWarnings("rawtypes")
	public Vector getItems()
    {
        return (Vector) items.clone();
    }

    @SuppressWarnings("unchecked")
	public void addItem(PostazioneBean newItem)
    {
        items.addElement(newItem);
    }

    public void removeItem(int itemIndex)
    {
        items.removeElementAt(itemIndex);
    }

// Warning! This order number is reset every time the server is
// restarted. This technique of generating an order number is
// just for demonstration.
    protected static int nextOrderNumber = 1;

// Submit the order and return a confirmation number.
    @SuppressWarnings("rawtypes")
	public String completeOrder(Billing billing)
        throws ShoppingCartException
    {
// You would normally insert the order into a database or send
// it to an application server. For the sake of simplicity
// this shopping cart just writes the order to a file.
        try
        {
            int orderNumber = 0;

// Make sure no other threads can be generating an order number.
            synchronized (this)
            {
                orderNumber = nextOrderNumber;
                nextOrderNumber = nextOrderNumber + 1;
            }
            PrintWriter out = new PrintWriter(
                new FileOutputStream("order"+orderNumber));

// Print the billing info.
            out.println(billing.nameOnCard);
            out.println(billing.creditCardType);
            out.println(billing.creditCardNumber);
            out.println(billing.creditCardExpiration);

// Print out the items.
            Enumeration e = items.elements();
            while (e.hasMoreElements())
            {
                PostazioneBean item = (PostazioneBean) e.nextElement();

                out.println(item.getN_posto()+","+
                    item.getTipo()+","+item.getCosto()+","+item.getCodice_sala());
            }
            out.close();

// Return a confirmation number (the order number as a string in this case).
            return ""+orderNumber;
        }
        catch (Exception exc)
        {
            throw new ShoppingCartException(
                "Error saving order: "+exc.toString());
        }
    }
}