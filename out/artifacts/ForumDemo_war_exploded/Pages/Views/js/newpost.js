var itemtemplate=`
<div class="itemlist">
  <div class="iteminput">
    <Button type="button"  class="dropitem" >-</Button>
    <label>Item 1</label>
  </div>
  <div class="iteminput">
    <label for="itemtype">Item Type:</label>
    <select name="itemtype">
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
    <input type="text" name="itemname1">
  </div>
    <div class="iteminput">
    <label for="itemprice1">Item Price:</label>
    <input type="text" name="itemprice1">
  </div>
  <div class="iteminput">
    <label for="itemdescription1">Item Description:</label>
    <textarea rows="4" cols="50" name="itemdes"></textarea>
  </div>
  <div class="iteminput">
    <label for="itempath1">Upload a image:</label>
    <input type="file" name="itempath1" value="upload">
  </div>
</div>
`;

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
function dropItem(){
  var itemIndex = $(".dropitem").index(this)
  //alert(itemIndex)
  var elementToRemove = $(".itemlist")[itemIndex]
  elementToRemove.remove();
  updateName();
}
$("document").ready(function(){
  $("#plusitem").on("click",appendItem);
  $(".dropitem").on("click",dropItem);
});
