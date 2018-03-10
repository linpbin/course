 $(function () {
  var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);

  var coursenoticeList =sessionStorage.getItem("leavemessages");
  coursenoticeList=JSON.parse(coursenoticeList);
  if (coursenoticeList.resultCode==0) {
   let coursenoticeLists = coursenoticeList.data.list;
   var list = '';
   list+= `  <table class="table"><tbody><thead>
   <tr>
   <th>时间</th>
   <th>内容</th>
   </tr>`
   for(var i=0;i<coursenoticeLists.length;i++){
    list+=`
    <tr>
    <td >`
    list+=formatDateTime(coursenoticeLists[i].createTime) 
    list+= `</td>
    <td >
       ${coursenoticeLists[i].context}
    </td>
    </tr>
    `
  }
  list+=`</thead></tbody></table>`
  list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${coursenoticeList.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (coursenoticeList.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${coursenoticeList.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < coursenoticeList.data.pages; i++) {
  if ((i+1) == coursenoticeList.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (coursenoticeList.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${coursenoticeList.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${coursenoticeList.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${coursenoticeList.data.total}条</a></li>`
list+=`</ul></div>`
list+=`
<div id="releasenoticess" class="block-body collapse in">
            <form class="form-inline">
              <div class="form-group">
                <input type="text" class="form-control" id="contents">
              </div>
              <a href="javascript:void(0)" id="renotice" class="btn btn-default" onclick="leavemessage()">发言</a> 
            </form>
          </div>
`
list+=`<input id="page" type="hidden">`
$("#leavemessagees").html(list);         
}else{
  let coursenoticeLists = coursenoticeList.resultMsg;
  var list = '';
  list+= `  <table class="table"><tbody>
  `
  list+=`
  <tr>
  <td >
  ${coursenoticeLists}
  </td>

  </tr>
  `
  list+=`</tbody></table>`
  $("#leavemessagees").html(list);
}

})
 function changePage(pageNo,pageSize){
  var courseid = sessionStorage.getItem("courseid");
  courseid = JSON.parse(courseid);
  var pageparam = {
    "pageNo":pageNo,
    "pageSize":pageSize,
    "courseId":courseid
  }
  $.ajax({
      type : "post",
      url : "http://localhost:8081/getleavemessage",
      data: JSON.stringify(pageparam),
      contentType : "application/json;charset=utf-8",
      dataType:"json",
       success:function (commResult) {
        var signrules=commResult;
        console.log(signrules)
        signrules=JSON.stringify(signrules);
        sessionStorage.setItem("leavemessages",signrules);
        window.location.href="leavemessage.html";
    },
    error:function (commResult) {
       window.location.href="teacourseHome.html"
    }
  })
}
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}; 
function leavemessage(){
   var teachername = sessionStorage.getItem("teacher");
   teachername=JSON.parse(teachername);
  var name=teachername.teacherName;
  var courseid = sessionStorage.getItem("courseid");
  courseid = JSON.parse(courseid);
  var context = $("#contents").val();
  var pageparam = {
    "name":name,
    "courseId":courseid,
    "context":context
  }
  $.ajax({
     type : "post",
      url : "http://localhost:8081/leavemessage",
      data: JSON.stringify(pageparam),
      contentType : "application/json;charset=utf-8",
      dataType:"json",
      success:function (commResult) {
          alert("发言成功！");
           var courseids = sessionStorage.getItem("courseid");
            courseids = JSON.parse(courseid);
           var pageparams={
                "pageNo":"1",
                "pageSize":"15",
                "courseId":courseids,
            }
           $.ajax({
                    type : "post",
                    url : "http://localhost:8081/getleavemessage",
                    data: JSON.stringify(pageparams),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function (commResult) {
                        var signrules=commResult;
                        console.log(signrules)
                        signrules=JSON.stringify(signrules);
                        sessionStorage.setItem("leavemessages",signrules);
                        window.location.href="leavemessage.html";
                    },
                    error:function (commResult) {
                       window.location.href="teacourseHome.html"
                    }
                }) 
      },
      error:function (commResult) {
         alert("发言失败！");
      }
  })
}
