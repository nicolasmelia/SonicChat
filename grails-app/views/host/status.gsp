<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<body style = "background-color: #f3f3f3;">

<style>
.linkBox {
width:230px; 
height:90px;
background-color: #80C3FF;
text-align: center;
border: solid 1px;
border-color: #F3F3F3;
display:inline-block;
margin: 2px;
cursor:default
box-shadow: 0 0 10px rgba(0,0,0,0.6); 
border-radius: 6px;
}

.linkBoxRefreash {
width:470px; 
height:40px;
background-color: #80C3FF;
text-align: center;
border: solid 1px;
border-color: #F3F3F3;
display:-block;
margin: auto;
cursor:default
box-shadow: 0 0 10px rgba(0,0,0,0.6); 
border-radius: 6px;
}

.linkBoxRefreash:hover {
  opacity: 0.6; 
}


.linkBox:hover {
  opacity: 0.6; 
}

.linkBoxServer {
width:230px; 
height:90px;
background-color: #FF1414;
text-align: center;
border: solid 1px;
border-color: #F3F3F3;
display:inline-block;
margin: 2px;
cursor:default
box-shadow: 0 0 10px rgba(0,0,0,0.6); 
border-radius: 6px;

}


.linkBoxServer:hover {
  opacity: 0.7; 
}




.textStart {
padding-top: 13px;
color: #2E2E2E;
}

.textRe {
margin-top: 10px;
padding-top: 0px;
color: #2E2E2E;
}

.textStop {
padding-top: 13px;
color: #2E2E2E;
}

</style>



<div style = "width: 605px;  background-color: #ffffff;     box-shadow: 0 0 10px rgba(0,0,0,0.2); margin:auto; margin-top: 60px;">
<p style = "width: 100%; text-align: center; color: #2E2E2E; font-size: 23px;  margin: auto; padding-top: 15px; margin-bottom: 10px; margin-top: 0px;" ><b>Admin Server Config</b></p>
<br>

<hr style = "width: 95%!important; margin: auto!important; border: 0!important; height: 0!important; border-top: 1px solid rgba(0, 0, 0, 0.1)!important; border-bottom: 1px solid rgba(255, 255, 255, 0.3)!important;">
<br>
<div style ="margin:auto; width: 480px;">
<div onClick = "toggleServer()" class = "linkBox" style = "" onClick = "toggleServer()">
<p class = "textStart"><b>Toggle Server Socket</b><br>Server Status: <b>${started}</b><p>
</div>
<div class = "linkBox" style = "" onClick = "forceReset()">
<p class = "textStop" style = "color: #FF1414;"  ><b>FORCE Static Reset</b><br>Stops socket server<p>
</div>
</div>

<div style ="margin:auto; width: 480px;">
<div  onClick = "toggleAway()" class = "linkBox" style = "" onClick = "">
<p class = "textStart" ><b>Toggle System Away</b><br> Status: <b>${away}</b><p>
</div>
<div class = "linkBox" style = "" onClick = "">
<p class = "textStart" ><b>Start a Host (LIVE)</b><br>As User: Nick<p>
</div>


<div onClick = "refreshPage()" class = "linkBoxRefreash" style = "" onClick = "">
<p class = "textRe" ><b>Refresh</b><br><p>
</div>

</div>

<br><br>
</div>
</body>

<script>

$( document ).ready(function() {
    console.log( "ready!" );
});





// ************ get data here ************
function forceReset() {
    if (confirm("A reset will stop the socket server. Are you sure?") == true) {
    	window.open("host/forceReset","_self")
    } else {
        // NO
    }
}

// ************ get data here ************
function toggleServer() {
    if (confirm("Are you sure you want to toggle Server?") == true) {
    	window.open("/SonicChatV1/host/toggleSocketServer","_self")
    } else {
        // NO
    }
}

function toggleAway() {
    if (confirm("Are you sure you want to toggle away?") == true) {
    	window.open("/SonicChatV1/host/toggleAway","_self")
    } else {
        // NO
    }	
}


function refreshPage() {
location.reload();	
}


</script>



</html>