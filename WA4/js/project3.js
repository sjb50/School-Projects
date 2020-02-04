// *************************Setting up Browers selectors*******************************
var url = ('http://www.randyconnolly.com/funwebdev/services/visits/browsers.php');
$.get(url, function(data, status) {
  if (status == "success") {
    var select = $('#filterBrowser');
    for (count = 0; count < data.length; count++) {
      var opt = $('<option>' + data[count].name + "</option>");
      opt.attr("value", data[count].name);
      select.append(opt);
    }
  }
});
// *********************************************************************************************

// *************************Setting up OS selectors*******************************
var url = ('http://www.randyconnolly.com/funwebdev/services/visits/os.php');
var param = "iso" + $('#filterOS').val();
var osSystems=[];
$.get(url, param, function(data, status) {
  if (status == "success") {
    var select = $('#filterOS');
    for (count = 0; count < data.length; count++) {
      osSystems[count]=data[count].name;
      console.log(osSystems[count]);
      var opt = $('<option>' + data[count].name + "</option>");
      opt.attr("value", data[count].name);
      select.append(opt);
    }
  }
});
// *********************************************************************************************

// *************************Setting up country selectors*******************************
var countries = [];
var url = ('http://www.randyconnolly.com/funwebdev/services/visits/countries.php?continent=EU');
var param = "iso" + $('#filterCountry').val();
$.get(url, param, function(data, status) {
  if (status == "success") {
    var select = $('#filterCountry');
    for (count = 0; count < data.length; count++) {
      countries[count] = data[count].iso;
      var opt = $('<option>' + data[count].name + "</option>");
      opt.attr("value", data[count].Country);
      select.append(opt);
    }
  }
});
// *********************************************************************************************

// *****************************Sets up tables**************************************
var url = ('http://www.randyconnolly.com/funwebdev/services/visits/visits.php?continent=EU&month=1&limit=100');
$.get(url, function(data, status) {
  if (status == "success") {
    var select = $('#visitsBody');
    for (count = 0; count < data.length; count++) {
      var opt = $('<tr><td>' + data[count].id + "</td><td>" + data[count].visit_time + "</td><td>" + data[count].country_code +
        "<td>" + data[count].browser + "</td><td>" + data[count].operatingSystem + "</td></td></tr>");
      opt.attr("value", count);
      select.append(opt);
    }
  }
  // *********************************Filters out information******************************************
  $("select").on("change", function(e) {
    $("#visitsBody").empty();
    $.grep(data, function(n, i) {
      if ((n.operatingSystem == $("#filterOS").val() || $('#filterOS').val() == 0) &&
        (n.browser == $("#filterBrowser").val() || $('#filterBrowser').val() == 0) &&
        (n.country == $("#filterCountry").val() || $('#filterCountry').val() == 0)) {
        var select = $('#visitsBody');
        var opt = $('<tr><td>' + data[i].id + "</td><td>" + data[i].visit_time + "</td><td>" + data[i].country_code +
          "<td>" + data[i].browser + "</td><td>" + data[i].operatingSystem + "</td></td></tr>");
        opt.attr("value", i);
        select.append(opt);
      }
    })
  })
  // *********************************************************************************************
  google.charts.load('current', {
    'packages': ['corechart']
  });
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {
    var chartData = new google.visualization.DataTable();
    chartData.addColumn('string', 'Browers');
    chartData.addColumn('number', 'Howmany');
    chartData.addRows([
      ['Unknown', countBrowsers("Unknown")],
      ['Chrome', countBrowsers("Chrome")],
      ['FireFox', countBrowsers("Firefox")],
      ['Internet Exployer', countBrowsers("Internet Explorer")],
      ['Edge', countBrowsers("Edge")],
      ['Safari', countBrowsers("Safari")],
      ['Mobile Chrome', countBrowsers("Mobile Chrome")],
      ['Opera', countBrowsers("Opera")]
    ]);

    function countBrowsers(browser) {
      var count = 0;
      $.grep(data, function(n, i) {
        if (browser == data[i].browser) {
          count++;
        }
      })
      return count;
    }

    var options = {
      'width': 500,
      'height': 400
    };
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(chartData, options);
  }

  google.charts.load('current', {
    'packages': ['geochart'],
    // Note: you will need to get a mapsApiKey for your project.
    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
  });
  google.charts.setOnLoadCallback(drawRegionsMap);

  function drawRegionsMap() {
    var geoData = google.visualization.arrayToDataTable(arrayOfCountrys())

    function arrayOfCountrys() {
      var array = [];
      array.push(["Country", "Popularity"]);
      var array2;
      for (count = 0; count < countries.length; count++) {
        array2 = [countries[count], countCountry(countries[count])];
        array.push(array2);
      }
      return array;
    }

    function countCountry(country) {
      var count = 0;
      $.grep(data, function(n, i) {
        if (country == data[i].country_code) {
          count++;
        }
      })
      return count;
    }

    var options = {};

    var chart = new google.visualization.GeoChart(document.getElementById('geochart'));

    chart.draw(geoData, options);
  }
  /******************************************************************/
  google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart1);
    function drawChart1() {
      var chartData = google.visualization.arrayToDataTable(arrayOfOS());

      function arrayOfOS() {
        var array = [];
        array.push(["OS", "Popularity"]);
        var array2;
        for (count = 0; count < osSystems.length; count++) {
          array2 = [osSystems[count], countOS(osSystems[count])];
          array.push(array2);
        }
        return array;
      }

      function countOS(country) {
        var count = 0;
        $.grep(data, function(n, i) {
          if (country == data[i].operatingSystem) {
            count++;
          }
        })
        return count;
      }

      var view = new google.visualization.DataView(chartData);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       1]);

      var options = {
        title: "",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart"));
      chart.draw(view, options);
  }
});
