$("document").ready(function(){
  $("#comments_iframe").on("click",function(){
    $("div.message_content>iframe")[0].src="iframe_comments.html";
  });

    $("#messages_iframe").on("click",function(){
      $("div.message_content>iframe")[0].src="iframe_messages.html";
    });
});
