// Scoped Variables 
var webSocket;
var serverAddress = "ws://cool-517061425.us-west-2.elb.amazonaws.com"; 
var port = "50005";
var SelectedUserID = 0;
var connectedUsers = [];
var displayName;


var dotTimer = null; // Timer used to display now typing(from host).
var nowTyping = false; // toggled every 3seconds to allow a message to be sent to server to show client is typing.

$(document).ready(function() {
	// Connect to port that has been created by the host		
	connectToServer();
	sendAndRecieve();
	sendHostInfo();
});

function connectToServer() {
	// Connects to the server
	webSocket = $.gracefulWebSocket(serverAddress + ":" + port);

	// When the host (Server) disconnects show the user a message
	var checkConnection = setInterval(function () {
		if ((webSocket.readyState == 2 || webSocket.readyState == 3)) {
			$('#chatBox').append('<p style = "margin: 4px 5px 4px 2px; color: #3c3c3c;">' + "Host has disconnected." + '</p>');
			// Scroll to bottom of page when message recieved
			$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
			clearInterval(checkConnection); // Stop timer
		}
	}, 1500);	
}
		
function sendAndRecieve(){
	$("#chatBoxInput").keyup(function(event){
		if(event.keyCode == 13){
			sendMessage();
		}
	});
	
	// This looks for messages from socket
	webSocket.onmessage = function (event) {
		var messageFromServer = event.data;
		if (!getNewConnectionInformation(messageFromServer)){		
		var message = messageFromServer.split(":");		
		  if(message[1] == "!TYPING!") {  
			  if (SelectedUserID == message[0]) {
				displayNowTyping(true);
			  }		
		  } else {
				for (var i = 0; i < connectedUsers.length; ++i) {
					if (connectedUsers[i].userID == message[0]) {
						connectedUsers[i].messages.push('<p style = "margin: 4px 5px 4px 2px; font-size: 16px; color: #3c3c3c;"><span class = "searchLink" onClick="search(\'' + message[1] + '\')"><u><b>Customer</b></u></span>: ' + message[1] + '</p>');
						break;
					}
				}
				// Only show message if message is from selected user
				if (SelectedUserID == message[0]) {
						displayNowTyping(false);
						$('#chatBox').append('<p style = "margin: 4px 5px 4px 2px; font-size: 16px; color: #3c3c3c;"><span class = "searchLink" onClick="search(\'' + message[1] + '\')"><u><b>Customer</b></u></span>: ' + message[1] + '</p>');
						// Scroll to bottom of page when message recieved
						 $('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
				}	
				
		  }
		
		}
	};	
	
	
	// Onkey press send a message to socket to let host know client it typing
	$("#chatBoxInput").keypress(function(){
		if (nowTyping == false) {
			nowTyping = true;
			sendNowTyping();
			var typingTimer = setInterval(function () {
				nowTyping = false;
				clearInterval(typingTimer); 
			}, 3000);
		}
	});
	
}

function search(message) {
	var siteID;
	$("#searchBox").val(message);
	for (var i = 0; i < connectedUsers.length; ++i) {
		if (connectedUsers[i].userID == SelectedUserID) {
			siteID = connectedUsers[i].siteID;
			break;
		}
	}
	
	$.get("/DataAccess/quickSearch/" + siteID + ":" +  message, function(data) {
		var results = data.split(":::");
		var res = "";
		
		for (var i = 0; i < results.length - 1; ++i) {
			res = res + '<div style = "width: 93%; margin: auto; margin-top: 7px;  border: solid 1px; border-color: #bdbdbd; border-radius: .3em; background-color: #f7f7f7; padding: 2px;">' +
						'<p style = "margin: 0px 0px 0px 5px"><span style = "color: #009ac7;">Result ' + (i + 1) + '<br></span>' + results[i] + '</p>' +
						'</div>';
		}
		var htmlInsert = '<div style = "margin: auto; padding-top: 4px; width: 93%; text-align: center;">Possible Answers: ' + (results.length - 1) + '<hr style = "margin-top: 2px;"></div>' + res;
		$("#quickAnswer").html(htmlInsert);
		});
} 

	function sendMessage() {
		if (webSocket.readyState == 1 && $("#chatBoxInput").val().replace(" ", "").trim().length > 1) {
			webSocket.send(SelectedUserID + ": " + $("#chatBoxInput").val());

			if ($("#typingP").length) { // If now typing is being displayed append sent message above that
				$('#typingP').before('<p style = "margin: 4px 5px 4px 2px; font-size: 16px; color: #009ac7;"><b>You:</b><span style = "color: #202020;"> ' + $('#chatBoxInput').val() + '</span></p>');
			} else {
				$('#chatBox').append('<p style = "margin: 4px 5px 4px 2px; font-size: 16px; color: #009ac7;"><b>You:</b><span style = "color: #202020;"> ' + $('#chatBoxInput').val() + '</span></p>');
			}
			
			// Scroll to bottom of page when message sent
			$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
			
			// Save sent message to clients message array to show on select
			for (var i = 0; i < connectedUsers.length; ++i) {		
				if (connectedUsers[i].userID == SelectedUserID) {
					connectedUsers[i].messages.push('<p style = "margin: 4px 5px 4px 2px; font-size: 16px; color: #009ac7;"><b>You:</b><span style = "color: #202020;"> ' + $('#chatBoxInput').val() + '</span></p>');
					 break;
				}
			}
			$("#chatBoxInput").val(""); // clear the chat box;
		} else {
			alert("You must enter text before you can send a message.");
			$("#chatBoxInput").val(""); // clear the chat box;
		}
		
	}
	
	function sendNowTyping() {
		if (webSocket.readyState == 1) {
			webSocket.send(SelectedUserID + ":" + "!TYPING!");
		}
	}
	
function sendHostInfo() {
		var sendInfo = setInterval(function () {
		if (webSocket.readyState == 1) {
			webSocket.send("CONNECTION INFORMATION:HOST:" + $('#userDisplayName').val());
			clearInterval(sendInfo); // Stop timer
		}
	}, 500);	
}	

//****** Following methods change and add display elements to the chat box******
function displayNowTyping(show){
	if (show && dotTimer == null) {
		$('#chatBox').append('<p id = "typingP" style = "font-family: Arial, Helvetica, sans-serif;important; font-weight: 600!important;  margin: 3px 3px 6px 5px!important; color: #949494!important; font-size: 14px!important; line-height: 100%!important;"></p>');
	
		// Change text dots(...) per second to show now typing...
		var dots = ".";
		var displayCount = 0; // counts how many times this blinks a dot
		dotTimer = setInterval(function () {
			
			$("#typingP").text("Customer is typing" + dots);		
			$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));

			switch (dots) {
			case ".": dots = "..";
				break;
			case "..": dots = "...";
				break;
			case "...": dots = "";
				break;
			default: dots = ".";
			}
			
			displayCount++;	
			
			if (displayCount > 15) {
				clearInterval(dotTimer); // end the timer
				dotTimer = null;
				$("#typingP").remove();
			}
		}, 400);
		
	} else if ($("#typingP").length && !show) {//Test whether the typingP ID exist.
		clearInterval(dotTimer); // end the timer
		dotTimer = null;
		$("#typingP").remove();
		$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
	}
}

