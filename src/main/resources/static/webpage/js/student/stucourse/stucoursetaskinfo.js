$(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);
        var coursetaskinfo =sessionStorage.getItem("coursetaskinfo");
        coursetaskinfo=JSON.parse(coursetaskinfo);
        if (coursetaskinfo.resultCode==0) {
         let coursetaskinfos = coursetaskinfo.data.list;
         console.log(coursetaskinfos);
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>作业名称</th>
         </tr>`
         for(var i=0;i<coursetaskinfos.length;i++){
            list+=`
            <tr>
            <td >
            ${coursetaskinfos[i].taskName}
            </td>
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${coursetaskinfo.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (coursetaskinfo.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${coursetaskinfo.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < coursetaskinfo.data.pages; i++) {
  if ((i+1) == coursetaskinfo.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (coursetaskinfo.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${coursetaskinfo.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${coursetaskinfo.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${coursetaskinfo.data.total}条</a></li>`
list+=`</ul></div>`
list+=`<input id="page" type="hidden">`
        $("#taskinfos").html(list);
    }else{
       let coursetaskinfos = coursetaskinfo.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${coursetaskinfos}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#taskinfos").html(list);
   }
});
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
    url : "http://localhost:8081/selectCoursetaskinfo",
    dataType:"json",
    data:JSON.stringify(pageparam),
    contentType : "application/json;charset=utf-8",
    dataType:"json",
    success:function (commResult) {
      var coursetask=commResult;
      coursetask=JSON.stringify(coursetask);
      sessionStorage.setItem("coursetask",coursetask);
      window.location.href="stuCourseTask.html";
    },
    error:function (commResult) {
      window.location.href='stuCourseHome.html';
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

