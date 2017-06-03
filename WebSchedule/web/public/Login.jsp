<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/LoginStyle.css"/>
    <title>Scheduler Builder Login</title>
</head>
<body>
    <div class="container">
        <img src="../images/man.png"/>
        <form action="Login" method="post">
            <div class="form-input">
                <input type="text" name="username" placeholder="Enter User Name">
            </div>
            <div class="form-input">
                <input type="password" name="password" placeholder="Enter Password">
            </div>
            <input type="submit" name="submit" value="LOGIN" class="btn-login"/>
        </form><br>
        <a href="#">Forget password?</a>
        <p id="error">${errorString}</p>
    </div>
    <footer>
        <p>Login Form powered by <a class="footer-text" href="#">LGTM Creative</a></p>
    </footer>
</body>
</html>