$(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);
        var coursetask =sessionStorage.getItem("coursetask");
        coursetask=JSON.parse(coursetask);
        if (coursetask.resultCode==0) {
         let coursetasks = coursetask.data.list;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>作业名称</th>
         <th>作业描述</th>
         <th>截至时间</th>
         <th>作业下载</th>
         <th>作业提交</th>
         </tr>`
         for(var i=0;i<coursetasks.length;i++){
            list+=`
            <tr>
            <td >
            ${coursetasks[i].taskName}
            </td>
            <td >
            ${coursetasks[i].taskDescribe}
            </td>
             <td >`
             list+=formatDateTime(coursetasks[i].taskDeadline);
           
            list+=`</td>           
            <td>
              <form action="http://localhost:8081/downloadCoursetask" method="post">
                <input type="hidden" name="download" value="${coursetasks[i].id}"/>
                <button class="btn" type="submit">下载</button>
              </form>
            </td>
            <td>
              <form class="form-inline" id="form-file`
              list+=i
              list+=`" entype="multipart/form-data">
                <div class="form-group">
                  <input type="file" id="file" name="file">
                </div>
                <input type="hidden" name="coursetaskId" value="${coursetasks[i].id}"/>
                <input type="hidden" name="studentId" value="${studentname.id}"/>
                <button type="button" class="btn btn-default" onclick="uploadcoursetask(`
                list+=i
                list+=`)">上传</a>
              </form>
            </td>          
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${coursetask.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (coursetask.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${coursetask.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < coursetask.data.pages; i++) {
  if ((i+1) == coursetask.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (coursetask.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${coursetask.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${coursetask.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${coursetask.data.total}条</a></li>`
list+=`</ul></div>`
list+=`<input id="page" type="hidden">`
        $("#coursetasks").html(list);
    }else{
       let coursetasks = coursetask.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${coursetasks}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#coursetasks").html(list);
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
    url : "http://localhost:8081/selectCoursetask",
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

function downloadcoursetask(id){
 $download = $("#download");
  $download.bind()
  $.ajax({
        type :"post",
        url:"http://localhost:8081/downloadCoursetask",
        data: JSON.stringify(id),
        contentType : "application/json;charset=utf-8",
        dataType:"json",
        success:function(commResult){
           
        },
        error:function(commResult){
            alert("下载失败！");
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
function uploadcoursetask(i){
  var formData=new FormData($("#form-file"+i)[0]);
  $.ajax({
    url: "http://localhost:8081/updateStudentTask",
    type:"post",
    data:formData,
    contentType:false,
    processData:false,
    success:function(commResult){
      if (commResult.resultCode==0) {
        alert("提交成功！！！")
      }else{
        alert(commResult.resultMsg)
      }
    },
    error:function(commResult){
      alert("上传失败！！！")
    }
  })
}

