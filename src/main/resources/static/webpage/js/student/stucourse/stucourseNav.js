 $(function () {
            // home nav
            $("#main").click(function(){
               window.location.href='stucourseHome.html';
           })
            
            //notice nav
            var $notice = $("#notice");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            var pageparam={
                "pageNo":"1",
                "pageSize":"5",
                "courseId":courseid,
            }
            $notice.bind("click",function () {
                $.ajax({
                    type : "post",
                    url : "http://localhost:8081/CourseNotice",
                    data: JSON.stringify(pageparam),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function (commResult) {
                        console.log(commResult);
                        var coursenotice=commResult;
                        coursenotice=JSON.stringify(coursenotice);
                       sessionStorage.setItem("coursenotice",coursenotice);
                       window.location.href="stuCourseNotice.html";
                    },
                    error:function (commResult) {
                       window.location.href="stucourseHome.html"
                    }
                })
            })
            // course info
            var $courseinfo =$("#courseinfo");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            $courseinfo.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/courseInfo",
                    data: JSON.stringify(courseid),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var courseinfo=commResult;
                        courseinfo=JSON.stringify(courseinfo);
                        sessionStorage.setItem("courseinfo",courseinfo);
                        window.location.href="stuCourseInfo.html";
                    },
                    error:function(commResult){
                        window.location.href="stucourseHome.html"
                    }
                })
            })
           
            //course teacher
            var $courseteacher =$("#courseteacher");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            $courseteacher.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/classTeacher",
                    data: JSON.stringify(courseid),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                       var courseteacherinfo=commResult;
                        courseteacherinfo=JSON.stringify(courseteacherinfo);
                        sessionStorage.setItem("courseteacherinfo",courseteacherinfo);
                        window.location.href="stuCourseTeacher.html";
                    },
                    error:function(commResult){
                         window.location.href="stucourseHome.html"
                    }
                })
            })
            //courseware
            var $courseware =$("#courseware");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
              var coursewares={
                "pageNo":"1",
                "pageSize":"5",
                "courseId":courseid,
            }
            $courseware.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCourseware",
                    data: JSON.stringify(coursewares),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                       var courseware=commResult;
                        courseware=JSON.stringify(courseware);
                        sessionStorage.setItem("courseware",courseware);
                        window.location.href="stuCourseware.html";
                    },
                    error:function(commResult){
                         window.location.href="stucourseHome.html"
                    }
                })
            })
            //courseTask
            var $coursetask =$("#coursetask");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
             var courseTasks={
                "pageNo":"1",
                "pageSize":"5",
                "courseId":courseid,
            }
            $coursetask.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCoursetask",
                    data: JSON.stringify(courseTasks),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var coursetask=commResult;
                        coursetask=JSON.stringify(coursetask);
                        sessionStorage.setItem("coursetask",coursetask);
                        window.location.href="stuCourseTask.html";
                    },
                    error:function(commResult){
                         window.location.href="stucourseHome.html"
                    }
                })
            })
            //task info 
             var $taskinfo =$("#taskinfo");
            var student = sessionStorage.getItem("student");
            student = JSON.parse(student);
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
             var taskinfos={
                "pageNo":"1",
                "pageSize":"5",
                "studentId":student.id,
                "courseId":courseid,
            }
            $taskinfo.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCoursetaskinfo",
                    data: JSON.stringify(taskinfos),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var coursetaskinfo=commResult;
                        coursetaskinfo=JSON.stringify(coursetaskinfo);
                        sessionStorage.setItem("coursetaskinfo",coursetaskinfo);
                        window.location.href="stuCourseTaskInfo.html";
                    },
                    error:function(commResult){
                         window.location.href="stucourseHome.html"
                    }
                })
            })
            //leavemessage
            var $leavemessage =$("#leavemessage");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            var pageparam={
                "pageNo":"1",
                "pageSize":"15",
                "courseId":courseid,
            }
           $leavemessage.bind("click",function(){
                $.ajax({
                    type : "post",
                    url : "http://localhost:8081/getleavemessage",
                    data: JSON.stringify(pageparam),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function (commResult) {
                        var signrules=commResult;
                        console.log(signrules)
                        signrules=JSON.stringify(signrules);
                        sessionStorage.setItem("leavemessages",signrules);
                        window.location.href="leavemessage.html";
                    },
                    error:function (commResult) {
                       window.location.href="teacourseHome.html"
                    }
                })
           })


            var $layout=$("#layout");
            $layout.bind("click",function(){
            sessionStorage.clear();
            window.location.href="../../main.html"
            })

})

