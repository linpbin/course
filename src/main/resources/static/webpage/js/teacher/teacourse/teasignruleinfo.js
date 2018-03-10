 $(function () {
  var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);

  var coursenoticeList =sessionStorage.getItem("signrules");
  coursenoticeList=JSON.parse(coursenoticeList);
  if (coursenoticeList.resultCode==0) {
   let coursenoticeLists = coursenoticeList.data.list;
   var list = '';
   list+= `  <table class="table"><tbody><thead>
   <tr>
   <th>考勤时间</th>
   <th>查看签到列表</th>
   </tr>`
   for(var i=0;i<coursenoticeLists.length;i++){
    list+=`
    <tr>
    <td >
    ${coursenoticeLists[i].startTime}
    </td>
    <td >
      <a href="javascript:void(0)" onclick="showinfo(${coursenoticeLists[i].id})">查看</a>
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
list+=`<input id="page" type="hidden">`
$("#signrules").html(list);         
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
  $("#signrules").html(list);
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
      url : "http://localhost:8081/signrules",
      data: JSON.stringify(pageparam),
      contentType : "application/json;charset=utf-8",
      dataType:"json",
      success:function (commResult) {
          var signrules=commResult;
          console.log(signrules)
          signrules=JSON.stringify(signrules);
          sessionStorage.setItem("signrules",signrules);
          window.location.href="teasignruleinfo.html";
      },
      error:function (commResult) {
         window.location.href="teacourseHome.html"
      }
  })
}
function showinfo(id){
   var pageparam = {
    "pageNo":"1",
    "pageSize":"5",
    "signruleId":id
  }
  $.ajax({
    type :"post",
        url:"http://localhost:8081/selectAllSignRecord",
        data: JSON.stringify(pageparam),
        contentType : "application/json;charset=utf-8",
        dataType:"json",
        success:function (commResult) {
          var signrecordList=commResult;
          console.log(signrecordList)
          signrecordList=JSON.stringify(signrecordList);
          sessionStorage.setItem("signrecordList",signrecordList);
          window.location.href="teasignrecordlist.html";
      },
      error:function (commResult) {
         window.location.href="teacourseHome.html"
      }
  })
}

