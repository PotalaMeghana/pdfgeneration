<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="eStoreProduct.model.orderModel" %>
<%@ page import="eStoreProduct.utility.ProductStockPrice" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Invoice</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
    <style>
        /* Common styles */

        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 20px;
        }

        .invoice-title {
            margin-bottom: 20px;
        }

        .invoice-title h2 {
            font-size: 28px;
        }

        .invoice-title h3 {
            font-size: 16px;
        }

        hr {
            border: 0;
            height: 1px;
            background: #ccc;
            margin-bottom: 20px;
        }

        .row {
            margin-bottom: 20px;
        }

        .col-xs-6 {
            width: 50%;
        }

        .address strong {
            font-weight: bold;
        }

        .table {
            margin-bottom: 0;
        }

        .table th,
        .table td {
            padding: 8px;
            vertical-align: top;
        }

        .table th {
            font-weight: bold;
            background-color: #f9f9f9;
        }

        .text-right {
            text-align: right;
        }

        /* Styles specific to print media */

        @media print {
            button.print-button {
                display: none;
            }
        }
    </style>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 20px;
        }

        .invoice-title {
            margin-bottom: 20px;
        }

        .invoice-title h2 {
            font-size: 28px;
        }

        .invoice-title h3 {
            font-size: 16px;
        }

        hr {
            border: 0;
            height: 1px;
            background: #ccc;
            margin-bottom: 20px;
        }

        .row {
            margin-bottom: 20px;
        }

        .col-xs-6 {
            width: 50%;
        }

        .address strong {
            font-weight: bold;
        }

        .table {
            margin-bottom: 0;
        }

        .table th,
        .table td {
            padding: 8px;
            vertical-align: top;
        }

        .table th {
            font-weight: bold;
            background-color: #f9f9f9;
        }

        .text-right {
            text-align: right;
        }
    </style>
</head>
<body>
<%
orderModel order=(orderModel)session.getAttribute("vieworder");
%>
<script>
function printInvoice() {
    var printButton = document.getElementById('printButton');
    printButton.style.display = 'none'; // Hide the button
    window.print();
    printButton.style.display = 'block'; // Restore the button after printing
}
</script>
<div class="container">
    <div class="invoice-title">
        <h2>Invoice</h2>
        <h3>#By SLAM Store</h3>
    </div>
    <hr>
    <div class="row">
        <div class="col-xs-6">
            <address>
                <strong>Billed To:</strong><br>
                <p><%=order.getCustname()%></p>
                <p><%=order.getMobile()%></p>
                <p><%=order.getLocation()%></p>
            </address>
        </div>
        <div class="col-xs-6 text-right">
            <address>
                <strong>Shipped To:</strong><br>
                <p><%=order.getCustname()%></p>
                <p><%=order.getMobile()%></p>
                <p><%=order.getSaddress()%></p>
                <p><%=order.getSpincode() %></p>
            </address>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6">
            <address>
                <strong>Payment Method:</strong><br>
                Online
                <p>Payment ID: <%=order.getOrderpayid() %></p>
            </address>
        </div>
        <div class="col-xs-6 text-right">
            <address>
                <strong>Order Date:</strong><%=order.getOrderdate()%><br>
                <br><br>
            </address>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>Order summary</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <th><strong>ITEMID</strong></th>
                                    <th class="text-center"><strong>ITEM</strong></th>
                                    <th class="text-center"><strong>ProductGSTCID</strong></th>
                                    <th class="text-right"><strong>PRICE</strong></th>
                                </tr>
                            </thead>
                            <tbody>
                               <%--  <% for(ProductStockPrice p : products) { %>
                                <tr>
                                    <td><%=p.getProd_id()%></td>
                                    <td class="text-center"><%=p.getProd_title()%></td>
                                    <td class="text-center"><%=p.getProd_gstc_id()%></td>
                                    <td class="text-right"><%=p.getPrice()%></td>
                                </tr>
                                <% } %> --%>
                                <tr>
                                    <td>Total:</td>
                                    <td><%=order.getOrdertotal() %></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div>
            
<button onclick="printInvoice()" id="printButton" >Print Invoice</button>          </div>
        </div>
    </div>
</div>

</body>
</html>