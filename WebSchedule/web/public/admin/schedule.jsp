<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

    <title>Schedule | Schedule Builder</title>

    <link rel='stylesheet' type='text/css' href='../../css/style.css' />
    <link rel='stylesheet' type='text/css' href='../../css/loader2.css' />
    <link rel='stylesheet' type='text/css' href='../../css/Buttons2.css' />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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

            <h2>Schedule Builder</h2>
            <div class="btn-container">
                <a id="run" href="#" class="btn-3d blue">Build Schedule</a>
            </div>

            <a href="DownloadFileServlet?file=schedule" class="buttonDownload" style="display: none;" id="link">Download Generated Schedules</a>

            <script>
                $("#run").click(function(e) {
                    e.preventDefault();
                    $.ajax({
                        type: 'GET',
                        url: '/BuildSchedule',
                        beforeSend: function () {
                            $("#run").hide();
                            $("#circle_container").css("display", "block");
                            $("#link").hide();
                        },
                        success: function (data) {
                            $("#run").show();
                            $("#circle_container").hide();
                            $("#link").show();

                        },
                        error: function () {
                            alert("Something went wrong try again later");
                        }
                    });
                });

            </script>
            <br>
            <br>
            <br>
            <br>
            <div id="circle_container" style="display: none;">
                <div id="stars"></div>
                <div id="load_wrapper">
                    <div id="sun"></div>
                    <div id="moon"></div>
                </div>
            </div>

            <script>
                function getRandom (size)
                {
                    return Math.floor(Math.random() * size);
                }

                //Creating the layers for the stars
                for (i = 2; i < 12; i++)
                {
                    $("#stars").append('<div class="star_layer" style="transform: translateZ(' + i + 'px) scale(' + (15 - i)/(15) +');"></div>')
                }

                //Creating the stars
                for (i = 0; i < 70; i++)
                {
                    $(".star_layer").eq(getRandom(10)).append('<div class="star"></div>');
                }

                updateStars();

                //Change stars every cycle
                setInterval(updateStars, 4000);

                //Randomising stars. Position and opacity is changed every cycle.
                function updateStars ()
                {
                    $(".star").each(function() {
                        $(this).css({"top": getRandom(200) + "px", "left": getRandom(200) + "px", "opacity": (20 + getRandom(50))/100});
                    });
                }
            </script>

        </div>
    </section>
</div>

</body>

</html>