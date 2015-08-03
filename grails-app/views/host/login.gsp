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
margin: auto!important;  
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
         <img style = "position: absolute; left: 8px; top: 6px; width: 120px;" src = "/static/images/sonicLogo.png">
         <ul>
            <li class='last'><a href='http://SonicChats.com'><span>Sign Up</span></a></li>
            <li class='last'><a href='http://SonicChats.com'><span>Learn about SonicChat</span></a></li>
            <li class='last'><a href='http://SonicChats.com'><span>Our Blog</span></a></li>
            
         </ul>
      </div>

<div  id = "okMessage"  style = "background:#fdfdfd!important; display: block; width: 360px; padding-top: 0px!important; height: 265px; margin-top: 70px;  margin-right: auto!important; box-shadow:0 .10em .5em rgba(0,0,0,.25)!important; margin-left: auto!important; border-radius: 5px!important; ">
<h2 style = "color: #4abbff!important; margin-left: 12px!important; margin-bottom:9px!important;  padding-top:10px!important;">Login to SonicChat</h2>
<hr style = " width: 95%!important; margin: auto!important; border: 0!important; height: 0!important; border-top: 1px solid rgba(0, 0, 0, 0.1)!important; border-bottom: 1px solid rgba(255, 255, 255, 0.3)!important;">
<p style = "color: ${color}; margin-top: -2px; font-size: 14px; margin-bottom: 0px;  padding: 10px;" ><span style = "color: #4abbff;" ><b></b></span>${message}<p>


 <g:form controller="Host" action="loginEmployee">

<div style = "width: 80%; margin: auto; margin-bottom: 10px; margin-top: -10px;">
<b><span style = 'color: #606060; margin-bottom: 3px;'>UserName</span></b> <br>
<g:textField name = "username" type = "text" style = "width: 100%; margin: auto; margin-top: 3px;" />
</div>


<div style = "width: 80%; margin: auto;  margin-bottom: -8px;">
<b><span style = 'color: #606060; margin-bottom: 3px;'>Password</span></b><br>
<g:textField  name = "password" type="password" style = "width: 100%; margin: auto; margin-top: 3px; " />
</div>

<br>

<div style = "width:80%; margin: auto;">
<g:actionSubmit  value = "Login"  action = "loginEmployee" style = "margin-bottom: 15px; margin-top: 3px;" class ="MessageOK"/>
<a style = "color: #51C7EE; padding-top: 5px; display: block;" href = "#"onclick = "noPass()" >Forgot your password?</a>
</div>
</g:form>



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