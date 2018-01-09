$(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);

        var courseware =sessionStorage.getItem("courseware");
        courseware=JSON.parse(courseware);
        if (courseware.resultCode==0) {
         let coursewares = courseware.data;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>课件名</th>
         <th>课件描述</th>
         <th>课件下载</th>
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
            <td><a href="javascript:void(0)" onclick="downloadcourseware(${coursewares[i].id})">下载</a></td>
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        $("#coursewares").html(list);
    }else{
       let coursewares = courseList.resultMsg;
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
function downloadcourseware(id){
  $.ajax({
        type :"post",
        url:"http://localhost:8081/downloadCourseware",
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

