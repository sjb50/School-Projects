<?php
  include './includes/data.inc.php';
  include './includes/functions.inc.php';
?>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <title>Chapter 7</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue_grey-orange.min.css">

    <link rel="stylesheet" href="css/styles.css">
    <script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>
  </head>

  <body>

    <!-- The drawer is always open in large screens. The header is always shown,
  even in small screens. -->
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer
            mdl-layout--fixed-header">

      <?php include './includes/header.inc.php'; ?>

      <div class="mdl-layout__drawer mdl-color--blue-grey-800 mdl-color-text--blue-grey-50">
        <div class="profile">
          <img src="images/profile.jpg" class="avatar">
          <h4>John Locke</h4>
          <span>johnlocke@example.com</span>
        </div>

        <?php include './includes/left.inc.php'; ?>

      </div>



      <main class="mdl-layout__content mdl-color--grey-50">
        <header class="mdl-color--blue-grey-200">
          <h4>Order Summaries</h4>
          <p>Examine your customer orders</p>
        </header>
        <section class="page-content">

          <div class="mdl-grid">

            <!-- mdl-cell + mdl-card -->
            <div class="mdl-cell mdl-cell--3-col card-lesson mdl-card  mdl-shadow--2dp">
              <div class="mdl-card__title mdl-color--deep-purple mdl-color-text--white">
                <h2 class="mdl-card__title-text">My Orders</h2>
              </div>
              <div class="mdl-card__supporting-text">
                <ul class="mdl-list">
                  <?php for ($row = 500; $row <= 540; $row+=10): ?>
                    <li><a href="#">Order #<?= $row; ?></a></li>
                  <?php endfor; ?>
                </ul>
              </div>
            </div> <!-- / mdl-cell + mdl-card -->




            <!-- mdl-cell + mdl-card -->
            <div class="mdl-cell mdl-cell--9-col card-lesson mdl-card  mdl-shadow--2dp">
              <div class="mdl-card__title mdl-color--orange">
                <h2 class="mdl-card__title-text">Selected Order: #520</h2>
              </div>
              <div class="mdl-card__supporting-text">
                <table class="mdl-data-table  mdl-shadow--2dp">
                  <caption>Customer: <strong>Mount Royal University</strong></caption>
                  <thead>
                    <tr>
                      <th>Cover</th>
                      <th class="mdl-data-table__cell--non-numeric">Title</th>
                      <th>Quantity</th>
                      <th>Price</th>
                      <th>Amount</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr class="totals">
                      <td colspan="4">Subtotal</td>
                      <td><?php echo "\$" . number_format(calculateSubtotal(),2) ?>0</td>
                    </tr>
                    <tr class="totals">
                      <td colspan="4">Shipping</td>
                      <td>$100.00</td>
                    </tr>
                    <tr class="grandtotals">
                      <td colspan="4">Grand Total</td>
                      <td><?php echo "\$" . number_format(calculateGrandTotal(),2)?></td>
                    </tr>
                  </tfoot>
                  <tbody>
                    <?php for ($i=1;$i<5;$i++){
                      outputOrderRow(${"file".$i},${"title".$i},${"quantity".$i},${"price".$i});
                    }?>
                  </tbody>

                </table>
              </div>

            </div> <!-- / mdl-cell + mdl-card -->




          </div> <!-- / mdl-grid -->


        </section>
      </main>


    </div>

  </body>

</html>