function getNewConnectionInformation(message) {
	// Retrieves the user id from the message received
	var affected = false;
	
	var connectionMessageArray = message.split(":");
	var usersCurrentURL = message.split("::"); // Splits to the clients URL at array [1]
	
	
	if (connectionMessageArray[0] == "CONNECTION INFORMATION") {
		addUserConnection(connectionMessageArray[1], usersCurrentURL[1], connectionMessageArray[2], connectionMessageArray[3]);
		affected = true;
	} 
		
	if (connectionMessageArray[0] == "REESTABLISH" && affected == false) {		
		// Display the clients new URL page
		for (var i = 0; i < connectedUsers.length; ++i) {		
			if (connectedUsers[i].userID == connectionMessageArray[1]) {
				connectedUsers[i].URLS.unshift(usersCurrentURL[1]); //unshift adds url string to top of array
				displayClientsURLHistory(connectedUsers[i].userID);
				
				if (connectedUsers[i].userID == SelectedUserID){
				selectUser(SelectedUserID, connectedUsers[i].fullURLName)
				}
				
				break;
			}
		}
		
		affected =  true;	
	}
	
	return affected;
	
}	

function selectUser(userID, siteURLName) {
	SelectedUserID = userID;
	displayClientsURLHistory(userID);
	
	// Change color of user item selected
	for (var i = 0; i < connectedUsers.length; ++i) {
		// Reset color for all other user boxes
		$("#"+connectedUsers[i].userID).css("background-color","#ebecf9");
	}
	$("#"+userID).css("background-color","#cfe8f0");

    	//Change the messages shown for the user selected
		$('#chatBox').html("");
		for (var i = 0; i < connectedUsers.length; ++i) {		
			if (connectedUsers[i].userID == SelectedUserID) {
				 $('#SiteName').html('Customer From: <b>' + siteURLName + '</b>');
				 $('#UrlLink').html('<p style = "margin-bottom: -2px; ">Question search: <b><a href = "'+ siteURLName + '">'+ siteURLName+'</a></b></p>');

				for (var j = 0; j < connectedUsers[i].messages.length; ++j) {		
					$('#chatBox').append(connectedUsers[i].messages[j]);
				}
				 $('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
				 displayNowTyping(false);
				 break;
			}
		}
		
		var htmlInsert = '<div style = "margin: auto; padding-top: 4px; width: 93%; text-align: center;">Possible Answers: 0 <hr style = "margin-top: 2px;"></div>';
		$("#quickAnswer").html(htmlInsert);
		
		$("#userNumberRow").html('<b>User: </b>' + SelectedUserID);
		
}

function addUserConnection(userID, currentURL, fullURL, siteId) {
	var NewUser = new Object();
	NewUser.userID = userID; // Adds connection user id
	NewUser.messages = [];
	NewUser.URLS = [];
	NewUser.fullURLName = fullURL;
	NewUser.URLS.push(currentURL);
	NewUser.siteID = siteId;
	connectedUsers.push(NewUser);

	if (connectedUsers.length > 1) { // 1 is the HOST connection. Do not display button for host
	// Append the User button
	$('#userConnections').append('<div class = "user" style = ""  id = "' + userID + '">' + 
			  '<img  src="/static/images/blankavatar.png"   style = "margin-top: 8px;  margin-left: 5px; border-radius: 5px; width: 33px; display:inline-block; float:left;">' +
				'<table border="1" style="width: 140px; margin-left: 5px;  margin-top: 8px;  border:none; display: inline-block; vertical-align:top;">' +
				  '<tr >' +
					'<td style = "padding: 0px; font-color: #ffffff; font-size: 14px; height: 5px; border:none;">User:' + userID + '</td>' +
				  '</tr>' +
				  	  '<tr>' +
					'<td style = "padding: 0px; font-color: #ffffff; height: 5px; max-width: 30px; font-size: 14px; border:none;">' + fullURL + '</td>' +
				  '</tr>' +
				'</table>' +
			  '</div>');
	
	// Append the onClick to the above element
    $('#' + userID).click(function() {
    	selectUser(userID, fullURL);
    });
	
	displayClientsURLHistory(userID);
	
	}
	
	// If user connected is first user auto select him
	if  (connectedUsers.length == 2) {
		selectUser(userID, fullURL);
	}
	
}


function displayClientsURLHistory(userID) {
	$("#currentlyViewing").html(""); // clear the chat box;

	for (var i = 0; i < connectedUsers.length; ++i) {		
		if (connectedUsers[i].userID == SelectedUserID) {
			//connectedUsers[i].URLS.reverse();
			for (var j = 0; j < connectedUsers[i].URLS.length; ++j) {	
				if (j == 0){
					$('#currentlyViewing').append("<p style = 'margin: 0px; font-size: 15px;'> <span style = 'color: #009ac7;'>" + "Looking at " + "</span>" + connectedUsers[i].URLS[j] + "</p>");
					if (connectedUsers[i].URLS.length > 1) {
					$('#currentlyViewing').append("<p style = 'margin: 0px; font-size: 15px;'> <span style = 'color: #009ac7;'>" + "Looked at " + "</span></p>");
					}
				} else {
					$('#currentlyViewing').append("<p style = 'margin: 0px; font-size: 15px;'>" + connectedUsers[i].URLS[j] + "</p>");
				}
			}
			// $('#currentlyViewing').scrollTop($('#chatBox').prop("scrollHeight"));
			 break;
		}
	}
}


					