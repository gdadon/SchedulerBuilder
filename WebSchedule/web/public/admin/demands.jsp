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
    <link rel='stylesheet' type='text/css' href='../../css/style2.css' />
</head>

<body>
<div id="page-wrap">

    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>
    <h3 id="demandError">${error}</h3>

    <section id="main-content">
        <div id="guts">

            <h2>Add Demands</h2>

            <form action="aDemands" method="post">
                <div class="demands">
                    <select required name="day" id="day" class="day">
                        <option value="" selected="true" disabled>Day</option>
                        <option value="1" ${1 == day ? 'selected="selected"' : ''} class="optOdd">Sunday</option>
                        <option value="2" ${2 == day ? 'selected="selected"' : ''}>Monday</option>
                        <option value="3" ${3 == day ? 'selected="selected"' : ''} class="optOdd">Tuesday</option>
                        <option value="4" ${4 == day ? 'selected="selected"' : ''}>Wednesday</option>
                        <option value="5" ${5 == day ? 'selected="selected"' : ''}class="optOdd">Thursday</option>
                        <option value="6" ${6 == day ? 'selected="selected"' : ''}>Friday</option>
                    </select>
                    <select required name="start" id="start" class="start">
                        <option value="" selected="true" disabled>Start</option>
                        <option value= "8" ${8 == start ? 'selected="selected"' : ''} class="optOdd">8:00</option>
                        <option value= "9" ${9 == start ? 'selected="selected"' : ''} >9:00</option>
                        <option value="10" ${10 == start ? 'selected="selected"' : ''} class="optOdd">10:00</option>
                        <option value="11" ${11 == start ? 'selected="selected"' : ''} >11:00</option>
                        <option value="12" ${12 == start ? 'selected="selected"' : ''} class="optOdd">12:00</option>
                        <option value="13" ${13 == start ? 'selected="selected"' : ''} >13:00</option>
                        <option value="14" ${14 == start ? 'selected="selected"' : ''} class="optOdd">14:00</option>
                        <option value="15" ${15 == start ? 'selected="selected"' : ''} >15:00</option>
                        <option value="16" ${16 == start ? 'selected="selected"' : ''} class="optOdd">16:00</option>
                        <option value="17" ${17 == start ? 'selected="selected"' : ''} >17:00</option>
                        <option value="18" ${18 == start ? 'selected="selected"' : ''} class="optOdd">18:00</option>
                        <option value="19" ${19 == start ? 'selected="selected"' : ''} >19:00</option>
                        <option value="20" ${20 == start ? 'selected="selected"' : ''} class="optOdd">20:00</option>
                    </select>
                    <select required name="end" id="end" class="end">
                        <option value="" selected="true" disabled>End</option>
                        <option value= "8" ${8 == end ? 'selected="selected"' : ''} class="optOdd">8:00</option>
                        <option value= "9" ${9 == end ? 'selected="selected"' : ''} >9:00</option>
                        <option value="10" ${10 == end ? 'selected="selected"' : ''} class="optOdd">10:00</option>
                        <option value="11" ${11 == end ? 'selected="selected"' : ''} >11:00</option>
                        <option value="12" ${12 == end ? 'selected="selected"' : ''} class="optOdd">12:00</option>
                        <option value="13" ${13 == end ? 'selected="selected"' : ''} >13:00</option>
                        <option value="14" ${14 == end ? 'selected="selected"' : ''} class="optOdd">14:00</option>
                        <option value="15" ${15 == end ? 'selected="selected"' : ''} >15:00</option>
                        <option value="16" ${16 == end ? 'selected="selected"' : ''} class="optOdd">16:00</option>
                        <option value="17" ${17 == end ? 'selected="selected"' : ''} >17:00</option>
                        <option value="18" ${18 == end ? 'selected="selected"' : ''} class="optOdd">18:00</option>
                        <option value="19" ${19 == end ? 'selected="selected"' : ''} >19:00</option>
                        <option value="20" ${20 == end ? 'selected="selected"' : ''} class="optOdd">20:00</option>
                    </select>
                    <input class="c_reason" id="reason" type="text" name="reason" value="${reason}" placeholder="Enter Your Reason..." required>
                    <%--<button class="btn" type="button" onclick="submitDemand()">Submit</button>--%>
                    <button class="btn" type="submit" value="Demands">Submit</button>
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
                            <td>
                                <c:out value="${demandArr.key}" />
                            </td>
                            <td><c:out value= "${demand.dayStr} | ${demand.start}:00 - ${demand.end}:00 | ${demand.reason}" /></td>
                            <TD class = "select">
                                <select class="selectStatus">
                                    <option value="Pending" ${demand.status.name == 'Pending' ? 'selected="selected"' : ''} >Pending</option>
                                    <option value="Approved" ${demand.status.name == 'Approved' ? 'selected="selected"' : ''}>Approved</option>
                                    <option value="Declined" ${demand.status.name == 'Declined' ? 'selected="selected"' : ''}>Declined</option>
                                </select>
                            <td><button type="button" class="saveChange">Save</button></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>

        </div>
    </section>
</div>
</body>

</html>