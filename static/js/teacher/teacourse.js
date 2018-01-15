$(function () {
        var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);
        var courseList =sessionStorage.getItem("courseList");
        courseList=JSON.parse(courseList);
        if (courseList.resultCode==0) {
         let courseLists = courseList.data;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>课程编号</th>
         <th>课程名称</th>
         </tr>`
         for(var i=0;i<courseLists.length;i++){
            list+=`
            <tr>
            <td >
            ${courseLists[i].id}
            </td>
            <td >
            ${courseLists[i].courseName}
            </td>          
            <td><a href="javascript:void(0)" onclick="showcourse(${courseLists[i].id})">查看详情</a></td>
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        $("#teacourse").html(list);
    }else{
       let courseLists = courseList.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${courseLists}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#teacourse").html(list);
   }
})
 function showcourse(courseid){
      sessionStorage.setItem("courseid",JSON.stringify(courseid));
      window.location.href="teaCourse/teaCourseHome.html"
}
