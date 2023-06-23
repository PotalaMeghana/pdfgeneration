<%-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="eStoreProduct.model.custCredModel" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>profile</title>
</head>
<style>
   /* CSS styles */
 body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
          header {
      background-color: #333;
      color: #fff;
      padding: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
       nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }
    
    nav ul li {
      display: inline;
    }
    
    nav ul li a {
      color: #fff;
      text-decoration: none;
      padding: 10px 20px;
    }
    
    nav ul li a:hover {
      background-color: #555;
    }
        
        h1 {
            color: #ffffff;
        }
        
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        
        form label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        form input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        form input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
<body>
    <header>
        <!-- Display the customer name -->
        <h1>Hey! ${cust != null ? cust.custName : ""}</h1>
        <nav>
            <ul>
                <li><a href="loggedIn">Home</a></li>
                <li><a href="#">&#128142; Orders</a></li>
                <li><a href="#">&#x1F381; Cupons</a></li>
                <li><a href="#">&#128722; Cart</a></li>
                <li><a href="#">&#10084; WishList</a></li>
                <li><a href="logout">LogOut</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div id="edit">
            <h1>Customer Profile</h1>
            <table>
                <tr>
                    <th>Customer ID</th>
                    <td>${cust != null ? cust.custId : ""}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${cust != null ? cust.custName : ""}</td>
                </tr>
                <tr>
                    <th>Mobile</th>
                    <td>${cust != null ? cust.custMobile : ""}</td>
                </tr>
                <tr>
                
                    <th>Location</th>
                    <td>${cust != null ? cust.custLocation : ""}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${cust != null ? cust.custEmail : ""}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${cust != null ? cust.custAddress : ""}</td>
                </tr>
                <tr>
                    <th>Shipping Address</th>
                    <td>${cust != null ? cust.custSAddress : ""}</td>
                </tr>
                <tr>
                    <th>Pincode</th>
                    <td>${cust != null ? cust.custPincode : ""}</td>
                </tr>
                <tr>
                    <th>Last Login Date</th>
                    <td>${cust != null ? cust.custLastLoginDate : ""}</td>
                </tr>
            </table>
            <button onclick="editprofile()">EDIT-PROFILE</button>
        </div>
    </main> 
