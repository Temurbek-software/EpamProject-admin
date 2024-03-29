<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 7/12/2022
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registtion page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

    /* Reseting */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }

    body {
        background: #00E5FF;
        min-height: 100vh;
    }

    .wrapper {
        max-width: 850px;
        background-color: #fff;
        border-radius: 10px;
        position: relative;
        display: flex;
        margin: 50px auto;
        box-shadow: 0 8px 20px 0px #1f1f1f1a;
        overflow: hidden;
    }

    .wrapper .form-left {
        background: #3786bd;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        padding: 20px 40px;
        position: relative;
        width: 100%;
        color: #fff;
    }

    .wrapper h2 {
        font-weight: 700;
        font-size: 25px;
        padding: 5px 0 0;
        margin-bottom: 34px;
        pointer-events: none;
    }

    .wrapper .form-left p {
        font-size: 0.9rem;
        font-weight: 300;
        line-height: 1.5;
        pointer-events: none;
    }

    .wrapper .form-left .text {
        margin: 20px 0 25px;
    }

    .wrapper .form-left p span {
        font-weight: 700;
    }

    .wrapper .form-left input {
        padding: 15px;
        background: #fff;
        border-top-left-radius: 5px;
        border-bottom-right-radius: 5px;
        width: 180px;
        border: none;
        margin: 15px 0 50px 0px;
        cursor: pointer;
        color: #333;
        font-weight: 700;
        font-size: 0.9rem;
        appearance: unset;
        outline: none;
    }

    .wrapper .form-left input:hover {
        background-color: #f2f2f2;
    }

    .wrapper .form-right {
        padding: 20px 40px;
        position: relative;
        width: 100%;
    }

    .wrapper .form-right h2 {
        color: #3786bd;
    }

    .wrapper .form-right label {
        font-weight: 600;
        font-size: 15px;
        color: #666;
        display: block;
        margin-bottom: 8px;
    }

    .wrapper .form-right .input-field {
        width: 100%;
        padding: 10px 15px;
        border: 1px solid #e5e5e5;
        border-top-left-radius: 5px;
        border-bottom-right-radius: 5px;
        outline: none;
        color: #333;
    }

    .wrapper .form-right .input-field:focus {
        border: 1px solid #31a031;
    }


    .wrapper .option {
        display: block;
        position: relative;
        padding-left: 30px;
        margin-bottom: 12px;
        font-size: 0.95rem;
        font-weight: 900;
        cursor: pointer;
        user-select: none
    }

    .wrapper .option input {
        position: absolute;
        opacity: 0;
        cursor: pointer;
        height: 0;
        width: 0
    }

    .wrapper .checkmark {
        position: absolute;
        top: 0;
        left: 0;
        height: 18px;
        width: 18px;
        background-color: #fff;
        border: 2px solid #ddd;
        border-radius: 2px
    }

    .wrapper .option:hover input ~ .checkmark {
        background-color: #f1f1f1
    }

    .wrapper .option input:checked ~ .checkmark {
        border: 2px solid #e5e5e5;
        background-color: #fff;
        transition: 300ms ease-in-out all
    }

    .wrapper .checkmark:after {
        content: "\2713";
        position: absolute;
        display: none;
        color: #3786bd;
        font-size: 1rem;
    }

    .wrapper .option input:checked ~ .checkmark:after {
        display: block
    }

    .wrapper .option .checkmark:after {
        left: 2px;
        top: -4px;
        width: 5px;
        height: 10px
    }

    .wrapper .register {
        padding: 12px;
        background: #3786bd;
        border-top-left-radius: 5px;
        border-bottom-right-radius: 5px;
        width: 130px;
        border: none;
        margin: 6px 0 50px 0px;
        cursor: pointer;
        color: #fff;
        font-weight: 700;
        font-size: 15px;
    }

    .wrapper .register:hover {
        background-color: #3785bde0;
    }

    .wrapper a {
        text-decoration: none;
    }

    @media (max-width: 860.5px) {
        .wrapper {
            margin: 50px 5px;
        }
    }


    @media (max-width: 767.5px) {
        .wrapper {
            flex-direction: column;
            margin: 30px 20px;
        }

        .wrapper .form-left {
            border-bottom-left-radius: 0px;
        }


    }

    @media (max-width: 575px) {

        .wrapper {
            margin: 30px 15px;
        }

        .wrapper .form-left {
            padding: 25px;
        }

        .wrapper .form-right {
            padding: 25px;
        }
    }
</style>
<body>
<c:if test="${msg!=null}">
    <div class="alert alert-primary alert-dismissible fade show" role="alert">
        <strong>Hey! </strong><c:out value='${msg}'/>
    </div>
</c:if>
<div class="wrapper">

    <div class="form-left">
        <h2 class="text-uppercase">information</h2>
        <p>
            Nothing keeps the world on its toes quite as news does.
            Good news, bad news, sports news, current news, breaking news—however
            you read it, the news is the ultimate source of information in the technology age..
        </p>
        <p class="text">
            <span>Main point:</span>
            With over 600 million existing news
            blogs online, the competition is more than fierce.</p>

    </div>
    <form action="regis" method="post" class="form-right">
        <h2 class="text-uppercase">Registration!</h2>
        <input type="hidden" name="description" value=""/>
        <div class="row">
            <div class="col-sm-12 mb-3">
                <label>Company name</label>
                <input type="text" name="nameOfCompany" class="input-field">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 mb-3">
                <label>Address</label>
                <input type="text" name="address" class="input-field">
            </div>
        </div>
        <div class="mb-3">
            <label>Enter Email</label>
            <input type="email" class="input-field" name="email" required>
        </div>
        <div class="row">
            <div class="col-sm-6 mb-3">
                <label>Phone number</label>
                <input type="text" name="phoneNumber" class="input-field">
            </div>
            <div class="col-sm-6 mb-3">
                <label>Username</label>
                <input type="text" name="username" class="input-field">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 mb-3">
                <label>Password</label>
                <input type="password" name="password" id="pwd" class="input-field">
            </div>
            <%--            <div class="col-sm-6 mb-3">--%>
            <%--                <label>Current Password</label>--%>
            <%--                <input type="password" name="cpwd" id="cpwd" class="input-field">--%>
            <%--            </div>--%>
        </div>
        <%--        <div class="mb-3">--%>
        <%--            <label class="option">I agree to the <a href="#">Terms and Conditions</a>--%>
        <%--                <input type="checkbox"  checked>--%>
        <%--                <span class="checkmark"></span>--%>
        <%--            </label>--%>
        <%--        </div>--%>
        <div class="form-field">
            <button type="submit" value="Register" class="register">
                Send
            </button>
        </div>
    </form>
</div>
</body>
</html>
