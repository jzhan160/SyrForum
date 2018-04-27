<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="Pages/LoginAndRegister/css/styles.css">
</head>
<body>
<div id="login_frame">
    <h1>Syracuse Forum </h1>

    <!------------------------- Login Form --------------------------->

    <div>
        <button name = "login_button" onclick="document.getElementById('LogIn').style.display='block'" style="width:200px;">Login</button>
    </div>

    <div id="LogIn" class="login_modal">
        <form class="login_modal-content login_animate"
              action="<%=request.getContextPath()%>/DispatcherServlet?method=login" method="post">
            <div class="login_imgcontainer">
                <span onclick="document.getElementById('LogIn').style.display='none'" class="login_close"
                      title="Close Modal">&times;</span>
            </div>

            <div class="login_container">
                <c:if test="${requestScope.loginMsg != 'error'}">  </c:if>
                <c:if test="${requestScope.loginMsg == 'error' }">
                    <div style="color:#FF3030" style="font-size:10px"> The user name or password is invalid! </div>
                </c:if>
                <label><b>Username</b></label>
                <input type="login_text" placeholder="Enter Username" name="userName" required>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <span class="login_psw">Forgot <a href="#">password?</a></span>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>

                <div class="login_container">
                    <button type="submit" class="login_subbtn">Login</button>
                    <button type="button" onclick="document.getElementById('LogIn').style.display='none'"
                            class="login_cancelbtn">Cancel
                    </button>
                </div>
            </div>
        </form>
    </div>
        <c:if test="${loginMsg == 'error'}">
            <script>
                document.getElementById('LogIn').style.display='block';

            </script>
        </c:if>

    <script>
        // Get the modal
        var modal = document.getElementById('LogIn');

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>


    <!--------------------- Signup Form ------------------------------>
    <div style="text-align:center;">
        <button onclick="document.getElementById('SignUp').style.display='block'" style="width:200px;">Sign Up</button>
    </div>
    <div id="SignUp" class="login_modal">
       
        <form class="login_modal-content login_animate"
              action="<%=request.getContextPath()%>/DispatcherServlet?method=register" method="post">
              <span onclick="document.getElementById('SignUp').style.display='none'" class="signup_close" title="Close Modal">&times;</span>

        <div class="login_container ">
            <c:if test="${requestScope.registerMsg != 'error'}">  </c:if>
            <c:if test="${requestScope.registerMsg == 'error' }">
                <div style="color:#FF3030" style="font-size:10px"> The account has already existed! </div>
            </c:if>
            <label><b>UserName</b></label>
            <input type="signup_text" placeholder="Enter UserName: only letters and numbers" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" 
            onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" name="userName" required>

            <label><b>Email</b></label>
            <input type="signup_text" placeholder="Enter Email" name="email" required>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <label><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

            <label><b>Gender</b></label> <br />
			<select id="gender" name="gender" required>
			<option>Select one</option> 
			<option>Male</option>
			<option>Female</option>
			<option>Not specified</option>
			</select>

            <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

            <div class="signup_clearfix">
                <button type="submit" class="signup_signupbtn">Sign Up</button>
                <button type="button" onclick="document.getElementById('SignUp').style.display='none'"
                        class="signup_cancelbtn">Cancel
                </button>

            </div>
        </div>
        </form>
    </div>
    <c:if test="${registerMsg == 'error'}">
        <script>
            document.getElementById('SignUp').style.display='block';

        </script>
    </c:if>
    <script>
        // Get the modal
        var modal = document.getElementById('SignUp');
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

</div>

</body>
</html>
