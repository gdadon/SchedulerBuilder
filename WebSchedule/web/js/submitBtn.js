/**
 * Created by Guy on 30/05/2017.
 */

var myNodelist = document.getElementsByClassName("close-btn");
var i;
for (i = 0; i < myNodelist.length; i++) {
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    myNodelist[i].appendChild(span);
}
// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}

// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}
function submitDemand() {
    // get day
    var e = document.getElementById("day");
    var day = e.options[e.selectedIndex].value;
    var dayTxt = e.options[e.selectedIndex].text;
    // get start hour
    e = document.getElementById("start");
    var start = e.options[e.selectedIndex].value;
    var startTxt = e.options[e.selectedIndex].text;
    // get end hour
    e = document.getElementById("end");
    var end = e.options[e.selectedIndex].value;
    var endTxt = e.options[e.selectedIndex].text;
    // get reason
    var reason = document.getElementById("reason").value;
    if (day == "Day") {
        alert("Missing Day");
        return;
    }
    if (start == "Start") {
        alert("Missing Start hour");
        return;
    }
    if (end == "End") {
        alert("Missing End hour");
        return;
    }
    if ((end - start) <= 0) {
        alert("Demands time is wrong, end hours should be later than start hour");
        return;
    }
    if (reason == "") {
        var r = confirm("No reason was filled, most likely demand will rejected."+
            "\n" + "presse OK to send demand anyway, Cancel to fill reason");
        if (r == false) {
            return;
        }
        else{
            reason = "None"
        }
    }
//            alert(day + "," + start + "," + end + "," + reason);
    newElement(dayTxt, startTxt, endTxt, reason);
    reset();
}

function reset() {
    document.getElementById("day").selectedIndex = 0;
    document.getElementById("start").selectedIndex = 0;
    document.getElementById("end").selectedIndex = 0;
    document.getElementById("reason").value = "";
}

function newElement(day, start, end, reason) {
    var li = document.createElement("li");
    var t = document.createTextNode(day + " | " + start + " - " +
        end + " | " + reason + " | PENDING");
    li.appendChild(t);
    li.className = "close-btn";
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);
    document.getElementById("my_d_list").appendChild(li);
    span.onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}

function setActive() {
    var url = window.location.href;
    var file = url.split('?')[0];
    var pathanddomain = file.split('/');
    var path = pathanddomain.splice(1, pathanddomain.length-1);
    var pathIndexToGet = 2;
    var current = path[pathIndexToGet];
    var list = document.getElementsByTagName("li");
    for(i = 0; i < list.length; i++){
        var t = list[i].innerText;
        if(t == current) {
            list[i].getElementsByTagName('a')[0].className = "active";
        }
    }
}