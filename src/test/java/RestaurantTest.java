import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RestaurantFinder.Restaurant;
import com.RestaurantFinder.itemNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    private void addRestaurantDetails() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
		restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
	    addRestaurantDetails();
		boolean result = restaurant.isRestaurantOpen();
		assertTrue(result);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        addRestaurantDetails();
		boolean result = restaurant.isRestaurantOpen();
		assertFalse(result);


    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>PRICE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
	//craeting spoof to test order details
    List<Item> spoof = new ArrayList<Item>();
 @Test
    public void order_total_post_item_selection() {
        addRestaurantDetails();
		 spoof = restaurant.getMenu();
		int price =  restaurant.getOrderTotal(spoof);
		assertEquals(888, price);
    }

    @Test
    public void order_total_post_item_removed_from_selection() {
      addRestaurantDetails();
	  spoof = restaurant.getMenu();
	 int total = restaurant.getOrderValue(spoof);
        int afterTotal = spoof.get(1).getPrice();
        spoof.remove(1);
        int price = restaurant.getOrderTotal();
		int value = total-afterTotal;
		assertEquals(value, price);
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>PRICE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
       addRestaurantDetails();
	   int initialMenuSize = restaurant.getMenu().size();
	   restaurant.addToMenu("Pasta",500);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addRestaurantDetails();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
       addRestaurantDetails();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}