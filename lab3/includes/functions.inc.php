
<?php

/*
 *  echo the passed information as a table row.
 *  Use the number_format() function to format the currency values
 *  with two decimal places.
 **/
function outputOrderRow($file, $title, $quantity, $price) {
      $total=number_format($price*$quantity,2);
      $price=number_format($price,2);
      echo "<tr>
      <td><img src='images/books/tinysquare/$file'></td>
      <td class='mdl-data-table__cell--non-numeric'>$title</td>
      <td>$quantity</td>
      <td>\$$price</td>
      <td>\$$total</td>
    </tr>";
  }

/*
 *  This function calculates and returns subTotal
 **/
function calculateSubtotal() {
  $subTotal = 0;
  global $price1, $price2, $price3, $price4,
      $quantity1, $quantity2, $quantity3, $quantity4;

  for($i = 1; $i < 5; $i++) {

    $subTotal += ${"price" . $i} * ${"quantity" . $i};
  }

  return $subTotal;
}

/*
 *  This function calculates shipping cost
 *  if subtotal greater than or equal to $10000
 *      shipping value is $100
 *  else
 *      shipping value is $200
 **/
function calculateShipping() {
  if (calculateSubtotal()>=100){
    return 100;
  }
  else return 200;
}

/*
 * calculates grandTotal by adding subtotal to shipping cost
 **/
function calculateGrandTotal() {
  return calculateSubtotal() + calculateShipping();
}
