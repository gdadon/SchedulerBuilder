<!DOCTYPE html>
<html>

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

    <title>Schedule | Reports</title>

    <link rel='stylesheet' type='text/css' href='../../css/style.css' />
    <link rel='stylesheet' type='text/css' href='../../css/Buttons2.css' />

    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="../../js/submitBtn.js"></script>
</head>

<body>

<div id="page-wrap">

    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>

    <section id="main-content">
        <div id="guts">

            <a href="DownloadFileServlet?file=template" class="buttonDownload" >Download Templates</a>



            <form method="post" action="Upload" enctype="multipart/form-data">

                <h2><br/>
                    Select files to upload:<br/>
                </h2><br/>

                <div class="wrapper">
                    <div class="file-upload">
                        <input type="file" name="uploadFileClassR" />Class Report
                        <i class="fa fa-arrow-up"></i>
                    </div>
                </div>

                <div class="wrapper">
                    <div class="file-upload">
                        <input type="file" name="uploadFileCourseR" />Course Report
                        <i class="fa fa-arrow-up"></i>
                    </div>
                </div>

                <div class="wrapper">
                    <div class="file-upload">
                        <input type="file" name="uploadFileTR" />Teachers Report
                        <i class="fa fa-arrow-up"></i>
                    </div>
                </div>



                <div class="wrapper">
                    <div class="file-submit">
                        <input type="submit" value="Upload" />Submit Files
                        <i class="fa fa-upload"></i>
                    </div>
                </div>

            </form>

            <br>
            <h2>
              ${message}
            </h2>

        </div>
    </section>

</div>
</body>

</html>