<script>
function editprofile() {
    var ele = document.getElementById("edit");
    ele.innerHTML = `
    <form action="updateProfile" method="post">    
        <label>CustomerId:</label>
        <input type="text" id="custId" name="custId" value="${cust != null ? cust.custId : ""}" readonly><br>

        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="${cust != null ? cust.custName : ""}"><br>

        <label for="custMobile">Mobile:</label>
        <input type="text" id="custMobile" name="custMobile" value="${cust != null ? cust.custMobile : ""}"><br>

        <label for="custLocation">Location:</label>
        <input type="text" id="custLocation" name="custLocation" value="${cust != null ? cust.custLocation : ""}"><br>

        <label for="custAddress">Address:</label>
        <input type="text" id="custAddress" name="custAddress" value="${cust != null ? cust.custAddress : ""}"><br>

        <label for="custSAddress">Shipping Address:</label>
        <input type="text" id="custSAddress" name="custSAddress" value="${cust != null ? cust.custSAddress : ""}"><br>

        <label for="custPincode">Pincode:</label>
        <input type="text" id="custPincode" name="custPincode" value="${cust != null ? cust.custPincode : ""}"><br>

        <input type="submit" value="Update Profile">
    </form>`;
}
</script>
</body>
</html> --%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="eStoreProduct.model.custCredModel" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<style>
    /* CSS styles */
    * {
        box-sizing: border-box;
    }
    
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f2f2f2;
    }
    
    header {
        background-color: #333;
        color: #fff;
        padding: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    nav ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
    
    nav ul li {
        display: inline;
    }
    
    nav ul li a {
        color: #fff;
        text-decoration: none;
        padding: 10px 20px;
    }
    
    nav ul li a:hover {
        background-color: #555;
    }
    
    h1 {
        color: #ffffff;
    }
    
    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    
    th,
    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }
    
    button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    
    form label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
    }
    
    form input[type="text"] {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    
    form input[type="submit"] {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
</style>
 <style>
    /* CSS styles for the dashboard */
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f1f1f1;
    }
    .sidebar {
      background-color: #333;
      color: #fff;
      width: 200px;
      height: 100vh;
      position: fixed;
      top: 0;
      left: 0;
      padding: 20px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
    .sidebar ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
    }
    .sidebar li {
      margin-bottom: 10px;
    }
    .sidebar li a {
      color: #fff;
      text-decoration: none;
      display: block;
      padding: 10px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }
    .sidebar li a:hover {
      background-color: #555;
    }
    .user-info {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 20px;
    }
    .user-info img {
      width: 130px; /* Adjust the width as per your preference */
      height: 130px; /* Adjust the height as per your preference */
      border-radius: 50%;
      margin-bottom: 10px;
    }
    .user-info span {
      font-weight: bold;
      font-size: 18px;
      cursor: pointer;
      text-decoration: underline;
    }
    .logout-link {
      margin-top: auto;
      display: block;
    }
    .content {
      margin-left: 250px; /* Adjust this value to accommodate the sidebar width */
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      font-size: 24px;
      margin-bottom: 20px;
    }
    h2 {
      font-size: 20px;
      margin-bottom: 10px;
    }
    p {
      margin-bottom: 10px;
    }
    
    .user-details p {
      margin-bottom: 5px;
    }
    .user-details.show {
      display: block;
    }
    .user-details {
  display: none;
  margin: 20px auto; /* Added margin:auto for center alignment */
  max-width: 400px; /* Added max-width for responsive layout */
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  
}
    
    .dashboard {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
    }
    .dashboard p {
      border: 1px solid #ccc;
      padding: 10px;
      border-radius: 5px;
      background-color: #f5f5f5;
      margin: 0;
      flex-basis: 50%;
    }
  </style>
</head>
<body>
     <div class="sidebar">
         <div class="user-info">
              <img src="https://tse4.mm.bing.net/th?id=OIP.CUmXK7A3sWtCO2KP_yPztgAAAA&pid=Api&P=0&h=180" alt="User Icon">
               <h1>Hey! ${cust != null ? cust.custName : ""}</h1>
         </div>
         <ul>                
                <li><a href="#">&#128142; Orders</a></li>
                <li><a href="#">&#128722; Cart</a></li>
                <li><a href="#">&#10084; WishList</a></li>
                <li><a href="logout">LogOut</a></li>
                <li><a href="loggedIn">Home</a></li>
            </ul>
     </div>
     <div class="content" id="content"> 
       <div style="display: flex; justify-content: space-between; align-items: center;">
             <h1>Welcome to the the store</h1>
       </div>
         <div id="edit">
            <h1>Customer Profile</h1>
            <table>
                <tr>
                    <th>Customer ID</th>
                    <td>${cust != null ? cust.custId : ""}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${cust != null ? cust.custName : ""}</td>
                </tr>
                <tr>
                    <th>Mobile</th>
                    <td>${cust != null ? cust.custMobile : ""}</td>
                </tr>
                <tr>
                
                    <th>Location</th>
                    <td>${cust != null ? cust.custLocation : ""}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${cust != null ? cust.custEmail : ""}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${cust != null ? cust.custAddress : ""}</td>
                </tr>
                <tr>
                    <th>Pincode</th>
                    <td>${cust != null ? cust.custPincode : ""}</td>
                </tr>
                <tr>
                    <th>Shipping Address</th>
                    <td>${cust != null ? cust.custSAddress : ""}</td>
                </tr>
                <tr>
                    <th>Shipment Pincode</th>
                    <td>${cust != null ? cust.custSpincode : ""}</td>
                </tr>
                <tr>
                    <th>Last Login Date</th>
                    <td>${cust != null ? cust.custLastLoginDate : ""}</td>
                </tr>
            </table> 
            <button onclick="editprofile()">EDIT-PROFILE</button> 
        </div>
  </div>
  
        <%-- <h1>Hey! ${cust != null ? cust.custName : ""}</h1>
        <nav>
            <ul>
                <li><a href="loggedIn">Home</a></li>
                <li><a href="#">&#128142; Orders</a></li>
                <li><a href="#">&#x1F381; Cupons</a></li>
                <li><a href="#">&#128722; Cart</a></li>
                <li><a href="#">&#10084; WishList</a></li>
                <li><a href="logout">LogOut</a></li>
            </ul>
        </nav>
    <main>
         <div id="edit">
            <h1>Customer Profile</h1>
            <table>
                <tr>
                    <th>Customer ID</th>
                    <td>${cust != null ? cust.custId : ""}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${cust != null ? cust.custName : ""}</td>
                </tr>
                <tr>
                    <th>Mobile</th>
                    <td>${cust != null ? cust.custMobile : ""}</td>
                </tr>
                <tr>
                
                    <th>Location</th>
                    <td>${cust != null ? cust.custLocation : ""}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${cust != null ? cust.custEmail : ""}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${cust != null ? cust.custAddress : ""}</td>
                </tr>
                <tr>
                    <th>Shipping Address</th>
                    <td>${cust != null ? cust.custSAddress : ""}</td>
                </tr>
                <tr>
                    <th>Pincode</th>
                    <td>${cust != null ? cust.custPincode : ""}</td>
                </tr>
                <tr>
                    <th>Last Login Date</th>
                    <td>${cust != null ? cust.custLastLoginDate : ""}</td>
                </tr>
            </table> 
            <button onclick="editprofile()">EDIT-PROFILE</button> 
        </div>
    </main> --%>
    <script>
function editprofile() {
    var ele = document.getElementById("edit");
    ele.innerHTML = `
    <form action="updateProfile" method="post">    
        <label>CustomerId:</label>
        <input type="text" id="custId" name="custId" value="${cust != null ? cust.custId : ""}" readonly><br>

        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="${cust != null ? cust.custName : ""}"><br>

        <label for="custMobile">Mobile:</label>
        <input type="text" id="custMobile" name="custMobile" value="${cust != null ? cust.custMobile : ""}"><br>

        <label for="custLocation">Location:</label>
        <input type="text" id="custLocation" name="custLocation" value="${cust != null ? cust.custLocation : ""}"><br>

        <label for="custAddress">Address:</label>
        <input type="text" id="custAddress" name="custAddress" value="${cust != null ? cust.custAddress : ""}"><br>
        
        <label for="custPincode">Pincode:</label>
        <input type="text" id="custPincode" name="custPincode" value="${cust != null ? cust.custPincode : ""}"><br>

        <label for="custSAddress">Shipping Address:</label>
        <input type="text" id="custSAddress" name="custSAddress" value="${cust != null ? cust.custSAddress : ""}"><br>

        <label for="custsPincode">Shipment Pincode:</label>
        <input type="text" id="custsPincode" name="custSpincode" value="${cust != null ? cust.custSpincode : ""}"><br>

        <input type="submit" value="Update Profile">
    </form>`;
}
</script>
</body>
</html>