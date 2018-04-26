//Apr 2018
//Syracuse Forum
//js file for editing password
//---------------------------------------------------------------

//validate the empty inputs
//and indicate users
function validate_password(){
    firsttime= $("input[name ='password']").val()
    secondtime=$("input[name ='retypepwd']").val()
  //  console.log(firsttime);
  //  console.log(secondtime);
    if(firsttime==""&&secondtime==""){
      $("#first_warning").text("Please type in your new password.");
      $("input[name ='password']").addClass("error_warning")
      $("#second_warning").text("Please confirm your new password.");
      $("input[name ='retypepwd']").addClass("error_warning")
    }else if(firsttime==""){
      $("#first_warning").text("Please type in your new password.");
      $("input[name ='password']").addClass("error_warning")
    }else if(secondtime==""){
      $("#second_warning").text("Please confirm your new password.");
      $("input[name ='retypepwd']").addClass("error_warning")
    }else if(firsttime==secondtime&&firsttime!=""&&secondtime!=""){
      $("#pw_form").submit();
    }else{
      $("#first_warning").text("Your inputs are not consistent.");
      $("input[name ='password']").addClass("error_warning")
      $("input[name ='retypepwd']").addClass("error_warning")
    }
}

//make sure two inputs are consistent and
//provide users enough info to resubmit
window.onload=function(){
  // console.log($("form").html)
  $("#submitbtn").on("click",function(event){
    console.log("cancel")
    event.preventDefault();
  });
  $("#submitbtn").on("click",validate_password);
  $("input[name=password]").on("input",function(){
    $("#first_warning").text("")
  });
  $("input[name=password]").on("focusout",function(){
    firsttime= $("input[name ='password']").val()
    secondtime=$("input[name ='retypepwd']").val()
    if($("input[name=password]").val()==""){
      $("input[name ='password']").addClass("error_warning")
      $("#first_warning").text("Please type in your new password.");
    }else{
      $("#first_warning").text("");
      $("input[name ='password']").removeClass("error_warning")
      if(firsttime!=secondtime){
        $("input[name ='retypepwd']").addClass("error_warning")
        $("#second_warning").text("Your inputs are not consistent.");
      }else{
        $("#second_warning").text("");
        $("input[name ='retypepwd']").removeClass("error_warning")
      }
    }
  });
  $("input[name=retypepwd]").on("focusout",function(){
    firsttime= $("input[name ='password']").val()
    secondtime=$("input[name ='retypepwd']").val()
    if(firsttime!=secondtime){
      $("input[name ='retypepwd']").addClass("error_warning")
      $("#second_warning").text("Your inputs are not consistent.");
    }else if(firsttime=""&&secondtime==""){
      $("#first_warning").text("Please type in your new password.");
      $("#second_warning").text("Please confirm your new password.");
    }else{
      $("#second_warning").text("");
      $("#first_warning").text("");
      $("input[name ='retypepwd']").removeClass("error_warning")
      $("input[name ='password']").removeClass("error_warning")
    }
  });
}
