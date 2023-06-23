<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="eStoreProduct.utility.ProductStockPrice" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <style>
    .not-available {
        color: red;
    }
    .available {
        color: green;
    }
</style>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    
<script>
function buynow()
{
	  console.log("buy now");
	        var notAvailable = $(".not-available");
	        if (notAvailable.length > 0) {
	            alert("Please check the availability of all products before placing the order!");
	        }   
	        else{
	  	window.location.href="buycartitems";  
	        }
	    }
    function checkPincodeAvailability(pincode, productId) {
        console.log("Checking pincode availability for Product ID: " + productId);
        
        $.ajax({
            type: "POST",
            url: "checkPincode",
            data: { pincode: pincode },
            success: function(response) {
                var availabilityElement = $("#availability-" + productId);
                
                if (response !== "false") {
                    availabilityElement.text("Available").removeClass("not-available").addClass("available");
                } else {
                    availabilityElement.text("Not Available").removeClass("available").addClass("not-available");
                }
            },
            error: function(error) {
                console.error(error);
            }
        });
    }

    $(document).ready(function() {
        // Automatically call the function for each input tag with a class of 'custPincode'
        $(".custPincode").each(function() {
            var pincode = $(this).val();
            var productId = $(this).attr("data-product-id");
            console.log("Automatically");
            checkPincodeAvailability(pincode, productId);
        });
    });
    function updateQuantity(input) {
        var quantity = input.value;
        console.log(quantity+"qnty!!!!!!!!!!!");
        var productId = input.getAttribute('data-product-id');
        console.log("qty in updateqty method "+quantity);
        console.log("product no=" + productId);
        $.ajax({
            url: 'updateQuantity',
            method: 'POST',
            data: { productId: productId, quantity: quantity },
            success: function(response) {
                console.log("response of updateqty  "+response);
                $("#cst").html("Total Cost: " + response);
            },
            error: function(xhr, status, error) {
                console.log('AJAX Error: ' + error);
            }
        });
    }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Cart</h2>
        <div class="row mt-4">
            <%-- Iterate over the products and render the HTML content --%>
            <%
                List<ProductStockPrice> products = (List<ProductStockPrice>) request.getAttribute("products");
				//double totalcost=0.0;
                for (ProductStockPrice product : products) {
            %>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100">
                    <img class="card-img-top" src="<%= product.getImage_url() %>" alt="<%= product.getProd_title() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getProd_title() %></h5>
                        <p class="card-text"><%= product.getProd_desc() %></p>
                        <p class="card-text"><%= product.getPrice() %></p>
                        <label>Qty:</label>
                        <input type="number" class="btn btn-primary qtyinp input-width" id="qtyinp" value="<%=product.getQuantity() %>" min="1" onchange="updateQuantity(this)" data-product-id="<%= product.getProd_id() %>">
                        <br>
                        <p>Pincode Availability:</p>
                        <p id="availability-<%= product.getProd_id() %>"></p>
                        <input type="text" id="custPincode-<%= product.getProd_id() %>" class="custPincode" name="custPincode" value="${cust != null ? cust.custSpincode : ""}" data-product-id="<%= product.getProd_id() %>">
                       <button class="btn btn-primary" onclick="checkPincodeAvailability($('#custPincode-<%= product.getProd_id() %>').val(), <%= product.getProd_id() %>);">Check</button>
                        <br><br>
                        <button class="btn btn-primary removeFromCart" data-product-id="<%= product.getProd_id() %>">Remove from Cart</button>
                        <button class="btn btn-secondary addToWishlistButton" data-product-id="<%= product.getProd_id() %>">Add to Wishlist</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <div id="cont">
    <form id="shipment-form">
        <p id="ship"></p>
        <table class="shipment-table">
            <tr>
                <td>Delivery To:</td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" id="custName" name="custName" value="${cust != null ? cust.custName : ""}" readonly></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" id="custSAddress" name="custSAddress" value="${cust != null ? cust.custSAddress : ""}"></td>
            </tr>
            <tr>
                <td>Pincode:</td><td>
<input type="text" id="custsPincode" name="custSpincode" value="${cust != null ? cust.custSpincode : ""}" onchange="checkPincodeAvailability(this.value, ${productId})">
         </td></tr>   <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>
    <div align="center" container mt-3">
        <div id="cst">
            <p align="center">Total Cost=${cartcost}</p>
        </div>
        <button class="btn btn-primary BuyNow" onclick="buynow()">Place Order</button>
    </div>
    <script>
$(document).ready(function() {
    $('#shipment-form').submit(function(e) {
        e.preventDefault();
        var submitButton = $(this).find('input[type="submit"]');
        console.log("shipment address");

        var name = $("#name").val();
        var add = $("#custSAddress").val();
        var pin = $("#custsPincode").val(); // Corrected id here
        console.log(name);

        $.ajax({
            type: 'POST',
            url: 'updateshipment',
            data: { custSAddress: add, custSpincode: pin },
            success: function(response) {
                console.log(response);
                if(response==="Saved")
                	{
                submitButton.val("Saved");
                	}
                else
                	{
                	alert("Shipment is Not available for this Address");
                	}
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});

</script>
</body>
</html>
