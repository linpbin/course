 $(function () {
  var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);
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
           
            //course studentes
            var $coursestudent =$("#coursestudent");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
           
            $coursestudent.bind("click",function(){
                 var pageparam={
                "pageNo":"1",
                "pageSize":"10",
                "courseId":courseid,
            }
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/courseStudents",
                    data: JSON.stringify(pageparam),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                       var coursestudentlist=commResult;
                        coursestudentlist=JSON.stringify(coursestudentlist);
                        sessionStorage.setItem("coursestudentlist",coursestudentlist);
                        window.location.href="teastudentlist.html";
                    },
                    error:function(commResult){
                         window.location.href="teaCourseHome.html"
                    }
                })
            })
            //courseware
          var $courseware =$("#courseware");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
              var pageparam={
                "pageNo":"1",
                "pageSize":"5",
                "courseId":courseid,
            }
            $courseware.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/selectCourseware",
                    data: JSON.stringify(pageparam),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                       var courseware=commResult;
                        courseware=JSON.stringify(courseware);
                        sessionStorage.setItem("courseware",courseware);
                        window.location.href="teaCourseware.html";
                    },
                    error:function(commResult){
                         window.location.href="teacourseHome.html"
                    }
                })
            })
            //courseTask
            var $coursetask =$("#coursetask");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            $coursetask.bind("click",function(){
                window.location.href="stuCourseTask.html";
            })

            //signrule
            var $signrule =$("#signrule");
            $signrule.bind("click",function(){
                window.location.href="teasignrule.html";
            })
            //signrecord
            var $signrecoed =$("#signrecoed");
            var courseid = sessionStorage.getItem("courseid");
            courseid = JSON.parse(courseid);
            var pageparam={
                "pageNo":"1",
                "pageSize":"5",
                "courseId":courseid,
            }
            $signrecoed.bind("click",function(){
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

