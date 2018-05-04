//Apr 2018
//Syracuse Forum
//js file for adding new POST
//---------------------------------------------------------------

//block of html codes to add new item
var itemtemplate=`
<div class="itemlist">
  <div class="iteminput">
    <Button type="button"  class="dropitem" >-</Button>
    <label>Item 1</label>
  </div>
  <div class="iteminput">
    <label for="itemtype">Item Type:</label>
    <select name="itemtype1" required>
      <option value="book">Used Books</option>
      <option value="car">Used Cars</option>
      <option value="furniture">Used Furniture</option>
      <option value="life">Syracuse Life</option>
      <option value="people">People</option>
      <option value="join">Join Us</option>
    </select>
  </div>
  <div class="iteminput">
    <label for="itemname1">Item Name:</label>
    <input type="text" name="itemname1" required>
  </div>
  <div class="iteminput">
    <label for="itemprice1" >Item Price:</label>
    <input type="number" name="itemprice1" required step="0.01" title="must input number">
  </div>
  <div class="iteminput">
    <label for="itemdescription1">Item Description:</label>
    <textarea rows="4" cols="50" name="itemdes1"></textarea>
  </div>
  <div class="iteminput">
    <label for="itempath1">Upload images:</label>
    <input type="file" name="itempath1" value="upload" multiple title="Click Ctrl or Shift to select multiple files">
  </div>
</div>
`;


//update name index for server identification
function updateName(){
  var itemlength = $("div.itemlist").length;
  if(itemlength>0){
    for(i=0;i<itemlength;i++){
      var elementToModify = $("div.itemlist")[i]
      elementToModify.children[0].children[1].innerHTML = "Item "+(i+1);
      elementToModify.children[1].children[1].name = "itemtype"+(i+1);
      elementToModify.children[2].children[1].name = "itemname"+(i+1);
      elementToModify.children[3].children[1].name = "itemprice"+(i+1); // add by Biao A 4/22/2018
      elementToModify.children[4].children[1].name = "itemdes"+(i+1);
      elementToModify.children[5].children[1].name = "itempath"+(i+1);
    }
  }
}

//append new item and bind new dropItem event
function appendItem(){
  var item0=$(itemtemplate);
  $("section.userinput form").append(item0);
  item0.insertBefore("#plus");
  $(".dropitem").on("click",dropItem);
  updateName();
}

//get the index of the nth element of button/item
// function alertIndex(){
//   alert($(".dropitem").index(this));
// }

//drop this item
function dropItem(){
  var itemIndex = $(".dropitem").index(this)
  //alert(itemIndex)
  var elementToRemove = $(".itemlist")[itemIndex]
  //make sure at least one item in the post.
  if($("div.itemlist").length>1){
    elementToRemove.remove();
    updateName();
  }
}



$("document").ready(function(){
  $("#plusitem").on("click",appendItem);
  $(".dropitem").on("click",dropItem);
  $("#new_post").submit(function(event){
    console.log("cancel");
    event.preventDefault();
    var itemlength = $("div.itemlist").length;
    console.log(itemlength);
    validation=true;
    if(itemlength>0){
      for(i=0;i<itemlength;i++){
        var elementToModify = $("div.itemlist")[i];
        var value=elementToModify.children[3].children[1].value;
        if(isNaN(value)){
          alert("Price must input numbers");
          validation=false;
          elementToModify.children[3].children[1].style.backgroundColor="#bdc3c7";
        }else{
          elementToModify.children[3].children[1].style.backgroundColor="white";
        }
      }
    }
    if(validation) this.submit();
  })
});

//validate numeric number of Price
//change to input type=number step=0.01
// function checkNum(){
//   var itemlength = $("div.itemlist").length;
//   console.log(itemlength);
//   validation=true;
//   if(itemlength>0){
//     for(i=0;i<itemlength;i++){
//       var elementToModify = $("div.itemlist")[i];
//       var value=elementToModify.children[3].children[1].value;
//       if(isNaN(value)){
//         alert("Price must input numbers");
//         validation=false;
//         elementToModify.children[3].children[1].style.backgroundColor="#bdc3c7";
//       }else{
//         elementToModify.children[3].children[1].style.backgroundColor="white";
//       }
//     }
//   }
// }
