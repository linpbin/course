$(function(){
       var studentname = sessionStorage.getItem("student");
       studentname = JSON.parse(studentname);
        var userId = studentname.id;
       $("#name").html(studentname.studentName);
       var announcementList =sessionStorage.getItem("announcementList");
        announcementList=JSON.parse(announcementList);
        if (announcementList.resultCode==0) {
           let announcementLists = announcementList.data;
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
        var studnetInfoList =sessionStorage.getItem("studnetInfo");
        studnetInfoList=JSON.parse(studnetInfoList);
        if (studnetInfoList.resultCode==0) {
           let studnetInfoLists = studnetInfoList.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
            <th>基本信息</th>
            </tr>
            <tr>
            <td>学号</td>
            <td>${studnetInfoLists.id}</td>
            </tr>
            <tr>
            <td>姓名</td>
            <td>${studnetInfoLists.studentName}</td>
            </tr>
            <tr>
            <td>系别</td>
            <td>${studnetInfoLists.studentFaculty}</td>
            </tr>
            <tr>
            <td>专业</td>
            <td>${studnetInfoLists.studentProfession}</td>
            </tr>
            <tr>
            <td>性别</td>
            <td>${studnetInfoLists.sex}</td>
            </tr>
            <tr>
            <td>入学年份</td>
            <td>${studnetInfoLists.studentEnrollmentyear}</td>
            </tr>
            <tr>
            <td>班级</td>
            <td>${studnetInfoLists.clazz.id}</td>
            </tr>
            <tr>
            <td>班级名称</td>
            <td>${studnetInfoLists.clazz.clazzName}</td>
            </tr>`
            list+=`</thead></tbody></table>`
            $("#stuinfo").html(list);
        }else{
            let studnetInfoLists = studnetInfoList.resultMsg;
           var list = '';
           list+= `  <table class="table"><tbody>
           `
            list+=`
            <tr>
            <td >
            ${studnetInfoLists}
            </td>
        
            </tr>
            `
            list+=`</tbody></table>`
            $("#stuinfo").html(list);
        }
         var courseList =sessionStorage.getItem("courseList");
        courseList=JSON.parse(courseList);
        if (courseList.resultCode==0) {
         let courseLists = courseList.data;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>课程编号</th>
         <th>课程名称</th>
         <th>课程介绍</th>
         <th>任课老师</th>
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
            <td >
            ${courseLists[i].courseDescribe}
            </td>
            <td >
            ${courseLists[i].teacherId.teacherName}
            </td>
            <td><a href="javascript:void(0)" onclick="showcourse(${courseLists[i].id})">查看详情</a></td>
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        $("#stucourse").html(list);
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
       $("#stucourse").html(list);
   }
   })
 function showcourse(courseid){
      sessionStorage.setItem("courseid",JSON.stringify(courseid));
      window.location.href="stuCourse/stuCourseHome.html"
   }
