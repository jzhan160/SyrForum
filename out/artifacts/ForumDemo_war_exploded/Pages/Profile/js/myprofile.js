//Apr 2018
//Syracuse Forum
//js file for view personal profile and change profile image
//
//---------------------------------------------------------------

$("document").ready(function(){
   //click the button and pop out a new window
   $("#editProfileBtn").on("click",appear);
   //cancel to close the window
   $("div#interface input[value='Cancel']").on("click",hide);
   //make radio and image consistent and change border
   //to notify users
   $("img.profileselection").on("click",changeBorder)
   $("input[type=radio]").on("click",changeImage)
   //ajax to upload profileImage value to server
   $("div#interface input[value='Select']").on("click",loadDoc);
   //load personal profile from local cookie
   if(document.cookie!=""){
     var storedCookie=document.cookie;
     var profileLocation=storedCookie.length-1;
     $("#profileImage")[0].src="Pages/Profile/img/profile"+storedCookie[profileLocation]+".jpg";
   }
});

//make pop up window visible
function appear() {
  var popupWindow=$("#interface");
   popupWindow[0].style.visibility="visible";
}

//hide the pop up window
function hide() {
   var popupWindow=$("#interface");
    popupWindow[0].style.visibility="hidden";
}

//make radio and images consistent and change border to
//indicate users.
function changeBorder(){
  $("img.profileselection").css("border","1px solid white");
  this.style.border="1px solid black";
  var itemIndex = $("img.profileselection").index(this)
  // check the index selected
  console.log(itemIndex);
  $("input[type='radio']")[itemIndex].checked=true
}

function changeImage(){
  $("img.profileselection").css("border","1px solid white");
  var itemIndex =$("input:radio[name=profile]:checked").val()-1;
  var correspondingImg=$("img.profileselection");
  // console.log(itemIndex);
  // console.log(correspondingImg[itemIndex]);
  var item= correspondingImg[itemIndex]
  item.style.border="1px solid black";
}
// I tried in php, set the cookie for the profile
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);
    }
  };
  var itemIndex =$("input:radio[name=profile]:checked").val()
  var queryString="test.php?profileImage="+itemIndex

  xhttp.open("GET", queryString, true);
  xhttp.send();
  hide();
  $("#profileImage")[0].src="Pages/Profile/img/profile"+itemIndex+".jpg";
}
