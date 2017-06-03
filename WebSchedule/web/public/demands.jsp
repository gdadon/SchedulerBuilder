<!DOCTYPE html>
<html>

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

    <title>Schedule | Demands</title>

    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <link rel='stylesheet' type='text/css' href='../css/style.css' />
    <script type='text/javascript' src='../js/submitBtn.js'></script>

</head>

<body>
<div id="page-wrap">

    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>

    <section id="main-content">
        <div id="guts">

            <h2>Add Demands</h2>

            <div class="demands">
                <select id="day" class="day">
                    <option selected="true" disabled>Day</option>
                    <option value="1" class="optOdd">Sunday</option>
                    <option value="2">Monday</option>
                    <option value="3" class="optOdd">Tuesday</option>
                    <option value="4">Wednesday</option>
                    <option value="5" class="optOdd">Thursday</option>
                    <option value="6">Friday</option>
                </select>
                <select id="start" class="start">
                    <option selected="true" disabled>Start</option>
                    <option value="8" class="optOdd">8:00</option>
                    <option value="9" >9:00</option>
                    <option value="10" class="optOdd">10:00</option>
                    <option value="11">11:00</option>
                    <option value="12" class="optOdd">12:00</option>
                    <option value="13">13:00</option>
                    <option value="14" class="optOdd">14:00</option>
                    <option value="15">15:00</option>
                    <option value="16" class="optOdd">16:00</option>
                    <option value="17">17:00</option>
                    <option value="18" class="optOdd">18:00</option>
                    <option value="19">19:00</option>
                    <option value="20" class="optOdd">20:00</option>
                </select>
                <select id="end" class="end">
                    <option selected="true" disabled>End</option>
                    <option value="8" class="optOdd">8:00</option>
                    <option value="9" >9:00</option>
                    <option value="10" class="optOdd">10:00</option>
                    <option value="11">11:00</option>
                    <option value="12" class="optOdd">12:00</option>
                    <option value="13">13:00</option>
                    <option value="14" class="optOdd">14:00</option>
                    <option value="15">15:00</option>
                    <option value="16" class="optOdd">16:00</option>
                    <option value="17">17:00</option>
                    <option value="18" class="optOdd">18:00</option>
                    <option value="19">19:00</option>
                    <option value="20" class="optOdd">20:00</option>
                </select>
                <input class="c_reason" id="reason" type="text" name="reason" placeholder="Enter Your Reason...">
                <button class="btn" type="button" onclick="submitDemand()">Submit</button>
            </div>

            <div id="demands_list">
                <ul class="d_list" id="my_d_list">
                    <!--<li class="close-btn">Sunday | 10:00 - 13:00 | Yoga course | STATUS</li>-->

                    <!--<li class="close-btn">Monday | 12:00 - 14:00 | Cyber seminar | STATUS</li>-->

                </ul>
            </div>
        </div>
    </section>
</div>
</body>

</html>