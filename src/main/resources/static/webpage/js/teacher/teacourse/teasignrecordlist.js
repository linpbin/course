 $(function () {
 var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);

  var coursenoticeList =sessionStorage.getItem("signrecordList");
  coursenoticeList=JSON.parse(coursenoticeList);
  if (coursenoticeList.resultCode==0) {
   let coursenoticeLists = coursenoticeList.data.list;
   var list = '';
   list+= `  <table class="table"><tbody><thead>
   <tr>
   <th>学号</th>
   <th>姓名</th>
   <th>班级</th>
   <th>签到时间</th>
   <th></th>
   </tr>`
   for(var i=0;i<coursenoticeLists.length;i++){
    list+=`
    <tr>
    <td >
    ${coursenoticeLists[i].studentId}
    </td>
    <td >
    ${coursenoticeLists[i].studentName}
    </td>
    <td >
    ${coursenoticeLists[i].studentClazz}
    </td>
    <td >`
   list+=formatDateTime(coursenoticeLists[i].signTime) 
   list+= `</td>
   <td><a href="javascript:void(0)" onclick="deletecourseware(${coursenoticeLists[i].id})">删除</a></td>
            </tr>
   
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
list+=`<li><a href="javascript:void(0)" onclick="expendExcell()">导出Excel</a></li>`
list+=`</ul></div>`
list+=`
  <form id="export" action="http://localhost:8081/exportExcel/${coursenoticeLists[i].signruleId}" method="get">
      <input type="hidden" />
  </form>
`
list+=`<input id="page" type="hidden">`

$("#signrecordlist").html(list);         
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
  $("#signrecordlist").html(list);
}

})
 function changePage(pageNo,pageSize){
  var courseid = sessionStorage.getItem("signrecordList");
  courseid = JSON.parse(courseid);
  var signruleId=courseid.data.list[0].signruleId;
  var pageparam = {
    "pageNo":pageNo,
    "pageSize":pageSize,
    "signruleId":signruleId
  }
  $.ajax({
      type : "post",
      url : "http://localhost:8081/selectAllSignRecord",
      data: JSON.stringify(pageparam),
      contentType : "application/json;charset=utf-8",
      dataType:"json",
      success:function (commResult) {
          var signrules=commResult;
          console.log(signrules)
          signrules=JSON.stringify(signrules);
          sessionStorage.setItem("signrecordList",signrules);
          window.location.href="teasignrecordlist.html";
      },
      error:function (commResult) {
        
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
function deletecourseware(id){
  console.log(id);
  $.ajax({
        type :"delete",
        url:"http://localhost:8081/signrecord/"+id,
        contentType : "application/json;charset=utf-8",
        dataType:"json",
        success:function(commResult){
           if (commResult.resultCode == 0) {
              alert("删除成功!!!");
              var courseid = sessionStorage.getItem("signrecordList");
              courseid = JSON.parse(courseid);
              var signruleId=courseid.data.list[0].signruleId;
              var pageparam = {
                "pageNo":"1",
                "pageSize":"5",
                "signruleId":signruleId
              }
             $.ajax({
              type :"post",
                  url:"http://localhost:8081/selectAllSignRecord",
                  data: JSON.stringify(pageparam),
                  contentType : "application/json;charset=utf-8",
                  dataType:"json",
                  success:function (commResult) {
                    console.log("success")
                    var signrecordList=commResult;
                    signrecordList=JSON.stringify(signrecordList);
                    sessionStorage.setItem("signrecordList",signrecordList);
                    window.location.href="teasignrecordlist.html";
                },
                error:function (commResult) {
                  console.log("success1")
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
function expendExcell(){
  $("#export").submit();
}