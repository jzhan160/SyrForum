//Apr 2018
//Syracuse Forum
//js file for viewing chat window
//---------------------------------------------------------------

//execute after dom finishing loading
$("document").ready(function(){

       //automatic scoll down to the latest message
  $("div.chat_wrapper").scrollTop(480);

   $("#backBtn").on("click",function(){
     window.location.href='iframe_messages.html';
   });
   $("#sendMsgBtn").on("click",function(){
     var msg_content=$("textarea#inputMsg")[0];
     console.log(msg_content.value);
     var msg_htmltag=`
 <div>
   <img class="self" src="img/profile2.jpg">
   <p class="self">I want to Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
   <p class="clearfix"></p>
 </div>
     `;
     //to view the content
     // console.log(msg_htmltag);
     var new_message = $(msg_htmltag);
     //change the msg in the div
     new_message[0].children[1].innerText=msg_content.value;
     //clear input section
     msg_content.value="";
     //append into DOM
     $("div.chat_wrapper").append(new_message);
     //automatic scoll down to the new message
      $("div.chat_wrapper").scrollTop(480);

   })
});
