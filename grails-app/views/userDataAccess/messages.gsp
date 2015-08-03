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
color: white!important;  
text-align: center!important;  
text-align: center!important;  
background: #4abbff!important;  
padding: 2px!important;
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

	  
		<div style = "margin: auto; margin-top: 25px;  width: 750px;  display: block!important;  ">
		<h2 style = "padding: 0px; margin: 0px; color: #4abbff!important; ">My Messages <span style = "font-size: 16px; color: #3d3d3d;" >Newest to top</span></h2>
<div  id = "okMessage"  style = "background:#fdfdfd!important; display: block; width: 750px; height: 70%; overflow-y: scroll; padding-top: 6px!important;  padding-bottom: 20px!important; margin-top: 7px;  margin-right: auto!important; box-shadow:0 .10em .5em rgba(0,0,0,.25)!important; margin-left: auto!important; border-radius: 5px!important; ">
	
	<g:each var="message" in="${messages}">
	<!-- MESSAGE ROW START -->
			<div id = "${message.messageID}" style = "box-shadow:0 0 10px rgba(0, 0, 0, 0.1); padding: 5px; border-radius: 8px; height: auto; margin: auto; margin-top: 13px; background-color: #FFFFFF; width: 90%; display: block;">
				<div style = "padding: 5px; margin-bottom: 0px;" > 
					<div style = " width: 100%; text-align: left;">
					<span style = "padding: 0px; margin-right: 14px; display: inline-block; color: #3d3d3d;" ><b>Status:</b> <span style = "color:<g:if test="${message.status == 'New'}">green</g:if><g:elseif test="${message.status == 'Read'}">#4abbff</g:elseif>"><b>${message.status}</b></span> </span>
					<span style = "padding: 0px; margin-right: 14px; display: inline-block; color: #3d3d3d;" ><b>Name:</b> ${message.name} </span>
					<span style = "padding: 0px; margin-right: 14px; display: inline-block; color: #3d3d3d;" ><b>Email:</b> ${message.email} </span>
					<span style = "padding: 0px; margin-right: 14px; display: inline-block; color: #3d3d3d;" ><b>Sent:</b> <g:formatDate format="MM-dd-yyyy" date="${message.date}"/> </span>
					</div>
				</div>	
				<span style = "padding: 4px; margin-bottom: -4px; display: block; color: #3d3d3d;"><b>Message:</b></span>
				<div style = "padding: 4px; overflow-y: auto; overflow-x: none; height: auto; max-height: 200px; margin-bottom: 5px;" > 
					
					${message.message}
					 
				</div>	
					<span  value = "Login"  style = "width: 100px; height: 18px; text-decoration: none; display: inline-block; margin-top: 2px; margin-bottom: 5px; margin-left: 5px; " class ="MessageOK"/>Reply</span>
					<span  onClick = "deleteMessage(${message.messageID})" value = "Login"  style = "width: 100px; height: 18px; text-decoration: none; display: inline-block; margin-top: 2px; margin-bottom: 5px;" class ="MessageOK"/>Delete</span>
			</div>
		<!-- MESSAGE ROW END -->
	</g:each>


</div>

</div>



<script>

// A $( document ).ready() block.
$( document ).ready(function() {


  
});

function noPass() {
alert("Please contact your Manager for lost password inquires.");
}

function deleteMessage(messageID){
	if (confirm('Are you sure you want to delete this message?')) {
		$(messageID).fadeOut(600);		
	    $.post("deleteMessage/" + $(messageID).attr('id'));	
	} else {
		// Cancel
	}

}

</script>




</body>
</html>