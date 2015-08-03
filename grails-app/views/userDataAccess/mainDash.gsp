<html>
<head>
 <link rel="stylesheet" type="text/css" href="<g:resource dir='css' file='menuStyle.css'/>">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>     
</head>
<body style = "">

<style>
body {
margin: 0px; 
padding: 0px; 
Background-color: #f3f3f3;
min-width: 1000px;
}

.MessageOK {
display: block!important; 
color: white!important;  
width: 100%; 
text-align: center!important;  
margin: auto;  
background: #4abbff!important;  
padding: 6px!important;
border-radius: 4px!important;  
border-style:none!important;  
}
.MessageOK:hover {
background: #71c7fb!important;  
}

</style>

  <div id='cssmenu' style = "min-width: 1000px;">
         <img style = "position: absolute; left: 8px; top: 6px; width: 120px;" src="${resource(dir:'images',file:'sonicLogo.png')}" >
         <ul>
            <li class='last'><a href='logOut'><span>LOG OUT</span></a></li>
            <li class='last'><a href='dashboard'><span>Main Dashboard</span></a></li>
            <li class='last'><a href='http://SonicChats.com'><span>Our Blog</span></a></li>           
         </ul>
      </div>

<div  id = "okMessage"  style = "background:#fdfdfd!important; display: block; width: 660px; padding-top: 0px!important;  padding-bottom: 20px!important; margin-top: 60px;  margin-right: auto!important; box-shadow:0 .10em .5em rgba(0,0,0,.25)!important; margin-left: auto!important; border-radius: 5px!important; ">
<h2 style = "color: #4abbff!important; margin-left: 22px!important; margin-bottom:9px!important;  padding-top:10px!important;">Welcome ${session.displayName}</h2>
<hr style = " width: 95%!important; margin: auto!important; border: 0!important; height: 0!important; border-top: 1px solid rgba(0, 0, 0, 0.1)!important; border-bottom: 1px solid rgba(255, 255, 255, 0.3)!important;">
<p style = "color: #555555; margin-top: -2px; font-size: 14px; margin-bottom: 0px;  padding: 10px;" ><span style = "color: #4abbff;" ><b></b></span><p>

<div  id = "okMessage"  style = "background:#F8F8F8!important; display: block; width: 460px; padding: 10px;   margin-top: -15px;  margin-right: auto!important; box-shadow:0 .10em .5em rgba(0,0,0,.15)!important; margin-left: auto!important; border-radius: 2px!important;">
	<p style = "color: #42AAFF; margin-top: -2px; font-size: 21px; text-align: center; display: block;  margin-bottom: 0px;  padding: 0px;" ><b>My SonicChat</b><p>
	<a  href = "#" value = "Login"  action = "loginEmployee" style = "width: 200px; text-decoration: none;  margin-top: 3px;" class ="MessageOK"/><b>Edit Tiles</b></a>
	<a  href = "#" value = "Login"  action = "loginEmployee" style = "width: 200px; text-decoration: none; margin-bottom: 15px; margin-top: 10px;" class ="MessageOK"/><b>Edit AnswerBase</b></a>

</div>


<div  id = "okMessage"  style = "background:#F8F8F8!important; display: block; width: 460px; padding: 10px; margin-top:  20px; margin-right: auto!important; box-shadow:0 .10em .5em rgba(0,0,0,.12)!important; margin-left: auto!important; border-radius: 2px!important; ">
	
	<p style = "color: #42AAFF; text-align: center; display: block; margin-top: -2px; font-size: 21px; margin-bottom: 0px;  padding: 0px;" ><b>My Tiles</b><p>
	
	 <g:each var="tile" in="${tiles}">
	    <a  href = "${tile.tileViewController}" target="_self"  value = "${tile.tileID}" style = "width: 200px; text-decoration: none; display: block; margin-top: 10px;" class ="MessageOK"/>${tile.tileDisplayName}</a> 
	 </g:each>
	
	<p style = "color: #3B3B3B; background-color: #F2F5FF; text-align: left; display: block; margin-top: 0px; font-size: 14px; margin-bottom: -15px; margin-left: 5px; margin-right: 5px;  padding: 10px; border-radius: 2px!important; " ><b>TIP:</b> Start chatting with customers or check your messages. These are your selected tiles. Check your stuff!<p>

</div>





</div>




</div>




<script>

// A $( document ).ready() block.
$( document ).ready(function() {


  
});

function noPass() {
alert("Please contact your Manager for lost password inquires.");
}

</script>




</body>
</html>