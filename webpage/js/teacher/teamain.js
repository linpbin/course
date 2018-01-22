$(function(){

               $.ajax({
                    type : "get",
                    url : "http://localhost:8081/teaAnnouncement",
                    dataType:"json",
                    success:function (commResult) {
                        var announcementList = commResult;
                        announcementList = JSON.stringify(announcementList);
                        sessionStorage.setItem("announcementList",announcementList);
          
                    },
                    error:function (commResult) {
                       
                    }
                });
       var teachername = sessionStorage.getItem("teacher");
       teachername = JSON.parse(teachername);
        var userId = teachername.id;
          $.ajax({
                    type :"post",
                    url:"http://localhost:8081/teacherInfo",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var teacherInfo =commResult;
                        teacherInfo=JSON.stringify(teacherInfo);
                        sessionStorage.setItem("teacherInfo",teacherInfo);
                      
                    },
                    error:function(commResult){

                    }
                     
                })
           $.ajax({
                    type :"post",
                    url:"http://localhost:8081/showTeaCourseList",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){               
                        var courseList =commResult;
                        courseList=JSON.stringify(courseList);
                        sessionStorage.setItem("courseList",courseList);
              
                    },
                    error:function(commResult){
                  
                    }
                })
       $("#name").html(teachername.teacherName);
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
            $("#teanotice").html(list);
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
            $("#teanotice").html(list);
        }
        var teacherInfoList =sessionStorage.getItem("teacherInfo");
        teacherInfoList=JSON.parse(teacherInfoList);
        if (teacherInfoList.resultCode==0) {
           let teacherInfoLists = teacherInfoList.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
           <th>基本信息</th>
            </tr>
            <tr>
            <td>用户名</td>
            <td>${teacherInfoLists.id}</td>
            </tr>
            <tr>
            <td>姓名</td>
            <td>${teacherInfoLists.teacherName}</td>
            </tr>
            <tr>
            <td>email</td>
            <td>${teacherInfoLists.teacherEmail}</td>
            </tr>
            <tr>
            <td>办公电话</td>
            <td>${teacherInfoLists.teacherPhone}</td>
            </tr>
            <tr>
            <td>办公地点</td>
            <td>${teacherInfoLists.teacherWorkingplace}</td>
            </tr>
            <tr>
            <td>办公时间</td>
            <td>${teacherInfoLists.teacherWorkingTime}</td>
            </tr>
            <tr>
            `
            list+=`</thead></tbody></table>`
            $("#teainfo").html(list);
        }else{
            let teacherInfoLists = teacherInfoList.resultMsg;
           var list = '';
           list+= `  <table class="table"><tbody>
           `
            list+=`
            <tr>
            <td >
            ${teacherInfoLists}
            </td>
        
            </tr>
            `
            list+=`</tbody></table>`
            $("#teainfo").html(list);
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
