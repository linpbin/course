 $(function () {
  var studentname = sessionStorage.getItem("student");
  studentname = JSON.parse(studentname);
  $("#name").html(studentname.studentName);
  var announcementList =sessionStorage.getItem("allannouncementList");
  announcementList=JSON.parse(announcementList);
  if (announcementList.resultCode==0) {
   let announcementLists = announcementList.data.list;
   var list = '';
   list+= `  <table class="table"><tbody><thead>
   <tr>
   <th>time</th>
   <th>context</th>
   </tr>`
   for(var i=0;i<announcementLists.length;i++){
    list+=`
    <tr>
    <td >
    ${announcementLists[i].stringTime}
    </td>
    <td >
    ${announcementLists[i].context}
    </td>
    </tr>
    `
  }
  list+=`</thead></tbody></table>`
  list+=`<div class="pagination">
  <ul>
  <li><a href="javascript:void(0)">共
  ${announcementList.data.pages}页</a></li>
  <li><a href="javascript:changePage(1,null)">首页</a></li>`
  if (announcementList.data.hasPreviousPage) {
   list+=`<li><a href="javascript:changePage(${announcementList.data.pageNum}-1,null)">上一页</a></li>`
 }
 for (var i = 0; i < announcementList.data.pages; i++) {
  if ((i+1) == announcementList.data.pageNum) {
    list+=`<li class="active"><a href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }else{
    list+=`<li><a  href="javascript:changePage(${i+1},null)">`
    list+=i+1
    list+=`</a></li>`
  }

}
if (announcementList.data.hasNextPage) {
  list+=`<li><a href="javascript:changePage(${announcementList.data.pageNum}+1,null)">下一页</a></li>`
}
list+= `<li><a href="javascript:changePage(${announcementList.data.pages},null)">末页</a></li>`
list+=`<li><a href="javascript:void(0)">共${announcementList.data.total}条</a></li>`
list+=`</ul></div>`
list+=`<input id="page" type="hidden">`
$("#stunotice").html(list);         
}else{
  let announcementLists = announcementList.resultMsg;
  var list = '';
  list+= `  <table class="table"><tbody>
  `
  list+=`
  <tr>
  <td >
  ${announcementLists}
  </td>

  </tr>
  `
  list+=`</tbody></table>`
  $("#stunotice").html(list);
}

})
 function changePage(pageNo,pageSize){
  var pageParams = {
    "pageNo":pageNo,
    "pageSize":pageSize,
  }
  $.ajax({
    type : "post",
    url : "http://localhost:8081/stuAllAnnouncement",
    dataType:"json",
    data:JSON.stringify(pageParams),
    contentType : "application/json;charset=utf-8",
    dataType:"json",
    success:function (commResult) {
      var announcementList = commResult;
      announcementList = JSON.stringify(announcementList);
      sessionStorage.setItem("allannouncementList",announcementList);
      window.location.href='stuAnnouncement.html';  
    },
    error:function (commResult) {
      window.location.href='stuMain.html';
    }
  })
}

