$(function () {
        var teachername = sessionStorage.getItem("teacher");
         JSON.parse(teachername);
        $("#name").html(teachername.teacherName);

        var courseware =sessionStorage.getItem("courseware");
        courseware=JSON.parse(courseware);
        if (courseware.resultCode==0) {
         let coursewares = courseware.data.list;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>课件名</th>
         <th>课件描述</th>
         <th>重新上传</th>
         <th>删除</th>
         </tr>`
         for(var i=0;i<coursewares.length;i++){
            list+=`
           <tr>
            <td >
            ${coursewares[i].coursewareName}
            </td>
            <td >
            ${coursewares[i].describes}
            </td>
            <td>
              <form class="form-inline">
                <div class="form-group">
                  <label for="file">File input</label>
                  <input type="file" id="file">
                </div>
                <a href="javascript:void(0)" class="btn btn-default" onclick="uploadcourseware(${coursewares[i].id})">上传</a>
              </form>
            </td>          
            <td><a href="javascript:void(0)" onclick="deletecourseware(${coursewares[i].id})">删除</a></td>
            </tr>
            `
          }
         list+=`</thead></tbody></table>`
        list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${courseware.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (courseware.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${courseware.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < courseware.data.pages; i++) {
  if ((i+1) == courseware.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (courseware.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${courseware.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${courseware.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${courseware.data.total}条</a></li>`
list+=`</ul></div>`
list+=`<input id="page" type="hidden">`
        $("#coursewares").html(list);
    }else{
       let coursewares = courseware.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${coursewares}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#coursewares").html(list);
   }
})
function deletecourseware(id){
  $.ajax({
        type :"post",
        url:"http://localhost:8081/deleteCourseware",
        data: JSON.stringify(id),
        contentType : "application/json;charset=utf-8",
        dataType:"json",
        success:function(commResult){
           if (commResult.resultCode == 0) {
              alert("删除成功!!!");
              var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
              var pageparam = {
    "pageNo":"1",
    "pageSize":"5",
    "courseId":courseid
  }
               $.ajax({
    type : "post",
    url : "http://localhost:8081/selectCourseware",
    dataType:"json",
    data:JSON.stringify(pageparam),
    contentType : "application/json;charset=utf-8",
    dataType:"json",
    success:function (commResult) {
      var courseware = commResult;
      courseware = JSON.stringify(courseware);
      sessionStorage.setItem("courseware",courseware);
      window.location.href='teaCourseware.html';  
    },
    error:function (commResult) {
      window.location.href='teaCourseHome.html';
    }
  })
           }else{
              alert("删除失败！！");
           }
        },
        error:function(commResult){
            alert("删除失败！");
        }
    })

}
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
    url : "http://localhost:8081/selectCourseware",
    dataType:"json",
    data:JSON.stringify(pageparam),
    contentType : "application/json;charset=utf-8",
    dataType:"json",
    success:function (commResult) {
      var courseware = commResult;
      courseware = JSON.stringify(courseware);
      sessionStorage.setItem("courseware",courseware);
      window.location.href='teaCourseware.html';  
    },
    error:function (commResult) {
      window.location.href='teaCourseHome.html';
    }
  })
}

