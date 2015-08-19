/**
 *
 * ShoppingCartTest has a main method and you cannot make changes to it.
 * You have to implement Item and ShoppingCart classes for the main method to work
 * Item class should have a constructor with 3 arguments. (product name, quantity, unit price)
 * Shopping Cart should hold all the items the user wants to buy
 * It should implement addToCart, removeFromCart, printInvoice, applyCoupon methods.
 * It should also implement getTotalAmount, getPayableAmount
 * Sales tax is 14% of the total amount and it should be printed on the invoice
 * On applying coupon IND10, 10% discount should be applied to the cart
 * No other copon codes are allowed
 * 
 */
class Item {
	String productname;
	 int quantity;
	 double unitprice;
	Item(String productname , int quantity , double unitprice) {
		this.productname = productname;
		this.quantity = quantity;
		this.unitprice = unitprice;
	}
}
class ShoppingCart {
	Item[] itemslist;
	int count;
	double price;
	double payment;
	double discount;
  double tax;
  String coupon;
	ShoppingCart() {
	  itemslist = new Item[10];
	  this.count = 0;
	  this.price = 0;
	  this.payment = 0;
	  this.discount = 0;
    this.tax = 0;
    this.coupon = "";
   }
   public void addToCart(Item details) {
   	itemslist[count] = details;
   	count++;
   }
   public void showCart() {
     for (int i = 0; i<count ; i++) {
     	System.out.println(itemslist[i].productname + itemslist[i].quantity);
     }
   }
   public void removeFromCart(Item details) {
   	for (int j=0; j<count; j++) {
   	if (details.productname.compareTo(itemslist[j].productname) == 0) {
   		for (int i = j; i < count - 1 ; i++) {
              itemslist [i] = itemslist[i+1];
   }
    System.out.println("-----------------------------------------");
   }
   }
   count--;
   } 
   public double getTotalAmount() {
    System.out.println("-----------------------------------------");
     this.price = 0;
   	for(int i = 0; i<count; i++) {
       this.price = this.price + (itemslist[i].unitprice * itemslist[i].quantity);
      }
    return this.price;
   }
   public void applyCoupon(String coupon) {
      this.coupon = coupon;
   	
   }
   public double getPayableAmount() {
    if(this.coupon == "IND10") {
      this.discount = this.price * (0.1);
      this.price = this.price - this.discount;
    } else {
         //System.out.println("coupon is invalid");
         this.price = this.price;
        }
      this.payment = 0;
      this.tax = this.price * (0.14);
       this.payment = this.price + this.tax;
       return this.payment;
     }
   
   public void printInvoice() {
     System.out.println("productname  quantity  unitprice  total");
     System.out.println("-----------------------------------------");
   	for(int i = 0; i < count; i++) {
   		System.out.println(itemslist[i].productname + "\t" + itemslist[i].quantity +
        "\t" + itemslist[i].unitprice + "\t" + (itemslist[i].unitprice * itemslist[i].quantity));
	}
    System.out.println("-----------------------------------------");
   	System.out.println("            total price " +this.getTotalAmount() );
    this.getPayableAmount();
    System.out.println("            discount" +this.discount);
    System.out.println("            tax    " + this.tax );
    System.out.println("            payment price" +this.payment);
    //System.out.println("-----------------------------------------");
   }
}
public class ShoppingCartTest
{
  public static void main(String[] args)
  {
    // creates new items with product name, quantity and unit price
    Item i1 = new Item("Olive Oil 1l", 3, 100.0);
    Item i2 = new Item("Cheese Slices", 2, 50.0);
    Item i3 = new Item("Bread", 1, 75.0);
    Item i4 = new Item("Eggs", 50, 10.0);
    Item i5 = new Item("Chicken Salami", 2, 100.0);
   ShoppingCart cart = new ShoppingCart();
     /* add the item to the cart */
    cart.addToCart(i1);
    cart.addToCart(i2);
    cart.addToCart(i3);
    cart.addToCart(i4);
    cart.addToCart(i5);
    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Bread: 1
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    /* remove the item sent as argument from the cart */
    cart.removeFromCart(i3);  
    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    /*
     * gets the total amount for the items in the cart
     * Expected Output:
     * 1100
     */
    double totalAmount = cart.getTotalAmount();
    System.out.println(totalAmount);
    /*
     * Gets the payable amount,
     * it deduct discount and add tax to the total amount of items in cart
     * Expected Output:
     * 1254
     */
    double payableAmount = cart.getPayableAmount();
    System.out.println(payableAmount);    
    /* apply a coupon code */
    cart.applyCoupon("IND50");
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:    0.00
     *                          Tax:    154.00
     *                          Total: 1254.00
     *
     */
    cart.printInvoice();
    cart.applyCoupon("IND10");
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:  110.00
     *                          Tax:    138.60
     *                          Total: 1128.60
     *
     */
    cart.printInvoice();
    cart.addToCart(new Item("Milk", 5, 30.00));
    /* 
     * print the items with the quanity, unit price, total amount
     * apply coupon if valid 
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00 
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     * Milk              5     30.00    150.00
     *                          Total: 1250.00
     *                          Disc%:  125.00
     *                          Tax:    157.50
     *                          Total: 1282.50
     *
     */
    cart.printInvoice();
  }
}