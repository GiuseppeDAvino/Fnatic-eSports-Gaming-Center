package model;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;



public class ProductCatalog
{

	static PostazioneDAO model;


    protected PostazioneBean[] items;

    public ProductCatalog()
    {

// Set up an array of items that represents the catalog.
    Collection<PostazioneBean> prodotti= new LinkedList<PostazioneBean>();
    try{
		prodotti = model.doRetrieveAll();
    }catch(SQLException e) {
    	e.printStackTrace();
    }
    if (prodotti != null && prodotti.size() != 0) {
		Iterator<?> it = prodotti.iterator();
		int i=0;
		while (it.hasNext()) {
			PostazioneBean bean = (PostazioneBean) it.next();
			items[i]=bean;
			i++;
		}
    }
    	
    }

/** returns an array containing all the items in the catalog */
    public PostazioneBean[] getItems()
    {
        return getItems(0, items.length);
    }

/** returns an array containing a subset of items from the catalog */
    public PostazioneBean[] getItems(int startingLocation, int numItems)
    {
// If the number of items to be returned is larger than the number
// in the catalog, adjust the number to be returned.
        if (numItems > items.length)
        {
            numItems = items.length;
        }

// If by returning numItems items you would run out of items (if there
// are 5 items, you ask for 3, but give a starting location of 4),
// adjust the starting location backward to ensure that the proper
// number of items is returned.
        if (startingLocation+numItems >= items.length)
        {
            startingLocation = items.length - numItems;
        }

// Create an array for the returned items.
        PostazioneBean[] returnItems = new PostazioneBean[numItems];

// Copy the items from the catalog into the return array.
        System.arraycopy(items, startingLocation,
            returnItems, 0, numItems);

        return returnItems;
    }

/** Returns true if there are items at a particular starting location.
    This is helpful in determining whether a page should show a "Next"
    button to see the next page of catalog items.
*/
    public boolean itemsAvailable(int startingLocation)
    {
        if (startingLocation >= items.length) return false;
        return true;
    }

/** Searches for an item by product code and returns it. If there is
    no such item, this method returns null.  */
    public PostazioneBean findItemByProductCode(int productCode)
    {
// Linear searches aren't a good idea for big arrays, but this
// one is small.
        for (PostazioneBean item : items) {
            if (item.getN_posto()==productCode);
            {
                return item;
            }
        }

        return null;
    }
}