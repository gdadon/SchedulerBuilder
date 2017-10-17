<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

    <title>Schedule | Demands</title>

    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/submitBtn.js"></script>
    <link rel='stylesheet' type='text/css' href='../../css/style.css' />
    <link rel='stylesheet' type='text/css' href='../../css/Buttons2.css' />
</head>

<body>
<div id="page-wrap">

    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>
    <h3 id="demandError">${error}</h3>

    <section id="main-content">
        <div id="guts">

            <h2>Filter Demands</h2>

            <form action="aDemands" method="post">
                <div class="demands">
                    <select name="day" id="day" class="day">
                        <option value="" selected="true" disabled>Day</option>
                        <option value="1" ${1 == day ? 'selected="selected"' : ''} class="optOdd">Sunday</option>
                        <option value="2" ${2 == day ? 'selected="selected"' : ''}>Monday</option>
                        <option value="3" ${3 == day ? 'selected="selected"' : ''} class="optOdd">Tuesday</option>
                        <option value="4" ${4 == day ? 'selected="selected"' : ''}>Wednesday</option>
                        <option value="5" ${5 == day ? 'selected="selected"' : ''}class="optOdd">Thursday</option>
                        <option value="6" ${6 == day ? 'selected="selected"' : ''}>Friday</option>
                    </select>
                    </select>
                    <input class="c_reason" id="reason" type="text" name="reason" value="${reason}" placeholder="Lecture...">
                    <%--<button class="btn" type="button" onclick="submitDemand()">Submit</button>--%>
                    <button class="btn" type="submit" value="Demands">Search</button>
                </div>
            </form>


            <table id="myTable">
                <tr class="header">
                    <th style="width: 30%">Name</th>
                    <th style="width: 50%">Details</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                <c:forEach var="demandArr" items="${demandsList}">
                    <c:forEach var="demand" items="${demandArr.value}">
                        <tr>
                            <td class="teacher_name">
                                <c:out value="${demandArr.key}" />
                            </td>
                            <td class="dtime"><c:out value= "${demand.dayStr} | ${demand.start}:00 - ${demand.end}:00 | ${demand.reason}" /></td>
                            <TD class = "select">
                                <select class="selectStatus">
                                    <%--<option value="Pending" ${demand.status.name == 'Pending' ? 'selected="selected"' : ''} >Pending</option>--%>
                                    <%--<option value="Approved" ${demand.status.name == 'Approved' ? 'selected="selected"' : ''}>Approved</option>--%>
                                    <%--<option value="Declined" ${demand.status.name == 'Declined' ? 'selected="selected"' : ''}>Declined</option>--%>
                                    <option value="0" ${demand.status.name == 'Pending' ? 'selected="selected"' : ''} >Pending</option>
                                    <option value="1" ${demand.status.name == 'Approved' ? 'selected="selected"' : ''}>Approved</option>
                                    <option value="2" ${demand.status.name == 'Declined' ? 'selected="selected"' : ''}>Declined</option>
                                </select>
                            </TD>
                            <td>
                                <%--<button type="button" class="btn pri" onclick="changeStyle(this)">Save</button>--%>
                                <button type="button" id="saveStatus" class="btn pri">Save</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            <script>
                $("#saveStatus").click(function() {
                    var $row = $(this).closest("tr");    // Find the row
                    console.log($row);
                    var $name = $row.find(".teacher_name").text(); // Find the text
                    console.log($name);
                    var $time = $row.find(".dtime").text().split(/([a-zA-Z]*day)|(\d{1,2})/); // Find the text
                    var time2 = [$time[1], $time[5], $time[11]];
                    console.log(time2);
                    var $selVal = $('.select').find(":selected").val();
                    console.log("status:" + $selVal);
                    $('#saveStatus').addClass("btn pri ico");
//                    $('#saveStatus').className = "btn pri ico";
                    $('#saveStatus').text("Saved");
                });

                function changeStyle(btn){
                    var tblrw = btn.closest("tr");
                    var cell1 = tblrw.cells[0];
                    var cell2 = tblrw.cells[1];
                    var value1 = cell1.innerHTML;
                    var value2 = cell2.innerHTML;
//                    console.log(value1 + value2);
                    var val3 = tblrw.cells[2];
                    console.log(val3);
                    val3 = val3.getElementsByClassName("selectStatus");
//                    console.log(val3);
                    var status = val3.getPropertyValue();
                    console.log(status);
                    btn.className = "btn pri ico";
                    btn.innerText = "Saved";
                }
            </script>

        </div>
    </section>
</div>
</body>

</html>