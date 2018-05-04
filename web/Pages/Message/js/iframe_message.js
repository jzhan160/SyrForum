$("document").ready(function(){
   unread=$("div.unread_messages");
   var i;
   for(i=0;i<unread.length;i++){
      if(unread[i].innerText==0){
        $(unread[i]).css("visibility",'hidden');
      }
   }
   $("#markRead").on("click",function(){
      for(i=0;i<unread.length;i++){
         $(unread[i]).css("visibility",'hidden');

      }
   });

   $("#clearMessage").on("click",function(){
      var items= $("div.message_wraper");
      var i;
      for(i=0;i<items.length;i++){
        // $(items[i]).css('visibility','hidden');
        $(items[i]).remove();
      };
   });
   function loadDoc() {
     var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         console.log(this.responseText);
       }
     };

     var queryString="chat.php?chatwith="+username+"&self="+self;

     xhttp.open("GET", queryString, true);
     xhttp.send();
   }

   //should get information from server
   $("div.message_wraper").on("click",function(){
     itemIndex = $(".message_wraper").index(this)

     var readItem=$("div.unread_messages")[itemIndex];
     $(readItem).css("visibility",'hidden');
     $(readItem).text('0');
     console.log(readItem);
     window.location.href='iframe_chat.html';
     //uncomment the line below to direct to chat window
     // loadDoc()
   });
});
