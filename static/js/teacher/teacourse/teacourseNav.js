 $(function () {
            // home nav
            $("#main").click(function(){
               window.location.href='teacourseHome.html';
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
                       window.location.href="teanotice.html";
                    },
                    error:function (commResult) {
                       window.location.href="teacourseHome.html"
                    }
                })
            })

             var $releasenotice = $("#releasenotice");
         
            $releasenotice.bind("click",function () {
               window.location.href="teareleasenotice.html";
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
                        window.location.href="teaCourseInfo.html";
                    },
                    error:function(commResult){
                        window.location.href="teacourseHome.html"
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
            $courseware.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCourseware",
                    data: JSON.stringify(courseid),
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
            $coursetask.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCoursetask",
                    data: JSON.stringify(courseid),
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

            var $layout=$("#layout");
            $layout.bind("click",function(){
            sessionStorage.clear();
            window.location.href="../../main.html"
            })

})

