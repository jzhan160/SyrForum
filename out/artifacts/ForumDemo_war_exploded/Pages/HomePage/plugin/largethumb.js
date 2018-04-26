 $(document).ready(function(){
 var $mainImg = $("#main");
 var $mainLink = $("#header > a");
 $(".thumbs").mouseover(function(){
    var src = $(this).attr("src");
    var link = $(this).parent().attr("href");
    console.log(link);
    var doubleWidth = $(this).width()*3.5;
    var doubleHeight = $(this).height()*3.5;
    $mainImg.attr("src",src);
    $mainImg.css({"width":doubleWidth,
        							"height:":doubleHeight})
		$mainLink.attr("href",link);
    });
 });