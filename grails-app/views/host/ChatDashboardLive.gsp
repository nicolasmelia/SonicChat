<!DOCTYPE html>
<html>
   <head>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
      <script src="<g:resource dir="js" file="jquery.gracefulWebSocket.js"/>"></script>
      <script src="<g:resource dir="js" file="ChatServiceHostLive.js" />"></script>
      <link rel="stylesheet" type="text/css" href="<g:resource dir='css' file='host.css'/>">
      <link rel="stylesheet" type="text/css" href="<g:resource dir='css' file='menuStyle.css'/>">
   </head>
   <body>
   
   <input id = "userDisplayName" type="hidden" value="${session.displayName}">
   
      <div id='cssmenu'>
         <img style = "position: absolute; left: 8px; top: 6px; width: 120px;" src = "/static/images/sonicLogo.png">
         <ul>
            <li class='active'><a href='#'><span>Dashboard</span></a></li>
            <li class='has-sub'>
               <a href='#'><span>Answer-Base</span></a>
               <ul>
                  <li><a href='#'><span>Product 1</span></a></li>
                  <li><a href='#'><span>Product 2</span></a></li>
                  <li class='last'><a href='#'><span>Product 3</span></a></li>
               </ul>
            </li>
            <li class='has-sub'>
               <a href='#'><span>Settings</span></a>
               <ul>
                  <li><a href='#'><span>Company</span></a></li>
                  <li class='last'><a href='#'><span>Contact</span></a></li>
               </ul>
            </li>
            <li class='last'><a href='/host/loginEmployee'><span>Log Out</span></a></li>
         </ul>
      </div>
      <br>
      <div class = "main" style = "margin: 0 auto;  width: 1105px; height: 500px;">
         <div style = "display: inline-block; float: left; width: 220px; ">
            <!-- Status -->
            <div style = "margin-bottom: 5px; height: 40px; background-color: #f4f4f4; border: solid 1px; border-color: #bdbdbd;  box-shadow:0 .25em 1em rgba(0,0,0,.35);">
           <div style = " margin:auto; margin-top: 13px;  width: 150px; vertical-align: top;" >  <img src = "/static/images/greenStatus.png" style = "width: 16px; vertical-align: top; "> <p style = "display: inline-block; vertical-align: top;  margin: 0px 0px 10px 5px; font-color: #3C3C3C; font-size: 15px;"> <b>Accepting Chats</b></p></div>
             </div>
            <!-- Status -->
            <div class = "userConnectionsContainter">
            <div class = "userConnections" id = "userConnections" >
	            </div>
	            <!-- Users Div -->
	            <!-- Status -->
	            <div style = "margin-top: 0px; height: 50px;   background-color: #f4f4f4;  text-align: center;  border-top: solid 1px; border-color: #bdbdbd; ">
	               <img src = "/static/images/icons/transfer.png" style = "margin: 6px 13px 0px; width: 35px; display:inline-block;">
	               <img src = "/static/images/icons/end.png" style = "margin: 6px 13px 0px; width: 35px; display:inline-block;">
	               <img src = "/static/images/icons/settings.png" style = "margin: 6px 13px 0px; width: 35px;  display:inline-block;">
	            </div>
	            <!-- Status -->
            </div>	
         </div>
         <!-- user info div -->		
         <div class = "well">
            <!-- text display and type -->	
            <div style = "display:inline-block">
               <div style = " display: table; height: 35px; width: 500px; text-align:center;  background-color: #545454;  border-top-right-radius: .2em;  border-top: solid 1px;   border-right: solid 1px;  border-left: solid 1px;  border-color: #bdbdbd;  border-top-left-radius: .2em;">
                  <p id = "SiteName" style = "margin: 0px; color: #FFFFFF; margin-top: 9px;">Waiting for customers...</p>
               </div>
               <div  id = "chatBox" style = "height: 380px; width: 496px; border-radius: 1px; padding: 2px; background-color: #FFFFFF;  border: solid 1px; border-color: #bdbdbd; overflow-y: auto; overflow-x: none;"></div>
               <textarea id = "chatBoxInput" rows="4" cols="55" style = " display:table;  width: 493px; height: 70px; margin:auto; display: block; resize: none;  border:3px solid #ADD8E6;"></textarea>
            </div>
            <!-- text display and type -->	
            <div style = "display: inline-block; float: right; width: 340px; vertical-align: top;">
               <img  src="${resource(dir:'images',file:'blankavatar.png')}"  style = "width: 80px; display:inline-block;">
               <table border="1" style="width: 200px;  border:none; display: inline-block; vertical-align:top;">
                  <tr >
                     <td id = "userNumberRow" style = "border:none;"><b>User:</b></td>
                  </tr>
                  <tr>
                     <td style = " border:none;">Cleveland, OH</td>
                  </tr>
                  <tr>
                     <td style = " border:none;">Returning Customer</td>
                  </tr>
               </table>
               <br>
               <!-- viewing div -->		
               <p style = "margin-bottom: -2px;">Currently viewing</p>
               <hr>
               <div id = "currentlyViewing" style = "height: 70px; width: 100%; border-radius: 1px; background-color: #FFFFFF;  border: solid 1px; border-color: #bdbdbd; overflow-y: scroll; overflow-x: none;">
               </div>
               <!-- viewing div -->		
               <!-- Search div -->		
               <div id = "UrlLink">
                  <p style = "margin-bottom: -2px; ">Question search: <b><a href = "NicoalsMelia.com"></a></b></p>
               </div>
               <hr>
               <input id = "searchBox" style = "width: 92%; margin-bottom: 4px; display:inline-block" type="text" name="fname" placeholder="Search...">
               <img   src="${resource(dir:'images',file:'searchIcon.png')}"  style = "margin-top: -1px; margin-bottom: -3px; width: 18px; display: inline-block; float: right;">
               <!-- Search div -->						
               <div id = "quickAnswer" style = "height: 215px; width: 100%; border-radius: 1px; background-color: #FFFFFF;  border: solid 1px; border-color: #bdbdbd; overflow-y: scroll; overflow-x: none;">
                  <div style = "margin: auto; padding-top: 4px; width: 93%; text-align: center;">Search Empty...<hr style = "margin-top: 2px;"> </div>
               </div> 
               <!-- search div -->
            </div>
         </div>
         <!-- user info div -->	
      </div>
      <script>
         $(document).ready(function() {
         // You still run JS from page as normal
         
         
         
         });
      </script>
   </body>
</html>