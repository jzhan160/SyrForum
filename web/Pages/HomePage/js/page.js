/**
 * divObj:显示翻页的div，默认body。如：#pageDiv
 * inputId:跳转页面的id，默认goPage。如：goPage
 * js:点击过之后执行js的方法名。默认query。如：query
 * records:总页数，默认1。
 * total：总记录数，默认0。
 * page：当前页，默认1。
 */
function displayPage(divObj,inputId,js,records,page,total){
	    //设置默认值  
    divObj = divObj||"body";
    inputId = inputId||"goPage";
    js=js||"query";
    records=records||1;
    total=total||0;
    page=page||1;
    var str = "当前第&ensp;<span name='page'>"+page+"</span>&ensp;页";
    str += "&ensp;共&ensp;<span name='total'>"+records+"</span>&ensp;页";
    str += "&ensp;<a href='javascript:void(0)' onclick='"+js+"(1)'>首页</a>";
    if(page>1){
        str += "&ensp;<a href='javascript:void(0)' onclick='"+js+"("+(page-1)+")'>上一页</a>";
    }else{
        str += "&ensp;<a>上一页</a>";
    }
    if(page<records){
        str += "&ensp;<a href='javascript:void(0)' onclick='"+js+"("+(page+1)+")'>下一页</a>";
    }else{
        str += "&ensp;<a>下一页</a>";
    }
    str += "&ensp;<a href='javascript:void(0)' onclick='"+js+"("+records+")'>尾页</a>";
    str += "&ensp;跳转到&ensp;<input type='number' min=1 max="+records+" id='"+inputId+"' value="+page+" style='width:40px'/>";
    str += "&ensp;<input type='button' value='go' onclick='var num=$(\"#"+inputId+"\").val();"+js+"(num)'/>";
    $(divObj).empty();
    $(divObj).append(str);
}

