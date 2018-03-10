$(function () {
       var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);

        var coursestudentlist =sessionStorage.getItem("coursestudentlist");
        coursestudentlist=JSON.parse(coursestudentlist);
        console.log(coursestudentlist);
        if (coursestudentlist.resultCode==0) {
         let coursestudentlists = coursestudentlist.data.list;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>班级</th>
         <th>学号</th>
         <th>姓名</th>
         <th>性别</th>
         </tr>`
         for(var i=0;i<coursestudentlists.length;i++){
            list+=`
           <tr>
            <td >
            ${coursestudentlists[i].clazzId}
            </td>
            <td >
            ${coursestudentlists[i].id}
            </td>
             <td >
            ${coursestudentlists[i].studentName}
            </td>
             <td >
            ${coursestudentlists[i].sex}
            </td>
            </tr>
            `
          }
         list+=`</thead></tbody></table>`
        list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${coursestudentlist.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (coursestudentlist.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${coursestudentlist.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < coursestudentlist.data.pages; i++) {
  if ((i+1) == coursestudentlist.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (coursestudentlist.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${coursestudentlist.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${coursestudentlist.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${coursestudentlist.data.total}条</a></li>`
list+=`</ul></div>`
list+=`<input id="page" type="hidden">`
        $("#studentlist").html(list);
    }else{
       let coursestudentlists = coursestudentlist.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${coursestudentlists}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#studentlist").html(list);
   }
})

 function changePage(pageNo,pageSize){
  var courseid = sessionStorage.getItem("courseid");
  courseid = JSON.parse(courseid);
  var pageparam = {
    "pageNo":pageNo,
    "pageSize":"10",
    "courseId":courseid
  }
  $.ajax({
    type : "post",
    url : "http://localhost:8081/courseStudents",
    dataType:"json",
    data:JSON.stringify(pageparam),
    contentType : "application/json;charset=utf-8",
    dataType:"json",
    success:function (commResult) {
      var coursestudentlist = commResult;
      coursestudentlist=JSON.stringify(coursestudentlist);
      sessionStorage.setItem("coursestudentlist",coursestudentlist);
      window.location.href='teastudentlist.html';  
    },
    error:function (commResult) {
      window.location.href='teaCourseHome.html';
    }
  })
}

